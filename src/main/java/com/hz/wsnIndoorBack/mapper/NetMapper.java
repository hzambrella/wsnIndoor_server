package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Sink;

public interface NetMapper {
	public List<Sink>getSinksByBid(@Param("bid")Integer bid);
	public List<Sink>getCoordinatorsByBid(@Param("bid")Integer bid);
}
