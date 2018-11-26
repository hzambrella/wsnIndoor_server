package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.BaseMapInfo;
import com.hz.wsnIndoorBack.model.BuildMapRel;
import com.hz.wsnIndoorBack.model.Map;

public interface MapMapper {
	List<BuildMapRel>getBuildMapRel(@Param("bid")int bid);
	List<Map>getMaps();
	Map getMapById(@Param("mapId")int mapId);
	BaseMapInfo getBaseMapInfo(@Param("mapId")int mapId);
}
