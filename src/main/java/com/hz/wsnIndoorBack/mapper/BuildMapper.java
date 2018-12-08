package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Building;

public interface BuildMapper {
	List<Building>getBuildings();
	Building getBuildByNid(@Param("nid")int nid);
}
