$(function () {
    var vdata = {
        finishLoading: true, //异步请求的数据是否加载完毕
        title: getTitle("buildInfo") + "/" + getTitle(),
        data: commonPageData,
    }
    
    //TODO:page
    var getData = function () {
        $("#refresh").disabledButton()
        vdata.finishLoading = false;

        /*setTimeout(function () {
            vdata.data = getBuildDataMock();
            vdata.finishLoading = true;
        }, 200)*/

        
        $.ajax({
            url: AjaxReqUrl.building,
            method: 'get',
            dataType: 'json',
            data: {},
            success: function (data) {
                vdata.data = data
                vdata.finishLoading = true;

                $("#refresh").enableButton()
            },
            error: function (data, status, e) {
                console.log(data,status,e)
                $("#refresh").enableButton()
                vdata.finishLoading = true;
            }
        })
    }

    var app = new Vue({
        el: "#mainbox",
        data: vdata,
        mounted: function () {},
        methods: {
            refresh: getData,
            showLoc: function showLoc() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                if (id);
               // alert(id);
            },
            toDetail: function toDetail() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                if (id);
              //  alert(id);
            },
            toMap: function toMap() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                if (id);
               // alert(id);
            },
            toNetwork: function toNetWork() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                if (id);
               // alert(id);
            },
            toMonitor: function toMonitor() {
                var id = $(event.target).parent().siblings(".data-item-id").html();
                if (id);
                location.href = getRouter("mOverview")+"?bid="+id;
            },
        }
    })

    app.refresh();
})