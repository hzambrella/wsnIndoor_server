package com.hz.wsnIndoorBack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz.wsnIndoorBack.model.Anchor;
import com.hz.wsnIndoorBack.model.Coordinator;
import com.hz.wsnIndoorBack.model.Network;
import com.hz.wsnIndoorBack.model.Sink;

public interface NetMapper {
	public List<Sink>getSinksByBid(@Param("bid")Integer bid);//根据bid获取网关
	public List<Coordinator>getCoordinatorsByBid(@Param("bid")Integer bid);//根据bid获取协调器
	public List<Anchor>getAnchors(@Param("bid")Integer bid,
			@Param("floor")Integer floor, @Param("anchorType")Integer anchorType);//获取锚节点列表
	public Network getNetwork(@Param("bid")Integer bid,@Param("floor")Integer floor);//获取某楼层的网络对象
	public List<Network> getNetworks(@Param("bid")Integer bid);//获取楼宇的所有网络对象
	
	public void saveAnchorLocResult(Anchor anchor);//存储锚节点定位的结果
}
