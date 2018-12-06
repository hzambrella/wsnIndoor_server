package com.hz.wsnIndoorBack.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hz.wsnIndoorBack.mapper.SensorMapper;
import com.hz.wsnIndoorBack.model.Sensor;
import com.hz.wsnIndoorBack.model.SensorData;

@RestController
@RequestMapping("/wsnIndoorBackMockData")
public class BackDataMockController {
	@Autowired
	private SensorMapper sensorMapper;

	@GetMapping("/sensorData")
	public void createSensorDataMock(@RequestParam(value = "nid") Integer nid) {
		List<Sensor> sensors = sensorMapper.getSensorsByNid(nid);
		sensorMapper.deleteSensorData(0);
		Random random = new Random();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		String[] createTime = new String[3];
		for (int i = 0; i < 3; i++) {
			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
			createTime[i] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")
					.format(calendar.getTime());
		}

		int len = sensors.size();
		int fire = random.nextInt(len);
		int smog = random.nextInt(len);
		int poison = random.nextInt(len);
		int index = 0;
		for (Sensor sensor : sensors) {
			for (int i = 0; i < 3; i++) {
				SensorData sensorData = new SensorData();
				sensorData.setSid(sensor.getSid());
				if (fire == index && i == 0) {
					sensorData.setFlame(true);
				} else {
					sensorData.setFlame(false);
				}

				if (poison == index && i == 0) {
					sensorData.setPoison(true);
				} else {
					sensorData.setPoison(false);
				}

				if (smog == index && i == 0) {
					sensorData.setSmog(true);
				} else {
					sensorData.setSmog(false);
				}

				sensorData.setLux(5 + random.nextInt(10));
				sensorData.setTemperature((float) (5 + random.nextInt(5)));
				sensorData.setHumidity(45 + random.nextInt(5));
				sensorData.setCreateTime(createTime[i]);
				sensorMapper.insertSensorData(sensorData);
			}
			index++;
		}
	}
}
