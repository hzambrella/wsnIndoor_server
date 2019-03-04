package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 无线传感器网络
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Network implements Serializable{
	public static Integer Limit=10;
	private Integer nid;
	private Integer bid;
	private Integer floor;
	private Integer coorId;
	private Integer comStatus;//通信状态 0：关闭(维护中) 1：打开 2：异常。
	/**
	 * 锚节点布设任务的阶段
	 * 0.新建网络（已存在时不可新建。添加楼层的同时，在数据库的buid_network表中添加一行数据）=>
	 * 1.布设硬件（填写协调器的coor_id）=》 2.组网(com_status设置为1)-》
	 * 3.标定关键锚节点(anchor表中，输入部分锚节点的坐标)=》 4.洪范（向该网络发起洪范请求，返回关键锚节点间的跳距）=》
	 * 5.训练（普通的锚节点发送跳距，得到坐标）=》 6.完成构建（此时才能进行定位监控）
	 */
	private Integer anchorStatus;
	/**
	 * 锚节点布设任务的状态
	 * 0：否 1：是 2：任务失败了
	 */
	private Integer anchorTaskStatus;
	private Coordinator coordinator;//协调器
	
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
	
	public Integer getCoorId() {
		return coorId;
	}
	public void setCoorId(Integer coorId) {
		this.coorId = coorId;
	}
	public Integer getComStatus() {
		return comStatus;
	}
	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}
	public Integer getAnchorStatus() {
		return anchorStatus;
	}
	public void setAnchorStatus(Integer anchorStatus) {
		this.anchorStatus = anchorStatus;
	}
	public Coordinator getCoordinator() {
		return coordinator;
	}
	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}
	public Integer getAnchorTaskStatus() {
		return anchorTaskStatus;
	}
	public void setAnchorTaskStatus(Integer anchorTaskStatus) {
		this.anchorTaskStatus = anchorTaskStatus;
	}
	
}
