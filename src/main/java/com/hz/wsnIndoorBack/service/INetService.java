package com.hz.wsnIndoorBack.service;

import com.hz.wsnIndoorBack.DTO.NetComDev;
import com.hz.wsnIndoorBack.DTO.Result;

public interface INetService {
	/**
	 * 根据bid获取网络通信设备，包括协调器和网关
	 * @param bid
	 * @return
	 */
	public Result<NetComDev> getNetComDevByBid(Integer bid);
}
