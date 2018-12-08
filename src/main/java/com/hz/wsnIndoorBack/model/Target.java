package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
// target_id(主键),name(名字),type(类型 0:员工 1:访客 2:机器人 ),
// unit(单位),ID_card(身份证),mobile(手机),create_time,leave_time(离开的时间),remark(备注)
public class Target implements Serializable {
	private int targetId;
	private String name;
	private int bid;
	private int type;
	private String unit;
	private String idCard;
	private String mobile;
	private String createTime;
	private String leaveTime;
	private String remark;

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
