package com.hz.wsnIndoorBack.model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Network implements Serializable{
	private Integer nid;
	private Integer bid;
	private Integer floor;
	private Integer comStatus;
	private Integer anchorStatus;
	private Integer coorId;
	
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
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
	public Integer getComStatus() {
		return comStatus;
	}
	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}
	public Integer getAnchorStatus() {
		return anchorStatus;
	}
	public void setAnchorStatus(Integer anchorStatus) {
		this.anchorStatus = anchorStatus;
	}
	public Integer getCoorId() {
		return coorId;
	}
	public void setCoorId(Integer coorId) {
		this.coorId = coorId;
	}
	
	
}
