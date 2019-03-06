$(function () {
    var defaultGisData = {
        code: '',
        host: '',
        serverType: '',
        workspace: '',
        requestType: '',
        layers: '',
        x_min: 0,
        y_min: 0,
        x_max: 0,
        y_max: 0,
        zoom_default: 0,
        zoom_max: 0,
        zoom_min: 0,
    }

    var defaultMapDetail = {
        "mapId": 0,
        "title": "",
        "status": 1,
        "descrip": "",
        "createTime": "",
        "updateTime": "",
        "floor": 1,
        "height": 1,
        "buildName": "",
        "bid": 0
    }

    var app = new Vue({
        el: "#mainbox",
        data: {
            mapId: $("mapId").html(),
            finishLoading: true,
            title: '地图详情和修改',
            items: {},
            baseMapData: defaultGisData,
            mapDetail: defaultMapDetail,
            floor: [],
            GmapParamMeanMap: GmapParamMeanMap,
        },
        methods: {
            back: routerBack
        }
    })

    //TODO:根据mapID，目前写死的。
    getData(1); //根据mapID加载数据地图

    function getData(mapId) {
        app.finishLoading = false;
        /*setTimeout(function () {
            app.mapDetail = getMapDetailMock().obj
            //右边信息栏，显示第一个标签页
            $('#mapDataTab li:eq(0) a').tab('show');
            floorButton();
            getBaseMapData(mapId)
        }, 20)*/

        $.ajax({
            url: AjaxReqUrl.mapDetail,
            method: 'get',
            dataType: 'json',
            data: {mapId:mapId},
            success: function (data) {
                app.mapDetail = data
                 //右边信息栏，显示第一个标签页
                $('#mapDataTab li:eq(0) a').tab('show');
                floorButton();
                getBaseMapData(mapId)
            },
            error: function (data, status, e) {
                console.log(data, status, e)
                $("#refresh").enableButton()
                app.finishLoading = true;
            }
        })
    }

    function getBaseMapData(mapId) {
        /*setTimeout(function () {
            app.baseMapData = getBaseMapDataMock(mapId).obj;
            loadMap(mapId, app.baseMapData)
            app.finishLoading = true
        }, 20)*/
        $.ajax({
            url: AjaxReqUrl.baseMap,
            method: 'get',
            dataType: 'json',
            data: {mapId:mapId},
            success: function (data) {
                app.baseMapData =data.obj;
                loadMap(mapId, app.baseMapData)
                app.finishLoading = true
            },
            error: function (data, status, e) {
                console.log(data, status, e)
                $("#refresh").enableButton()
                app.finishLoading = true;
            }
        })
    }

    function floorButton() {
        floor = new Array();
        for (i = app.mapDetail.height; i > 0; i--) {
            floor.push(i + "F");
        }
        app.floor = floor
    }

    // function loadMap() {
    //     var mapMess = getBaseMapMock().obj;
    //     var url = mapMess.host + '/' + mapMess.serverType + '/' + mapMess.workspace + '/' + mapMess.requestType;
    //     var layersName = mapMess.workspace + ":" + mapMess.layers
    //     var extentBaseMap = [mapMess.x_min, mapMess.y_min, mapMess.x_max, mapMess.y_max];

    //     var projectionBaseMap = new ol.proj.Projection({
    //         code: mapMess.code,
    //         units: 'pixels',
    //         // extent: extentBaseMap,
    //         extent: extentBaseMap,
    //     });

    //     var map = new ol.Map({
    //         target: 'indoorMap',
    //         layers: [
    //             new ol.layer.Image({
    //                 source: new ol.source.ImageWMS({
    //                     url: url,
    //                     projection: projectionBaseMap,
    //                     imageExtent: extentBaseMap,
    //                     serverType: mapMess.serverType,
    //                     params: {
    //                         'layers': layersName,
    //                     },
    //                     // crossOrigin: 'anonymous'
    //                 })
    //             }),
    //         ],
    //         view: new ol.View({
    //             projection: projectionBaseMap,
    //             center: ol.extent.getCenter(extentBaseMap),
    //             zoom: mapMess.zoom_default,
    //             maxZoom: mapMess.zoom_max,
    //             minZoom: mapMess.zoom_min,
    //         }),
    //         // control: ol.control.defaults().extend([mousePositionControl])
    //     });
    //     return mapMess;
    // }
})