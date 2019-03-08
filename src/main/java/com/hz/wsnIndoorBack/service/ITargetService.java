package com.hz.wsnIndoorBack.service;

import java.util.List;

import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.DTO.Trail;
import com.hz.wsnIndoorBack.model.Target;

public interface ITargetService {
	/**
	 * 根据nid和时间段获取轨迹数据
	 * @param targetId
	 * @return
	 */
	public Result<List<Target>> getTargetByNidAndTime(int nid, String startTime,
			String endTime);
	
	/**
	 * 根据targetId获取轨迹数据
	 * @param targetId
	 * @return
	 */
	public Result<List<Trail>> getTrailsByTargetId(Integer targetId);

}
