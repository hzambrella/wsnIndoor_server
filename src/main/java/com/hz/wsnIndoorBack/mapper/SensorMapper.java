package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Sensor;
import com.hz.wsnIndoorBack.model.SensorData;

public interface SensorMapper {
	// 不要分页
	public List<Sensor> getSensorsByNid(@Param("nid") Integer nid);

	public SensorData getLatestSensorDataBySid(int sid);

	public List<SensorData> getSensorDataBySid(@Param("sid") Integer sid,
			@Param("limit") Integer limit);

	public int insertSensorData(SensorData sensorData);
	public void insertSensorDatas(List<SensorData> sensorDatas);

	public int deleteSensorData(int sid);
}
