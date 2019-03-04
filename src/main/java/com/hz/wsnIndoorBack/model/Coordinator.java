package com.hz.wsnIndoorBack.model;

import java.io.Serializable;
/**
 * 协调器
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Coordinator implements Serializable {
	private Integer coorId;
	private Integer status;// 0-关闭 1-打开 2-废弃
	private String sn;
	private Integer bid;
	private Integer nid;
	private Integer floor;
	private String panId;//PANID
	
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public Integer getCoorId() {
		return coorId;
	}
	public void setCoorId(Integer coorId) {
		this.coorId = coorId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public String getPanId() {
		return panId;
	}
	public void setPanId(String panId) {
		this.panId = panId;
	}
}

