package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Building;

public interface BuildMapper {
	List<Building>getBuildings(); //获取楼宇列表
	Building getBuildByNid(@Param("nid")int nid); //根据nid获取楼宇
}
