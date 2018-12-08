package com.hz.wsnIndoorBack.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class TP implements Serializable {
	private int targetId;
	private Integer[]point;
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public Integer[] getPoint() {
		return point;
	}
	public void setPoint(Integer[] point) {
		this.point = point;
	}
	
}
