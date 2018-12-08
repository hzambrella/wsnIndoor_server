package com.hz.wsnIndoorBack;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.hz.wsnIndoorBack.mapper.BuildMapper;
import com.hz.wsnIndoorBack.mapper.TargetMapper;
import com.hz.wsnIndoorBack.model.Building;
import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.model.TrailPoint;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TargetDataMock {
	@Autowired
	private BuildMapper buildMapper;
	@Autowired
	private TargetMapper targetMapper;
	private int nid = 1;
	private int targetIdInSimu = 1;
	private int assertDateSizeInSimu=20;
	private String dateMockDay = "2018-12-1";
	private String dateMock = dateMockDay + "-11:50:06";
	private int frequence = 10;// 采样时间

	// 生成测试数据。不需要mock数据时，不要取消@Test的注释。
	@Test
	public void createTrailData() throws ParseException {
		List<TrailPoint> trailPoints = targetMapper.getTrailPointsInMockData(
				nid, targetIdInSimu);
		Assert.assertEquals(trailPoints.size(), assertDateSizeInSimu);
		// mock时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date date = sdf.parse(dateMock);
		String inTime=sdf.format(date);//进入时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		//进入时间
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 4);
		
		// 第一个轨迹
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		for (TrailPoint point : trailPoints) {
			if (point.getTrailId().equals("1")) {
				point.setTrailId(uuid);
				point.setCreateTime(sdf.format(calendar.getTime()));
				calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)
						+ frequence);
			}
		}

		// 假设间隔了spaceTime分钟
		int spaceTime = 3;
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + spaceTime);
		// 第二个轨迹
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		for (TrailPoint point : trailPoints) {
			if (point.getTrailId().equals("2")) {
				point.setTrailId(uuid);
				point.setCreateTime(sdf.format(calendar.getTime()));
				calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)
						+ frequence);
			}
		}
		
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 4);
		String outTime=sdf.format(calendar.getTime());//出去时间
		System.out.println(JSON.toJSON(trailPoints));
		// 生成轨迹表
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd");
		String suffix = sdf2.format(date);
		targetMapper.createTrailPointTableMock(nid, suffix);
		
		//插入目标数据
		Building building=buildMapper.getBuildByNid(nid);
		Target target=new Target();
		target.setTargetId(trailPoints.get(0).getTargetId());
		target.setMobile("13456731234");
		target.setIdCard("43232319890203123X");
		target.setName("张三");
		target.setRemark("美团外卖");
		target.setType(1);
		target.setBid(building.getBid());
		target.setUnit("美团外卖");
		target.setCreateTime(inTime);
		target.setLeaveTime(outTime);
		targetMapper.deleteTarget(target.getTargetId());
		targetMapper.registTarget(target);
		
		//插入轨迹数据
		for (TrailPoint point : trailPoints) {
			targetMapper.insertTrailPoint(point, nid, suffix);
		}
		
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd-HH");
		String startTime = sdf3.format(sdf.parse(inTime));
		String endTime=sdf3.format(sdf.parse(outTime));
		List<Target>targets=targetMapper.getTargetsByNidAndTime(nid, startTime, endTime);
		System.out.println(JSON.toJSON(targets));
	}
}
