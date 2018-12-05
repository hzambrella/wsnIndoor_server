package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SensorData implements Serializable{
	public static final Integer LIMIT = 10;
	private Integer id;
	private Integer sid;
	private Float temperature;
	private Integer humidity;
	private Integer lux;
	private boolean flame;
	private boolean smog;
	private boolean poison;
	private String createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Float getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Integer getLux() {
		return lux;
	}
	public void setLux(Integer lux) {
		this.lux = lux;
	}
	public boolean isFlame() {
		return flame;
	}
	public void setFlame(boolean flame) {
		this.flame = flame;
	}
	public boolean isSmog() {
		return smog;
	}
	public void setSmog(boolean smog) {
		this.smog = smog;
	}
	public boolean isPoison() {
		return poison;
	}
	public void setPoison(boolean poison) {
		this.poison = poison;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
