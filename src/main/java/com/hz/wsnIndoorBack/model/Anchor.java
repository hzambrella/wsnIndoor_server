package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Anchor implements Serializable {
	private Integer anchorId;
	private Integer nid;
	private Integer bid;
	private Integer floor;
	private Integer anchorType;
	private Integer status;
	private Integer x;
	private Integer y;
	private String sn;
	
	public Integer getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}
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
	public Integer getAnchorType() {
		return anchorType;
	}
	public void setAnchorType(Integer anchorType) {
		this.anchorType = anchorType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}

}
