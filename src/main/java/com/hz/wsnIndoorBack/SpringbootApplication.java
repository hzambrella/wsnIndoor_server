package com.hz.wsnIndoorBack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * SpringBoot启动类
 * @author xcbeyond
 * 2018年7月2日下午5:41:45
 */
@SpringBootApplication
//指定要扫描的Mapper类的包的路径
@MapperScan("com.hz.wsnIndoorBack.mapper")
public class SpringbootApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
