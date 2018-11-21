$(function () {
    //...静态变量和全局变量...
    var titleMap = {
        "allFile": "全部文件",
        "imageFile": "图片",
        "textFile": "文档",
        "recycleFile": "回收站",
    }
    var disabledButtonFlag = false;
    var jqueryTreeScrollHeight = "200px"

    //...初始化...
    //初始化模态modal (蒙版)
    //弹框
    var $modal = $("#modal").modal()
    //目录树蒙板
    var $modalDirTree = null;

    //初始化化容量条
    var $sizeBar = $("#size_progress_bar").progressbar({
        text_default: "0G/100G",
    });
    $sizeBar.setProgress(0.5, "50G/100G")
    // scrollsXY("#file-list-table")

    $(document).ready(function () {
        $("#allFile").trigger("click")
    });


    //...状态管理...
    //获得左边选中的按钮的id
    function GetItemId() {
        return $(".con-left .item-list").find(".select").prop("id")
    }

    //获得右边表格checkbox选中数量
    function GetSelectNum() {
        return $(".selectFile:checked").length
    }

    //更新和显示右边表格checkbox选中的数量,并且在此管理一些部件。 action 特定的操作。
    function ShowSelectNum(action) {
        num = GetSelectNum();
        if (num > 0) {
            $("#selectFileNum").html("(已选中" + num + "个)");
        } else {
            $("#selectFileNum").html("文件名");
            $("#selectAllList").prop("checked", false)
        }
        //默认的
        // console.log(action)
        if (action == null) {
            //隐藏操作栏
            if (num <= 0) {
                showOrHideOperateBar(false)
            } else {
                showOrHideOperateBar(true)
            }
            //禁止重命名按钮
            enableRename(num <= 1);
        } else {
            action(num);
        }
        // if (num<=1){
        //     $("#renameFile").removeClass("disabled");
        // }else{
        //     $("#renameFile").addClass("disabled");
        // }

        if (GetItemId() != "recycleFile") {
            $(".operate #doRecycle").remove();
        }
        return num;
    }

    //管理目录深度层次的数组。数组内容是目录层次。
    var dirStack = {
        data: [],
        toString: function () {
            var str = ""
            for (var i = 0; i < this.data.length; i++) {
                str += "/" + this.data[i]
            }
            return str;
        },
        reset: function (title) {
            this.data = [];
            if (title != null) {
                this.data[0] = title;
            }
            return this;
        },
        push:function(title){
            this.data.push(title)
        },
        pop:function(){
            return this.data.pop()
        }
    };

    //通过左边栏、文件深度，更新右边表格的 item title
    //title为null ,back_num<0或为null时，就是直接到初始层。用于左边栏切换。
    //back_num>0时，就是往上层。back_num意味着往上多少。结合.file-list-table的data-deep属性。若超出了data-deep，就回到最初层。
    //此时title 没有用
    //back_num=0时，就是往下一层。title是目录名。
    //无论那种情况，都要操作dirStack。别的地方不可操作这个对象
    
    //ajax在外层写，写在这个函数后面。
    //返回:对象类型
    function ChangeItemTitle(back_num, title) {
        back_num == null ? back_num = -1 : back_num = back_num;
        title == null ? title = "未命名" : title = title;

        //备份,ajax失败后可用于恢复$(".item-title").html(_backupHTML);
        var _backupHTML=$(".item-title").html();

        if (back_num < 0) {
            //title为null ,back_num<0或为null时，就是直接到初始层。用于左边栏切换。
            var itemTitle = titleMap[GetItemId()];
            $("#itemTitle").html(itemTitle)
            $(".item-title ul").empty();
            $("#gobackToLast").remove();

            dirStack.reset(itemTitle)
        } else if (back_num == 0) {
            //back_num=0时，就是往下一层。title是目录名。
            //原来的目录
            var preTitle = $("#itemTitle").html();

            //原来的data-deep -1
            $lis = $(".item-title ul").find("li")
            $lis.each(function () {
                var data_deep = $(this).find("a").attr("data-deep")
                $(this).find("a").attr("data-deep", parseInt(data_deep) - 1)
            })

            //返回上一级按钮
            $gobackToLast = $(".item-title").find("#gobackToLast");
            if ($gobackToLast.length == 0) {
                var _gobackHTML = "<a id='gobackToLast' data-deep='-1' class='dir_go_back' href='javascript:void(0)'>" +
                    "返回上一级<span class='EKIHPEb'>|</span></a>";
                $(".item-title ul").prepend(_gobackHTML)
            }

            //原来的title变成a
            var _nextHTML = "<li><a data-deep='-1' class='dir_go_back' href='javascript:void(0)'>" +
                preTitle +
                "</a><span class='KLxwHFb'>></span></li>"
            $(".item-title ul").append(_nextHTML)
            //设置title
            $("#itemTitle ").html(title);

            //data-deep
            // var dataDeep = $("#file-list-table").attr("data-deep")
            // $("#file-list-table").attr("data-deep",parseInt(dataDeep) + 1);

            dirStack.push(title);
        } else if (back_num > 0) {
            var pretitle=""
            $lis = $(".item-title ul").find("li")
            $lis.each(function () {
                var data_deep =parseInt($(this).find("a").attr("data-deep"))
                if (-data_deep<=back_num){
                    if (-data_deep==back_num){
                        pretitle=$(this).find("a").html();
                    }
                    $(this).remove()
                }else{
                    $(this).find("a").attr("data-deep", data_deep+1)
                }
            })
            $("#itemTitle ").html(pretitle);
            $lis = $(".item-title ul").find("li")
            if ($lis.length==0){
                $("#gobackToLast").remove();
            }
        }
        return {
            dirname:dirStack.toString(),
            _backupHTML:_backupHTML,
        }
    }

    //状态栏 click事件 返回上层目录
    $(".item-title").click(function(event){
        $target=$(event.target);
        // console.log($target)
        if($target.is("a.dir_go_back")||$target.is("a#gobackToLast.dir_go_back")){
            var dataDeep=$target.attr("data-deep")
            var obj=ChangeItemTitle(-dataDeep)
            //TODO:ajax and drawTable
            // console.log(obj)
            
        }else {
            return 
        }
    })

    //...左边...
    $(".item-list").click(function (event) {
        var $target = $(event.target);
        // console.log($target.prop("id"));
        // console.log($target)

        //已选中的就return
        if ($target.hasClass("select")) {
            return;
        }

        if (!disabledButtonFlag) {
            $(".item-list").find("a").removeClass("select")
            $target.addClass("select");

            //TOOD:change right and data
            var result_data = getMockData($target.prop("id"));
            drawTable(result_data);
            ChangeItemTitle()

            //移除全选按钮的状态，已选中归零
            $("input#selectAllList").prop("checked", false)
            ShowSelectNum();

            //回收站隐藏掉新建和上传按钮
            if ($target.is("#recycleFile")) {
                $("#uploadFile,#newFolder").hide()
            } else {
                $("#uploadFile,#newFolder").show()
            }

        }
    })

    //...右边...
    //..checkbox控制..
    //checkbox全选和单选控制
    $("#file-list-table").click(function (event) {
        var $target = $(event.target);
        // console.log($target,$target.is("input#selectAllList"));

        //选中时触发的事件
        var checkboxAction = null

        //回收站选中checkbox时触发的事假不一样
        if (GetItemId() == "recycleFile") {
            checkboxAction = function (num) {
                if (num <= 0) {
                    $(".operate #doRecycle").remove();
                } else {
                    console.log($(".operate .operate-file #doRecycle"))
                    if ($(".operate #doRecycle").length == 0) {
                        $(".operate").append("<a id='doRecycle' class='operate-common' href='javascript:void(0)'><i class='fa fa-recycle'></i>还原</a>")
                        $(".operate #doRecycle").bind("click", doRecycle)
                    }
                }
            }
        }

        if ($target.is("input#selectAllList")) {
            $(".tbcol-1 .selectFile").prop("checked", $(event.target).prop("checked"));
            ShowSelectNum(checkboxAction);
        } else if ($target.is("input.selectFile")) {
            ShowSelectNum(checkboxAction);
        }
    })


    //..表格控制..
    //  清除原有内容，将新内容绘入表格。dataDeep是文件深度。
    //#file-list-table的属性data-deep在drawTable函数操作。外层不准操作！！！ 
    function drawTable(resultList, dataDeep) {
        dataDeep == null ? dataDeep = 0 : dataDeep = dataDeep;
        //清空原有内容
        $("#file-list-table").find("tr").not(".table-title").remove();
        //新内容渲染模板
        var table_tpl = document.getElementById("table_tpl").innerHTML;
        var _HTML = template(table_tpl, resultList);
        // console.log(_HTML);
        $("#file-list-table").append(_HTML);
        //刷新状态
        ShowSelectNum()
        $("#file-list-table").attr("data-deep", dataDeep)
    }

    // 重名控制TODO
    function dealDupName(name) {
        // var name=name;
        // $target = $("#file-list-table .tbcol-1 span")
        // $.each($target, function () {
        //     if ($(this).html() == name) {
        //         reg = /\b\([\s\S]*\)$/;
        //         var st = reg.exec(name)
        //          console.log(st,name)
        //         if (st!=null&&st.length != 0) {
        //             var st1 = st[0];
        //             var st2 = st1.substring(1, st1.length).substring(0, st1.length - 1)
        //             i=parseInt(st2) + 1;
        //             name = dealDupName(name.replace(reg,"("+i+")"));
        //         }else{
        //             console.log(22)
        //             name=dealDupName(name+"("+1+")");
        //         }
        //     }
        // })
        return name;
    }

    //表格click控制,即选中这行,th是排序
    var tabClickTimer = null //解决单双击冲突
    $("#file-list-table").click(function (event) {
        clearTimeout(tabClickTimer)
        var $target = $(event.target);
        // console.log($target)
        //checkbox有单独的事件，排除掉
        if ($target.is("input.selectFile")) {
            //do nothing
            return
        }

        tabClickTimer = setTimeout(function () {
            if ($target.is("th")) {
                alert("尊敬的用户，排序功能敬请期待")
            } else {
                //选中这行
                $target.parent().find(".selectFile").trigger("click");
            }
        }, 200)
    })

    //双击表格中的元素时，调到下一层目录
    $("#file-list-table").dblclick(function (event) {
        clearTimeout(tabClickTimer)
        var $target = $(event.target);

        //checkbox有单独的事件，排除掉
        if ($target.is("input.selectFile")) {
            //do nothing
            return
        }

        //回收站不能双击进入下一层目录
        if (GetItemId() == "recycleFile") {
            return
        }

        tabClickTimer = setTimeout(function () {
            if ($target.is("th")) {
                //do nothing
            } else {
                //下一页
                var result_data = getMockData(GetItemId());
                //ChangeItemTitle(0,"111");
               var obj=ChangeItemTitle(0, $target.parent().find("span").html());
                //TODO:ajax drawtable
                // console.log(obj)
            }
        }, 200)
    })

    //..操作栏控制..
    //操作栏隐藏和显示。true为显示
    function showOrHideOperateBar(show) {
        if (show) {
            $(".operate ul").removeClass("hide");
        } else {
            $(".operate ul").addClass("hide");
        }
    }

    //新建文件夹按钮
    $("#newFolder").click(function (event) {
        // prepend

        //TODO:重名控制
        var date = new Date();
        dateStr = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
        resultList = {
            resultList: [{
                name: dealDupName("新建文件夹"),
                size: "0KB",
                modifiedTime: dateStr,
            }]
        }

        var table_tpl = document.getElementById("table_tpl").innerHTML;
        var _HTML = template(table_tpl, resultList);
        $("#file-list-table .table-title").after(_HTML);
        $target = $("#file-list-table .table-title").next().find(".tbcol-1")
        rename($target, $target.find("span"),
            //confirm_callback
            function () {
                //do ajax  if fail,cancel。后端将文件名字发送回来。
            },
            //cancel_callback
            function () {
                cancel()
            })

        function cancel() {
            $("#file-list-table .table-title").next().remove();
        }
    })


    //下载按钮
    $("#downloadFile").click(function (event) {

        var $checked_el = $("#file-list-table .selectFile:checked");
        var names = [];
        $checked_el.each(function () {
            var name = $(this).siblings("span").html()
            names.push(name)
        })
        //TODO：confirm
        //TODO：download
        alert("下载" + names + "完毕！");
    })

    //删除按钮
    $("#deleteFile").click(function (event) {
        var $checked_el = $("#file-list-table .selectFile:checked");
        var tbcolToDelete = [];
        var names = [];
        $checked_el.each(function () {
            tbcolToDelete.push($(this).parent().parent().parent())
            var name = $(this).siblings("span").html()
            names.push(name)
        })

        //确认
        var modalOpt = {
            'title': '确认删除',
            'content': "确认要把所选的" + GetSelectNum() + "个文件放入回收站吗?<br/> 删除的文件可在10天内通过回收站还原",
            'confirmFunc': function () {
                for (i = 0; i < tbcolToDelete.length; i++) {
                    tbcolToDelete[i].remove();
                }

                //$modal.children().remove()
                $modal.hideModal();
                ShowSelectNum();
                showOrHideOperateBar(false);
            },
            'cancelFunc': function () {
                //$modal.children().remove()
                $modal.hideModal();
            }
        }
        $modal.boxAlert(modalOpt);
        $modal.show();
        //TODO：ajax
    })

    //重命名按钮
    $("#renameFile").click(function (event) {
        var $target = $("#file-list-table .selectFile:checked").parent() //<div class="tbcol-1">
        var $name_el = $("#file-list-table .selectFile:checked").siblings("span")

        rename($target, $name_el)
    })

    function rename($target, $name_el, confirm_callback, cancel_callback) {
        var name = $name_el.html();
        $name_el.remove();
        var table_rename_tpl = document.getElementById("table_rename").innerHTML;
        var _HTML = template(table_rename_tpl, {
            "name": name,
        });
        disabledAllButton()
        $target.append(_HTML);
        //让input 选中状态
        $target.find("input").select().focus();
        //TODO:给按钮绑定监听
        $target.find(".rename_cancel").bind("click", function () {
            dorename(name)
            cancel_callback == null ? function () {} : cancel_callback();
        });

        $target.find(".rename_confirm").bind("click", function () {
            var new_name = $target.find(".rename_input").val();
            //TODO: ajax成功后调用
            dorename(new_name)
            confirm_callback == null ? function () {} : confirm_callback();
        });

        function dorename(new_name) {
            $target.find("div.rename").remove();
            $target.append("<span>" + new_name + "</span>");
            enableAllButton();
        }
    }

    //复制到按钮
    $("#copyFile").click(function (event) {
        var $checked_el = $("#file-list-table .selectFile:checked");
        var tbcolToDelete = [];
        var names = [];
        $checked_el.each(function () {
            // tbcolToDelete.push($(this).parent().parent().parent())
            var name = $(this).siblings("span").html()
            names.push(name)
        })
        //lazy load
        $modalDirTree == null ? $modalDirTree = initModalDirTree() : $modalDirTree = $modalDirTree;
        $modalDirTree.changeTitle("复制到")
        $modalDirTree.show()
        rebindConfirmAndCancelEventForDirTreeButton(confirmCallback)

        function confirmCallback() {
            //TODO:ajax and copy dir
            var absolutePath = getAbsolutePathFromDirTree()
            alert(names + "已经复制到" + absolutePath)
        }
    })

    //移动到按钮
    $("#moveFile").click(function (event) {
        var $checked_el = $("#file-list-table .selectFile:checked");
        var tbcolToDelete = [];
        var names = [];
        $checked_el.each(function () {
            // tbcolToDelete.push($(this).parent().parent().parent())
            var name = $(this).siblings("span").html()
            names.push(name)
        })
        //lazy load
        $modalDirTree == null ? $modalDirTree = initModalDirTree() : $modalDirTree = $modalDirTree;
        $modalDirTree.changeTitle("移动到")
        $modalDirTree.show()

        rebindConfirmAndCancelEventForDirTreeButton(confirmCallback)

        function confirmCallback() {
            //TODO:ajax and move dir
            var absolutePath = getAbsolutePathFromDirTree()
            alert(names + "已经移动到" + absolutePath)
        }
    })

    // 文件树
    function initModalDirTree() {
        var __dirTreeButtonHTML = $("#mock_dir_tree_button").html()
        //TODO:based on back data ajax not mock
        var _dirTreeHTML = drawDirTreeUl(jqueryTreeScrollHeight, getMockDirData())

        var $modalDirTree = $("#modalDirTree").modal()
        $modalDirTree.boxContainer({
            closeFunc: function () {
                $modalDirTree.hide();
                $(".box_container").hide();
            }
        }, _dirTreeHTML, __dirTreeButtonHTML);
        jqueryTreeScroll()
        $modalDirTree.changeTitle("移动到")

        $modalDirTree.find("a[data-deep='0']").addClass("ontree")

        $(".box_container .box_container_con").addClass("border_grey_solid")
        $(".box_container .box_container_button").addClass("box_container_button_dirTree")
        $("#dirTreeNewFolder").bind("click", function () {
            //TODO：new folder
            alert("新建文件夹")
        })

        return $modalDirTree
    }

    //从目录树中获得完整的目录
    function getAbsolutePathFromDirTree() {
        $ontree = $(".treebox .tree").find(".ontree")
        return $ontree.attr("title")
    }
    //重新绑定目录树box的确认和取消按钮事件
    function rebindConfirmAndCancelEventForDirTreeButton(confirmCallback, cancelCallback) {
        confirmCallback == null ? confirmCallback = function () {} : confirmCallback = confirmCallback;
        cancelCallback == null ? cancelCallback = function () {} : cancelCallback = cancelCallback;
        $("#dirTreeConfirm").unbind();
        $("#dirTreeCancel").unbind();
        $("#dirTreeConfirm").bind("click", function () {
            $modalDirTree.hide();
            confirmCallback();
        })

        $("#dirTreeCancel").bind("click", function () {
            $modalDirTree.hide();
            cancelCallback();
        })

    }

    //还原按钮
    function doRecycle() {
        var $checked_el = $("#file-list-table .selectFile:checked");
        var tbcolToDelete = [];
        var names = [];
        $checked_el.each(function () {
            tbcolToDelete.push($(this).parent().parent().parent())
            var name = $(this).siblings("span").html()
            names.push(name)
        })

        //确认
        var modalOpt = {
            'title': '确认还原',
            'content': "确认要把所选的" + GetSelectNum() + "个文件还原吗?",
            'confirmFunc': function () {
                //TODO：ajax
                for (i = 0; i < tbcolToDelete.length; i++) {
                    tbcolToDelete[i].remove();
                }

                //$modal.children().remove()
                $modal.hideModal();
                ShowSelectNum();
                $("#doRecycle").remove();
            },
            'cancelFunc': function () {
                //$modal.children().remove()
                $modal.hideModal();
            }
        }
        $modal.boxAlert(modalOpt);
        $modal.show();

    }

    //..按钮控制..
    //是否允许下载文件
    function enableDownload(yes) {
        if (yes) {
            $("#downloadFile").removeClass("disabled");
        } else {
            $("#downloadFile").addClass("disabled");
        }
    }
    //是否允许删除文件
    function enableDelete(yes) {
        if (yes) {
            $("#deleteFile").removeClass("disabled");
        } else {
            $("#deleteFile").addClass("disabled");
        }
    }
    //是否允许重命名文件
    function enableRename(yes) {
        if (yes) {
            $("#renameFile").removeClass("disabled");
        } else {
            $("#renameFile").addClass("disabled");
        }
    }
    //是否允许复制文件
    function enableCopy(yes) {
        if (yes) {
            $("#copyFile").removeClass("disabled");
        } else {
            $("#copyFile").addClass("disabled");
        }
    }
    //是否允许移动文件
    function enableMove(yes) {
        if (yes) {
            $("#moveFile").removeClass("disabled");
        } else {
            $("#moveFile").addClass("disabled");
        }
    }

    //按钮全部禁用
    function disabledAllButton() {
        // $("button").attr()
        $("a").addClass("disabled")
        $("button").attr("disabled", true)
        $("input").attr("disabled", true)
        disabledButtonFlag = true
    }

    //按钮全部启用
    function enableAllButton() {
        // $("button").attr()
        $("a").removeClass("disabled")
        $("button").attr("disabled", false)
        $("input").attr("disabled", false)
        disabledButtonFlag = false
    }
})