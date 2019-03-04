package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 楼宇
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class Building implements Serializable{
	private int bid;  //楼宇id
	private String Name; //楼宇名
	private Double X; //经度
	private Double Y; //纬度
	private String descrip; //介绍
	private String address; //地址
	
	//TODO 修改字段：最低高度 最高高度
	private int height; //高度
	
	public static int DefaultLimit=10;//列表数据限制默认值
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Double getX() {
		return X;
	}
	public void setX(Double x) {
		X = x;
	}
	public Double getY() {
		return Y;
	}
	public void setY(Double y) {
		Y = y;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
