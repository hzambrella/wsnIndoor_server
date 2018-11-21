package com.hz.wsnIndoorBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.mapper.BuildMapper;
import com.hz.wsnIndoorBack.mapper.MapMapper;
import com.hz.wsnIndoorBack.model.BaseMapInfo;
import com.hz.wsnIndoorBack.model.Building;
import com.hz.wsnIndoorBack.model.Map;

@RestController
@RequestMapping("/wsnIndoorBackData")
public class BackDataController {
	@Autowired
	private BuildMapper buildMapper;
	@Autowired
	private MapMapper mapMapper;

	@GetMapping("/building")
	public Result<PageInfo<Building>> building(
			@RequestParam(value = "page",required=false) Integer page) {
		if (page==null||page==0){
			page=1;
		}
		
		PageHelper.startPage(page,Building.Limit);
		List<Building> buildings = buildMapper.getBuildings();
		PageInfo<Building> pageInfo = new PageInfo<>(buildings);
		Result<PageInfo<Building>> result = new Result<>(pageInfo);
		return result;
	}

	@GetMapping("/map")
	public Result<PageInfo<Map>> map(@RequestParam(value = "page",required=false) Integer page){
		if (page==null||page==0){
			page=1;
		}
		PageHelper.startPage(page,Map.Limit);
		List<Map> maps = mapMapper.getMaps();
		PageInfo<Map> pageInfo = new PageInfo<>(maps);
		Result<PageInfo<Map>> result = new Result<>(pageInfo);
		return result;
	}
	
	@GetMapping("/mapDetail")
	public Result<Map> mapDetail(@RequestParam(value = "mapId") Integer mapId){
		Map map = mapMapper.getMapById(mapId);
		Result<Map> result = new Result<>(map);
		return result;
	}
	
	@GetMapping("/baseMap")
	public Result<BaseMapInfo> baseMap(@RequestParam(value = "mapId") Integer mapId){
		BaseMapInfo baseMap= mapMapper.getBaseMapInfo(mapId);
		Result<BaseMapInfo> result = new Result<>(baseMap);
		return result;
	}
}
