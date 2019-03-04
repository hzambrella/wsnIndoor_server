package com.hz.wsnIndoorBack.service;

import com.hz.wsnIndoorBack.DTO.NetComDev;
import com.hz.wsnIndoorBack.DTO.Result;

public interface INetService {
	public Result<NetComDev> getNetComDevByBid(Integer bid);
}
