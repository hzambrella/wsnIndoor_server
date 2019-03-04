package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;
import java.util.List;

import com.hz.wsnIndoorBack.model.Coordinator;
import com.hz.wsnIndoorBack.model.Sink;

/**
 * 无线传感器连接设备
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class NetComDev implements Serializable{
	private List<Coordinator>coordinators;//协调器
	private List<Sink>sinks;//网关
	
	public List<Coordinator> getCoordinators() {
		return coordinators;
	}
	public void setCoordinators(List<Coordinator> coordinators) {
		this.coordinators = coordinators;
	}
	public List<Sink> getSinks() {
		return sinks;
	}
	public void setSinks(List<Sink> sinks) {
		this.sinks = sinks;
	}
	
}
