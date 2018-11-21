var router = {
    "buildInfo": "back_building.html",
    "mapInfo": "back_map.html",
    "netInfo": "",
    "monitorInfo": "",
    "mapdetail": "back_mapdetail.html",
}

function getRouter(pageId){
    return router[pageId]
}

//左侧导航栏
$(function () {
    $("#leftNav").on('click', function (event) {
        var $target = $(event.target);
        var h = getRouter($target.prop("id"));
        if (h && h != '') {
            location.href = h
        }
    })
})