package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 建筑和地图关系，用于应用中的楼层索引。
 * 
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class BuildMapRel implements Serializable {
	//private Integer bmId;
	private Integer bid;
	private Integer floor;
	private Integer mapId;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getMapId() {
		return mapId;
	}
	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	
}
