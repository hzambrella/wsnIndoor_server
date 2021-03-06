$(function () {
    var vdata = {
        finishLoading: true, //异步请求的数据是否加载完毕
        hasLoadMarkerOnBmap: false, //是否已经把新数据显示在地图中
        title: getTitle("buildInfo") + "/" + getTitle(),
        data: commonPageData,
        currentData: {}, //选中marker的data
        currentMarker: null, //被选中该的marker
    }

    var getData = function () {
        //防连击
        $("#refresh").disabledButton()
        //初始化地图
        initBMap()
        vdata.finishLoading = false;
        //把地图上的覆盖物清除掉
        if (BMapObj) {
            BMapObj.clearOverlays();
        }
        vdata.hasLoadMarkerOnBmap = false;
        /*setTimeout(function () {
            vdata.data = getBuildDataMock();
            vdata.finishLoading = true;
            showBuildDataAsMarker()
        }, 200)*/

        $.ajax({
            url: AjaxReqUrl.building,
            method: 'get',
            dataType: 'json',
            data: {},
            success: function (data) {
                vdata.data = data
                vdata.finishLoading = true;
                showBuildDataAsMarker()

                $("#refresh").enableButton()
            },
            error: function (data, status, e) {
                console.log(data,status,e)
                $("#refresh").enableButton()
                vdata.finishLoading = true;
            }
        })
    }


    // 如果搞成tabs切换 有bug:表格页按刷新，切换到地图页，地图上的marker会偏移
    var showBuildDataAsMarker = function () {
        //当标签页是地图展示，且地图已经初始化，且新数据未显示在地图中时
        if (!app.hasLoadMarkerOnBmap) {
            list = app.data.obj.list;
            for (var item in list) {
                marker = addMarkerOnBMap(list[item].x, list[item].y, list[item].name, list[item].address, list[item])
                marker.addEventListener("click", function () {
                    var p = this.getPosition(); //获取marker的位置
                    vdata.currentData = this.data;
                    if (vdata.currentMarker !== null) {
                        vdata.currentMarker.setAnimation(null) //上个被选中的marker停止弹跳
                    }
                    this.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                    vdata.currentMarker = this;
                });
            }
            app.hasLoadMarkerOnBmap = true;
        }
    }

    var app = new Vue({
        el: "#mainbox",
        data: vdata,
        mounted: function () {

        },
        methods: {
            exit:function logout(){
                alert(1)
            },
            refresh: getData,
            showLoc: function showLoc() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                // if (id);
                // alert(id);
            },
            toMonitor: function toMonitor(e) {
                var bid=$(e.target).attr('bid');
                // console.log($(e.target))
                location.href = getRouter("mOverview")+"?bid="+bid;
            }
        }
    })

    app.refresh();
})