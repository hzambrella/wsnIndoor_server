package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 地图
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Map implements Serializable{
	private int mapId;
	private String title;//地图标题
	private int status;//状态 0:未发布 1:已发布 2:审核中3:已锁定
	private String descrip;//介绍
	private String createTime;
	private String updateTime;
	private int floor; //对应楼层
	
	//TODO 修改为最低高度和最高高度
	private int height;
	private String buildName; //对应楼宇的名字
	private int bid; //对应楼宇
	
	//列表数据限制
	public static final int Limit = 10;

	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
}
