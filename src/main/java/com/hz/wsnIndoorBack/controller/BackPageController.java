package com.hz.wsnIndoorBack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//TODO:容错性界面，登录
/**
 * 页面
 * 
 * @author haozhoa
 * 
 */
// http://localhost:8084/wsnIndoorBackPage
@Controller
@RequestMapping("/wsnIndoorBackPage")
public class BackPageController {
	@RequestMapping(method = RequestMethod.GET)
	public String page() {
		return "redirect:/wsnIndoorBackPage/buildInfoByBMap";
	}

	/**
	 * 楼内管理入口：表格展示
	 * 
	 * @return
	 */
	@RequestMapping("/buildInfoByTab")
	public String buildInfoByTab() {
		return "html/back_buildingByTab";
	}

	/**
	 * 楼内管理入口：地图展示
	 * 
	 * @return
	 */
	@RequestMapping("/buildInfoByBMap")
	public String buildInfoByBMap() {
		return "html/bMap";
	}

	/**
	 * 楼内管理入口：概览
	 * 
	 * @return
	 */
	@RequestMapping("/overview")
	public String overview() {
		return "html/back_overview";
	}

	/**
	 * 地图信息
	 * 
	 * @return
	 */
	@RequestMapping("/mapInfo")
	public String mapInfo() {
		return "html/back_map";
	}

	/**
	 * 地图详情
	 * 
	 * @return
	 */
	@RequestMapping("/mapdetail")
	public String mapdetail() {
		return "html/back_mapdetail";
	}

	/**
	 * 楼内信息概览
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mOverview")
	public String mOverview(
			@RequestParam(value = "bid", required = false) Integer bid,
			Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		if (bid == null || bid == 0) {
			bid = (Integer) session.getAttribute("bid");
			if (bid == null || bid == 0) {
				return "redirect:/wsnIndoorBackPage";
			}
		}
		//TODO:存楼宇名
		session.setAttribute("bid", bid);
		model.addAttribute("bid", bid);
		return "html/monitor_overview";
	}

	/**
	 * 无线网络管理
	 * 
	 * @return
	 */
	@RequestMapping("/mNetwork")
	public String mNetwork(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer bid = (Integer) session.getAttribute("bid");
		if (bid == null || bid == 0) {
				return "redirect:/wsnIndoorBackPage";
		}else{
			session.setAttribute("bid", bid);
			model.addAttribute("bid", bid);
			return "html/monitor_network";
		}
	}

	/**
	 * 锚节点管理
	 * 
	 * @return
	 */
	@RequestMapping("/mAnchor")
	public String mAnchor(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer bid = (Integer) session.getAttribute("bid");
		if (bid == null || bid == 0) {
				return "redirect:/wsnIndoorBackPage";
		}else{
			session.setAttribute("bid", bid);
			model.addAttribute("bid", bid);
			return "html/monitor_anchor";
		}
	}

	/**
	 * 环境传感器管理
	 * 
	 * @return
	 */
	@RequestMapping("/mSensor")
	public String mSensor() {
		return "html/monitor_sensor";
	}

	/**
	 * 移动目标管理
	 * 
	 * @return
	 */
	@RequestMapping("/mMove")
	public String mMove() {
		return "html/monitor_move";
	}
}
