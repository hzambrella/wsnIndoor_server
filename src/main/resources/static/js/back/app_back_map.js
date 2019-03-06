$(function () {
    //var pageId = $("#pageId").html();
    var app = new Vue({
        el: "#mainbox",
        data: {
            finishLoading: true,
            title: '地图管理',
            data: commonPageData,
        },
        methods: {
            showLoc: function showLoc() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                alert("正在开发中..."+id);
            },
         
            toDetail: function toDetail() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                //alert(id);
                //TODO:根据mapID获取地图。目前mapID是写死的1
                location.href = getRouter("mapdetail");
            },
            deleteMap: function toMonitor() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                alert("正在开发中..."+id);
            },

        }
    })

    getData(1); //首次加载页面时.从第一页开始
    //TODO:page
    function getData(page) {
        app.finishLoading = false;
        /*setTimeout(function () {
            app.data = getMapDataMock();
            app.finishLoading = true;
        }, 200)*/

        $.ajax({
            url: AjaxReqUrl.map,
            method: 'get',
            dataType: 'json',
            data: {},
            success: function (data) {
                app.data = data;
                app.finishLoading = true;
            },
            error: function (data, status, e) {
                console.log(data, status, e)
                app.finishLoading = true;
            }
        })
    }

})