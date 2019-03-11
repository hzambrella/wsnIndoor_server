package com.hz.wsnIndoorBack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hz.wsnIndoorBack.DTO.Result;
import com.hz.wsnIndoorBack.service.IAnchorLocService;

@RestController
@RequestMapping("/wsnIndoorBack/anchorLoc")
public class BackLearningController {
	@Autowired
	private IAnchorLocService anchorLocService;
	/**
	 * 锚节点定位-洪泛阶段。向网关索要关键锚节点之间的跳距，训练出模型 
	 * TODO:是否需要加锁？
	 * 
	 * @param nid
	 * @return
	 */
	// http://localhost:8084/wsnIndoorBack/anchorLoc/train?nid=1
	@GetMapping("/train")
	public Result<Object> anchorLocTrain(@RequestParam(value = "nid") int nid) {
		return anchorLocService.floodAndTrain(nid);
	}

	/**
	 * 锚节点定位-预测阶段。向网关索要普通锚节点之间的跳距 输入普通锚节点的跳距，得到预测结果。
	 *  TODO:是否需要加锁？
	 * 
	 * @param nid
	 * @return
	 */
	// http://localhost:8084/wsnIndoorBack/anchorLoc/predict?nid=1
	@GetMapping("/predict")
	public Result<Object> anchorLocPredict(@RequestParam(value = "nid") int nid) {
		return anchorLocService.predict(nid);
	}
}
