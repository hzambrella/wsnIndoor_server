package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Target;
import com.hz.wsnIndoorBack.model.TrailPoint;

public interface TargetMapper {
	public List<TrailPoint> getTrailPointsInMockData(
			@Param("targetId") int targetId, @Param("nid") int nid);

	public int deleteTarget(@Param("targetId") int targetId);

	public int registTarget(@Param("target")Target target);

	//
	public int createTrailPointTableMock(@Param("nid") int nid,
			@Param("dateSuffix") String dateSuffix);

	public int insertTrailPoint(@Param("point") TrailPoint point,
			@Param("nid") int nid, @Param("dateSuffix") String dateSuffix);
	//
	public List<Target> getTargetsByNidAndTime(@Param("nid") int nid,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime);
	//
	// public List<TrailPoint>getTrailPointsByTargetId(int targetId);
}
