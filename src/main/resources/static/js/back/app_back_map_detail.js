$(function () {
    var app = new Vue({
        el: "#app",
        data: {
            mapId: $("mapId").html(),
            finishLoading: true,
            title: '地图详情和修改',
            items: {},
        },
        methods: {

        }
    })

    getData(1); //首次加载页面时

    function getData(mapId) {
        app.finishLoading = false;
        //TODO:ajax
        setTimeout(function () {
            app.finishLoading = true
        }, 200)
    }
   
    var layers = [
        new ol.layer.Image({
            source: new ol.source.ImageWMS({
                //crossOrigin: 'anonymous',
                Layers:'hzmap:gdata_1_1_plane',
                serverType: 'geoserver',
                url: 'http://127.0.0.1:8083/geoserver/hzmap/wms'
            })
        })
    ];

    var projection = new ol.proj.Projection({
        code: 'EPSG:404000',
        units: 'm'
    });

    var map = new ol.Map({
        layers: layers,
        target: 'indoorMap',
        view: new ol.View({
            center: [66, 19],
            projection: projection,
            zoom: 9
        })
    });
    // var map = new ol.Map({
    //     target: 'indoorMap',
    //     layers: [
    //         planelayer
    //     ],
    //     view: new ol.View({
    //         projection: projectionBaseMap,
    //         //center: ol.extent.getCenter(extentBaseMap),
    //         zoom: 2,
    //         maxZoom: 5,
    //         minZoom: 1.2,
    //     }),
    //     // control: ol.control.defaults().extend([mousePositionControl])
    // });

    // var projectionBaseMap = new ol.proj.get("EPSG:404000");
    // var planelayer = new ol.layer.Image({
    //     source: new ol.source.ImageWMS({
    //         url: 'http://127.0.0.1:8080/geoserver/hzmap/wms', //这里添加静态图片的地址
    //         projection: projectionBaseMap,
    //         //imageExtent: extentBaseMap,
    //         // crossOrigin: 'anonymous'
    //     })
    // })
})