package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Trail implements Serializable {
	private int trailId;
	private int targetId;
	private int nid;
	private Float[][]points;
	private String startTime;
	private String endTime;
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getTrailId() {
		return trailId;
	}
	public void setTrailId(int trailId) {
		this.trailId = trailId;
	}
	public Float[][] getPoints() {
		return points;
	}
	public void setPoints(Float[][] points) {
		this.points = points;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
