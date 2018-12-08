package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrailPoint implements Serializable {
	private int targetId;
	private String trailId;
	private int x;
	private int y;
	private int nid;
	private String createTime;
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public String getTrailId() {
		return trailId;
	}
	public void setTrailId(String trailId) {
		this.trailId = trailId;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
