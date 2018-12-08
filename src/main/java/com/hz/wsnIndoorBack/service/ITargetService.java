package com.hz.wsnIndoorBack.service;

import java.util.List;

import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.DTO.Trail;

public interface ITargetService {
	public Result<List<Trail>> getTrailsByTargetId(Integer targetId);
}
