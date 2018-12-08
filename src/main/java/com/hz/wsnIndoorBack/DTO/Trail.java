package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Trail implements Serializable {
	private String trailId;
	private int targetId;
	private int nid;
	private List<Float[]>points;
	private String startTime;
	private String endTime;
	
	
	public Trail() {
		super();
	}
	
	public Trail(int targetId,String trailId,int nid) {
		this.targetId=targetId;
		this.trailId=trailId;
		this.nid=nid;
		this.points=new ArrayList<Float[]>();
	}
	
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
	
	public String getTrailId() {
		return trailId;
	}

	public List<Float[]> getPoints() {
		return points;
	}

	public void setPoints(List<Float[]> points) {
		this.points = points;
	}

	public void setTrailId(String trailId) {
		this.trailId = trailId;
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
