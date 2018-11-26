package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sink implements Serializable {
	private Integer sinkId;
	private Integer status;
	private String sn;
	private Integer bid;
	public Integer getSinkId() {
		return sinkId;
	}
	public void setSinkId(Integer sinkId) {
		this.sinkId = sinkId;
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
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
}
