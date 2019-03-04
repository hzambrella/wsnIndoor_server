package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 点
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Point implements Serializable {
	private Double x;
	private Double y;
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}
