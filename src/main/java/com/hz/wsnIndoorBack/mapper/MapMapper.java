package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.BaseMapInfo;
import com.hz.wsnIndoorBack.model.BuildMapRel;
import com.hz.wsnIndoorBack.model.Map;

public interface MapMapper {
	List<BuildMapRel>getBuildMapRel(@Param("bid")int bid);//根据bid获取楼层和地图的映射
	List<Map>getMaps();//获取所有地图
	Map getMapById(@Param("mapId")int mapId);//mapID获取地图对象
	BaseMapInfo getBaseMapInfo(@Param("mapId")int mapId);//mapID获取底图对象
}
