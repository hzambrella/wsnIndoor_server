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
import com.hz.wsnIndoorBack.DTO.NetComDev;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.mapper.BuildMapper;
import com.hz.wsnIndoorBack.mapper.MapMapper;
import com.hz.wsnIndoorBack.mapper.NetMapper;
import com.hz.wsnIndoorBack.mapper.SensorMapper;
import com.hz.wsnIndoorBack.model.Anchor;
import com.hz.wsnIndoorBack.model.BaseMapInfo;
import com.hz.wsnIndoorBack.model.BuildMapRel;
import com.hz.wsnIndoorBack.model.Building;
import com.hz.wsnIndoorBack.model.Map;
import com.hz.wsnIndoorBack.model.Network;
import com.hz.wsnIndoorBack.model.Sensor;
import com.hz.wsnIndoorBack.model.SensorData;
import com.hz.wsnIndoorBack.service.INetService;

@RestController
@RequestMapping("/wsnIndoorBackData")
public class BackDataController {
	@Autowired
	private BuildMapper buildMapper;
	@Autowired
	private MapMapper mapMapper;
	@Autowired
	private NetMapper netMapper;
	@Autowired
	private SensorMapper sensorMapper;
	@Autowired
	private INetService netService;

	@GetMapping("/building")
	public Result<PageInfo<Building>> building(
			@RequestParam(value = "page", required = false) Integer page) {
		if (page == null || page == 0) {
			page = 1;
		}

		PageHelper.startPage(page, Building.Limit);
		List<Building> buildings = buildMapper.getBuildings();
		PageInfo<Building> pageInfo = new PageInfo<>(buildings);
		Result<PageInfo<Building>> result = new Result<>(pageInfo);
		return result;
	}

	@GetMapping("/map")
	public Result<PageInfo<Map>> map(
			@RequestParam(value = "page", required = false) Integer page) {
		if (page == null || page == 0) {
			page = 1;
		}
		PageHelper.startPage(page, Map.Limit);
		List<Map> maps = mapMapper.getMaps();
		PageInfo<Map> pageInfo = new PageInfo<>(maps);
		Result<PageInfo<Map>> result = new Result<>(pageInfo);
		return result;
	}

	@GetMapping("/mapDetail")
	public Result<Map> mapDetail(@RequestParam(value = "mapId") Integer mapId) {
		Map map = mapMapper.getMapById(mapId);
		Result<Map> result = new Result<>(map);
		return result;
	}

	@GetMapping("/baseMap")
	public Result<BaseMapInfo> baseMap(
			@RequestParam(value = "mapId") Integer mapId) {
		BaseMapInfo baseMap = mapMapper.getBaseMapInfo(mapId);
		Result<BaseMapInfo> result = new Result<>(baseMap);
		return result;
	}

	@GetMapping("/netComDev")
	public Result<NetComDev> netComDev(@RequestParam(value = "bid") Integer bid) {
		return netService.getNetComDevByBid(bid);
	}

	@GetMapping("/buildMapRel")
	public Result<List<BuildMapRel>> anchors(
			@RequestParam(value = "bid") Integer bid) {
		List<BuildMapRel> buildMapRel = mapMapper.getBuildMapRel(bid);
		Result<List<BuildMapRel>> result = new Result<>(buildMapRel);
		return result;
	}

	// 网络状况，包括通讯状态，锚节点布设状态
	@GetMapping("/network")
	public Result<Network> network(@RequestParam(value = "bid") Integer bid,
			@RequestParam(value = "floor") Integer floor) {
		Network network = netMapper.getNetwork(bid, floor);
		Result<Network> result = new Result<>(network);
		return result;
	}

	@GetMapping("/networks")
	public Result<PageInfo<Network>> networks(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "bid") Integer bid) {
		if (page == null || page == 0) {
			page = 1;
		}
		PageHelper.startPage(page, Network.Limit);
		List<Network> networks = netMapper.getNetworks(bid);
		PageInfo<Network> pageInfo = new PageInfo<>(networks);
		Result<PageInfo<Network>> result = new Result<>(pageInfo);
		return result;
	}

	@GetMapping("/anchors")
	public Result<List<Anchor>> anchors(
			@RequestParam(value = "bid") Integer bid,
			@RequestParam(value = "floor") Integer floor,
			@RequestParam(value = "anchorType", required = false) Integer anchorType) {
		List<Anchor> anchors = netMapper.getAnchors(bid, floor, anchorType);
		Result<List<Anchor>> result = new Result<>(anchors);
		return result;
	}

	@GetMapping("/sensors")
	public Result<List<Sensor>> sensors(@RequestParam(value = "nid") Integer nid) {
		List<Sensor> sensors = sensorMapper.getSensorsByNid(nid);
		Result<List<Sensor>> result = new Result<>(sensors);
		return result;
	}

	@GetMapping("/sensor/latest")
	public Result<SensorData> sensorLatestData(
			@RequestParam(value = "sid") Integer sid) {
		SensorData data = sensorMapper.getLatestSensorDataBySid(sid);
		Result<SensorData> result = new Result<>(data);
		return result;
	}

	@GetMapping("/sensor/data")
	public Result<List<SensorData>> sensorData(
			@RequestParam(value = "sid") Integer sid,
			@RequestParam(value = "limit", required = false) Integer limit) {
		if (limit == null) {
			limit = SensorData.LIMIT;
		}
		List<SensorData> data = sensorMapper.getSensorDataBySid(sid, limit);
		Result<List<SensorData>> result = new Result<>(data);
		return result;
	}
}
