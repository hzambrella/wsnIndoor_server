package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrailPoint implements Serializable {
	private int targetId;
	private String trailId;
	private float x;
	private float y;
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
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
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
