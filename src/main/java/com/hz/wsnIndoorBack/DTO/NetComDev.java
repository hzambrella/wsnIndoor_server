package com.hz.wsnIndoorBack.DTO;

import java.io.Serializable;
import java.util.List;

import com.hz.wsnIndoorBack.model.Coordinator;
import com.hz.wsnIndoorBack.model.Sink;

@SuppressWarnings("serial")
public class NetComDev implements Serializable{
	private List<Coordinator>coordinators;
	private List<Sink>sinks;
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
