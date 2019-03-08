package com.hz.wsnIndoorBack;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.DTO.Trail;
import com.hz.wsnIndoorBack.mapper.TargetMapper;
import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.service.ITargetService;

@RunWith(SpringRunner.class)
@SpringBootTest
// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TestService {
	@Autowired
	private TargetMapper targetMapper;
	@Autowired
	private ITargetService targetService;
	@Test
	public void test(){
//		List<TP>tp=targetMapper.test(2018120111);
//		System.out.println(tp.size());
//		System.out.println(JSON.toJSON(tp));
		List<Target>targets=targetMapper.getTargetsByNidAndTime(1, "2018-12-01-10", "2018-12-01-13");
		System.out.println(JSON.toJSON(targets));
		
		Result<List<Trail>>result=targetService.getTrailsByTargetId(2018120112);
		System.out.println(JSON.toJSON(result));
	}
}
