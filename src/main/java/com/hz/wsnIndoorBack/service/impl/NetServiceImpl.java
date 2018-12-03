package com.hz.wsnIndoorBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.wsnIndoorBack.DTO.NetComDev;
import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.mapper.NetMapper;
import com.hz.wsnIndoorBack.model.Coordinator;
import com.hz.wsnIndoorBack.model.Sink;
import com.hz.wsnIndoorBack.service.INetService;

@Service
public class NetServiceImpl implements INetService{
	@Autowired
	NetMapper networkMapper;
	
	@Override
	public Result<NetComDev> getNetComDevByBid(Integer bid) {
		List<Coordinator> coordinators=networkMapper.getCoordinatorsByBid(bid);
		List<Sink>sinks=networkMapper.getSinksByBid(bid);
		NetComDev netComDev=new NetComDev();
		netComDev.setCoordinators(coordinators);
		netComDev.setSinks(sinks);
		Result<NetComDev>result=new Result<>(netComDev);
		return result;
	}

}
