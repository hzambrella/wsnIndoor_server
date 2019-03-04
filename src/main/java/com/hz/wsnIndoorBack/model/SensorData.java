package com.hz.wsnIndoorBack.model;

import java.io.Serializable;

/**
 * 环境数据
 * @author haozhoa
 *
 */
@SuppressWarnings("serial")
public class SensorData implements Serializable{
	private Integer id;
	private Integer sid;
	private Float temperature; //温度
	private Integer humidity;//湿度
	private Integer lux;//光照
	private boolean flame;//是否有明火
	private boolean smog;//是否有烟雾
	private boolean poison;//是否有毒气
	private String createTime;
	
	public static final Integer DefaultLimit = 10;//列表数据限制默认值
	
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
