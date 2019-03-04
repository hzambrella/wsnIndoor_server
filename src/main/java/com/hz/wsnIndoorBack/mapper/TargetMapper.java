package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.TP;
import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.model.TrailPoint;

public interface TargetMapper {
	public List<TrailPoint> getTrailPointsInMockData(
			@Param("targetId") int targetId, @Param("nid") int nid);//从mock表中获得轨迹数据

	public int deleteTarget(@Param("targetId") int targetId);//删除一个移动监测目标

	public int registTarget(@Param("target")Target target);//注册一个移动监测目标

	public int createTrailPointTableMock(@Param("nid") int nid,
			@Param("dateSuffix") String dateSuffix);//创建mock数据

	public int insertTrailPoint(@Param("point") TrailPoint point,
			@Param("nid") int nid, @Param("dateSuffix") String dateSuffix);//插入轨迹点
	//
	public List<Target> getTargetsByNidAndTime(@Param("nid") int nid,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime);//查询某楼层时间段内的移动目标
	//
	public List<TrailPoint>getTrailPointsByTargetId(int targetId);//根据目标ID获取轨迹点
	
	public List<TP>test(@Param("targetId")int targetId);
}
