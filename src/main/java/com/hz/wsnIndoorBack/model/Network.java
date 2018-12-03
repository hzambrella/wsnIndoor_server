package com.hz.wsnIndoorBack.model;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Network implements Serializable{
	public static Integer Limit=10;
	private Integer nid;
	private Integer bid;
	private Integer floor;
	private Integer coorId;
	private Integer comStatus;
	private Integer anchorStatus;
	private Integer anchorTaskStatus;
	private Coordinator coordinator;
	
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
	
	public Integer getCoorId() {
		return coorId;
	}
	public void setCoorId(Integer coorId) {
		this.coorId = coorId;
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
	public Coordinator getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	public Integer getAnchorTaskStatus() {
		return anchorTaskStatus;
	}
	public void setAnchorTaskStatus(Integer anchorTaskStatus) {
		this.anchorTaskStatus = anchorTaskStatus;
	}
	
}
