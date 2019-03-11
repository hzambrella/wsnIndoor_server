package com.hz.wsnIndoorBack.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.wsnIndoorBack.DTO.NetComDev;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.DTO.Trail;
import com.hz.wsnIndoorBack.libSVMUtils.model.LearningParam;
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
import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.service.INetService;
import com.hz.wsnIndoorBack.service.ITargetService;

//TODO:日志系统,权限系统
/**
 * 
 * @author haozhoa 数据
 */
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
	@Autowired
	private ITargetService targetService;

	/**
	 * 楼宇列表
	 * 
	 * @param page
	 *            页
	 * @return
	 */
	@GetMapping("/building")
	public Result<PageInfo<Building>> building(
			@RequestParam(value = "page", required = false) Integer page) {
		if (page == null || page == 0) {
			page = 1;
		}

		PageHelper.startPage(page, Building.DefaultLimit);
		List<Building> buildings = buildMapper.getBuildings();
		PageInfo<Building> pageInfo = new PageInfo<>(buildings);
		Result<PageInfo<Building>> result = new Result<>(pageInfo);
		return result;
	}

	/**
	 * 地图列表
	 * 
	 * @param page
	 *            页
	 * @return
	 */
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

	/**
	 * 地图详情
	 * 
	 * @param mapId
	 *            地图id
	 * @return
	 */
	@GetMapping("/mapDetail")
	public Result<Map> mapDetail(@RequestParam(value = "mapId") Integer mapId) {
		Map map = mapMapper.getMapById(mapId);
		Result<Map> result = new Result<>(map);
		return result;
	}

	/**
	 * 底图
	 * 
	 * @param mapId
	 * @return
	 */
	@GetMapping("/baseMap")
	public Result<BaseMapInfo> baseMap(
			@RequestParam(value = "mapId") Integer mapId) {
		BaseMapInfo baseMap = mapMapper.getBaseMapInfo(mapId);
		Result<BaseMapInfo> result = new Result<>(baseMap);
		return result;
	}

	/**
	 * 获取无线传感器连接设备列表
	 * 
	 * @param bid
	 *            楼宇id
	 * @return
	 */
	@GetMapping("/netComDev")
	public Result<NetComDev> netComDev(@RequestParam(value = "bid") Integer bid) {
		return netService.getNetComDevByBid(bid);
	}

	/**
	 * 获取建筑楼层和地图映射关系，用于应用中的楼层索引。
	 * 
	 * @param bid
	 *            楼宇id
	 * @return
	 */
	@GetMapping("/buildMapRel")
	public Result<List<BuildMapRel>> anchors(
			@RequestParam(value = "bid") Integer bid) {
		List<BuildMapRel> buildMapRel = mapMapper.getBuildMapRel(bid);
		Result<List<BuildMapRel>> result = new Result<>(buildMapRel);
		return result;
	}

	/**
	 * 网络状况，包括通讯状态，锚节点布设状态
	 * 
	 * @param bid
	 *            楼宇id
	 * @param floor
	 *            楼层
	 * @return
	 */
	@GetMapping("/network")
	public Result<Network> network(@RequestParam(value = "bid") Integer bid,
			@RequestParam(value = "floor") Integer floor) {
		Network network = netMapper.getNetwork(bid, floor);
		Result<Network> result = new Result<>(network);
		return result;
	}

	/**
	 * 网络状况列表
	 * 
	 * @param page
	 *            页数
	 * @param bid
	 *            楼宇id
	 * @return
	 */
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

	/**
	 * 锚节点
	 * 
	 * @param bid
	 *            楼宇id
	 * @param floor
	 *            楼层
	 * @param anchorType
	 *            锚节点类型
	 * @return
	 */
	@GetMapping("/anchors")
	public Result<List<Anchor>> anchors(
			@RequestParam(value = "bid") Integer bid,
			@RequestParam(value = "floor") Integer floor,
			@RequestParam(value = "anchorType", required = false) Integer anchorType) {
		List<Anchor> anchors = netMapper.getAnchors(bid, floor, anchorType);
		Result<List<Anchor>> result = new Result<>(anchors);
		return result;
	}

	/**
	 * 环境设备信息列表，包括最新的环境数据 TODO:分页
	 * 
	 * @param nid
	 *            网络id
	 * @return
	 */
	@GetMapping("/sensors")
	public Result<List<Sensor>> sensors(@RequestParam(value = "nid") Integer nid) {
		List<Sensor> sensors = sensorMapper.getSensorsByNid(nid);
		Result<List<Sensor>> result = new Result<>(sensors);
		return result;
	}

	/**
	 * 某环境设备的最新环境信息
	 * 
	 * @param sid
	 *            环境传感器id
	 * @return
	 */
	@GetMapping("/sensor/latest")
	public Result<SensorData> sensorLatestData(
			@RequestParam(value = "sid") Integer sid) {
		SensorData data = sensorMapper.getLatestSensorDataBySid(sid);
		Result<SensorData> result = new Result<>(data);
		return result;
	}

	/**
	 * 某环境设备的所有历史环境信息
	 * 
	 * @param sid
	 * @param limit
	 * @return
	 */
	@GetMapping("/sensor/data")
	public Result<List<SensorData>> sensorData(
			@RequestParam(value = "sid") Integer sid,
			@RequestParam(value = "limit", required = false) Integer limit) {
		if (limit == null) {
			limit = SensorData.DefaultLimit;
		}
		List<SensorData> data = sensorMapper.getSensorDataBySid(sid, limit);
		Result<List<SensorData>> result = new Result<>(data);
		return result;
	}

	/**
	 * 获取一个楼层监控网络中的移动目标
	 * 
	 * @param nid
	 *            网络id
	 * @param startTime
	 *            时间段：开始，若为空，就是所有数据
	 * @param endTime
	 *            时间段：结束，默认是当前时间
	 * @return
	 */
	@GetMapping("/targets")
	public Result<List<Target>> targets(
			@RequestParam(value = "nid") Integer nid,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) {
		if (endTime == null || endTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
			endTime = sdf.format(new Date());
		}

		Result<List<Target>> targets = targetService.getTargetByNidAndTime(nid,
				startTime, endTime);
		return targets;
	}

	/**
	 * 获取移动目标的轨迹
	 * 
	 * @param targetId
	 * @return
	 */
	// http://localhost:8084/wsnIndoorBackData/trails?targetId=2018120111
	@GetMapping("/trails")
	public Result<List<Trail>> trailData(
			@RequestParam(value = "targetId") int targetId) {
		Result<List<Trail>> trails = targetService
				.getTrailsByTargetId(targetId);
		return trails;
	}
}
