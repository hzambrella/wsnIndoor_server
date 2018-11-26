package com.hz.wsnIndoorBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Anchor;
import com.hz.wsnIndoorBack.model.Coordinator;
import com.hz.wsnIndoorBack.model.Network;
import com.hz.wsnIndoorBack.model.Sink;

public interface NetMapper {
	public List<Sink>getSinksByBid(@Param("bid")Integer bid);
	public List<Coordinator>getCoordinatorsByBid(@Param("bid")Integer bid);
	public List<Anchor>getAnchors(@Param("bid")Integer bid,
			@Param("floor")Integer floor, @Param("anchorType")Integer anchorType);
	public Network getNetwork(@Param("bid")Integer bid,@Param("floor")Integer floor);
}
