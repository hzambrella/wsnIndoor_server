package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 网关（汇聚节点）
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Sink implements Serializable {
	private Integer sinkId;
	private Integer status;//0-关闭 1-打开 2-尚未使用 3-废弃
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
