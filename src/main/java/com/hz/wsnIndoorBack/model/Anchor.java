package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 锚节点
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Anchor implements Serializable {
	private Integer anchorId;//锚节点id
	private Integer nid;//无线网络id
	private Integer bid;//楼宇id
	private Integer floor;//楼层
	private Integer anchorType; //锚节点类型 和数据库一致 1.关键锚节点 0.普通锚节点
	private Integer status;//状态 和数据库一致 0:关闭 1:打开 2:故障 3:停用';
	private Float x;//横坐标
	private Float y;//纵坐标
	private String sn;//序列号
	private Integer energy;//能量
	private String createTime;
	private String updateTime;
	/**
	 * 关键锚节点 anchorType=1
	 */
	public static int KeyAnchor=1; 
	/**
	 * 普通锚节点 anchorType=0;
	 */
	public static int NormalAnchor=0;
	
	/**
	 * 锚节点关闭
	 */
	public static int Close=0;
	/**
	 * 锚节点打开
	 */
	public static int Open=1;
	/**
	 * 锚节点故障
	 */
	public static int Breakdown=2;
	/**
	 * 锚节点废弃停用
	 */
	public static int Discard=3;
	
	public Integer getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(Integer anchorId) {
		this.anchorId = anchorId;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getAnchorType() {
		return anchorType;
	}
	public void setAnchorType(Integer anchorType) {
		this.anchorType = anchorType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getEnergy() {
		return energy;
	}
	public void setEnergy(Integer energy) {
		this.energy = energy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	

}
