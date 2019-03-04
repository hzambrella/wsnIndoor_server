package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Sensor;
import com.hz.wsnIndoorBack.model.SensorData;

public interface SensorMapper {
	// 不要分页
	public List<Sensor> getSensorsByNid(@Param("nid") Integer nid);//获取环境设备
	public SensorData getLatestSensorDataBySid(int sid);//获取某环境设备的最新数据

	public List<SensorData> getSensorDataBySid(@Param("sid") Integer sid,
			@Param("limit") Integer limit);//获取环境设备的历史数据

	public int insertSensorData(SensorData sensorData);//插入一条环境数据
	public void insertSensorDatas(List<SensorData> sensorDatas);//插入多条环境数据

	public int deleteSensorData(int sid);//删除某个环境设备所有的环境数据
}
