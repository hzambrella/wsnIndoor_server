package com.hz.wsnIndoorBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hz.wsnIndoorBack.mapper.BuildMapper;
import com.hz.wsnIndoorBack.model.Building;

@RestController
public class RestControllerDemo {
	@Autowired
	private BuildMapper buildMapper;
	
	@RequestMapping("/print")
	public String print() {
		return "hello SpringBoot!";
	}
	
	@RequestMapping("/sql")
	public List<Building> sql() {
		List<Building>buildings=buildMapper.getBuildings();
		return buildings;
	}
}