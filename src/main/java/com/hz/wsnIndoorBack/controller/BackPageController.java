package com.hz.wsnIndoorBack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author haozhoa
 * 页面
 */
@Controller
@RequestMapping("/wsnIndoorBackPage")
public class BackPageController {
	@RequestMapping("/building")
	public String buildingPage() {
		return "html/back_buildingByBMap";
	}

	@RequestMapping("/map")
	public String mapPage() {
		return "html/back_map";
	}
	
	@RequestMapping("/mapdetail")
	public String mapdetailPage() {
		return "html/back_mapdetail";
	}
	
	@RequestMapping("/network")
	public String networkPage() {
		return "html/back_network";
	}

	@RequestMapping("/monitor")
	public String monitorPage() {
		return "html/back_monitor";
	}
}
