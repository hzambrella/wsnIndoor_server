package com.hz.wsnIndoorBack.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class ControllerDemo {
	@RequestMapping("/hello")
	public String helloHtml(HashMap<String, Object> map) {
		map.put("hello", "欢迎进入HTML页面");
		return "/html/hello";
	}
	
	//http://localhost:8084/page/index/1
	@RequestMapping("/index/{id}")
	public String getIndex(@PathVariable("id") int id, Model model) {
		// return "index";
		// 这里模拟一些数据
		model.addAttribute("title", "This is a blog with id = " + id);
		model.addAttribute("createdTime", "2017-11-13");
		model.addAttribute("content", "This is content");
		model.addAttribute("bid", "12312312");
		return "/html/test_template";
	}
}