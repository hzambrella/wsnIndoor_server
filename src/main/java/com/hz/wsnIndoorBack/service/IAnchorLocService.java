package com.hz.wsnIndoorBack.service;

import com.hz.wsnIndoorBack.DTO.Result;

public interface IAnchorLocService {
	/**
	 * 锚节点定位-洪泛阶段。向网关索要关键锚节点之间的跳距，训练出模型 
	 * TODO:是否需要加锁？
	 * 
	 * @param nid 网络id
	 * @return 信息，若有异常，返回用户错误提示。
	 */
	public Result<Object>floodAndTrain(Integer nid);
	/**
	 * 锚节点定位-预测阶段。向网关索要普通锚节点之间的跳距 输入普通锚节点的跳距，得到预测结果。 
	 * TODO:是否需要加锁？
	 * 
	 * @param nid 网络id
	 * @return 信息，若有异常，返回用户错误提示。 
	 */
	public Result<Object>predict(Integer nid);
}
