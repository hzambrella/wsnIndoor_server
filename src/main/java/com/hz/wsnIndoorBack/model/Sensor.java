package com.hz.wsnIndoorBack.model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Sensor implements Serializable {
	private Integer sid;
	private String sn;
	private Integer nid;
	private Integer status;
	private Integer energy;
	private Integer sensorType;
	private boolean hasLocation;
	private Float x;
	private Float y;
	private String createTime;
	private String updateTime;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public Integer getSensorType() {
		return sensorType;
	}
	public void setSensorType(Integer sensorType) {
		this.sensorType = sensorType;
	}
	public boolean isHasLocation() {
		return hasLocation;
	}
	public void setHasLocation(boolean hasLocation) {
		this.hasLocation = hasLocation;
	}
	
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
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
}
