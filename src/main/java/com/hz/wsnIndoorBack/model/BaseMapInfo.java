package com.hz.wsnIndoorBack.model;

import java.io.Serializable;
/**
 * 地图的底图信息
 * @author lenovo
 *
 */
@SuppressWarnings("serial")
public class BaseMapInfo implements Serializable{
	private int mapId; //地图id
	private String code; //空间参考标识符(例如ESPG:3857) 
	private String host;//地图服务器地址[:端口]
	private String serverType;//服务器类型(例如GeoServer,ArcServer)
	private String workspace;//GIS服务器的工作空间
	private String requestType;//请求类型(OGC)
	private String layers;//图层名
	private Double x_min;//地图范围-横向最小值
	private Double y_min;//地图范围-横向最大值
	private Double x_max;//地图范围-纵向最小值
	private Double y_max;//地图范围-纵向最大值
	private Double zoom_default;//地图默认缩放值
	private Double zoom_max;//地图最小缩放值
	private Double zoom_min;//地图最大缩放值
	
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getLayers() {
		return layers;
	}
	public void setLayers(String layers) {
		this.layers = layers;
	}
	public Double getX_min() {
		return x_min;
	}
	public void setX_min(Double x_min) {
		this.x_min = x_min;
	}
	public Double getY_min() {
		return y_min;
	}
	public void setY_min(Double y_min) {
		this.y_min = y_min;
	}
	public Double getX_max() {
		return x_max;
	}
	public void setX_max(Double x_max) {
		this.x_max = x_max;
	}
	public Double getY_max() {
		return y_max;
	}
	public void setY_max(Double y_max) {
		this.y_max = y_max;
	}
	public Double getZoom_default() {
		return zoom_default;
	}
	public void setZoom_default(Double zoom_default) {
		this.zoom_default = zoom_default;
	}
	public Double getZoom_max() {
		return zoom_max;
	}
	public void setZoom_max(Double zoom_max) {
		this.zoom_max = zoom_max;
	}
	public Double getZoom_min() {
		return zoom_min;
	}
	public void setZoom_min(Double zoom_min) {
		this.zoom_min = zoom_min;
	}
	
	
}
