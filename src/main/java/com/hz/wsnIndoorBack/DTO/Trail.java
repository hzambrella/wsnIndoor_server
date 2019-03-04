package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 轨迹
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Trail implements Serializable {
	private String trailId; //轨迹id
	private int targetId; //目标id
	private int nid; //无线传感器网络id
	private List<Float[]>points; //轨迹点
	private String startTime; //开始时间
	private String endTime; //结束时间
	 
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
