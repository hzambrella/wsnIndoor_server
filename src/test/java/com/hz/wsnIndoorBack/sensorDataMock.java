package com.hz.wsnIndoorBack;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hz.wsnIndoorBack.mapper.SensorMapper;
import com.hz.wsnIndoorBack.model.Sensor;
import com.hz.wsnIndoorBack.model.SensorData;

@RunWith(SpringRunner.class)
@SpringBootTest
// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class sensorDataMock {
	@Autowired
	private SensorMapper sensorMapper;

	// 生成测试数据。不需要mock数据时，不要取消@Test的注释。
	// @Test
	public void createSensorDataMock() {
		int nid = 1;
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
		// 随机出现险情的点
		int fire = random.nextInt(len);
		int smog = random.nextInt(len);
		int poison = random.nextInt(len);
		int index = 0;

		for (Sensor sensor : sensors) {
			for (int i = 0; i < 3; i++) {
				SensorData sensorData = new SensorData();
				sensorData.setSid(sensor.getSid());
				if (smog == index && i == 0) {
					sensorData.setSmog(true);
				} else {
					sensorData.setSmog(false);
				}
				// 火灾时有烟雾
				if (fire == index && i == 0) {
					sensorData.setFlame(true);
					sensorData.setSmog(true);
				} else {
					sensorData.setFlame(false);
				}

				if (poison == index && i == 0) {
					sensorData.setPoison(true);
				} else {
					sensorData.setPoison(false);
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
