package com.hz.wsnIndoorBack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO:容错性界面，登录
/**
* 页面
* 
* @author haozhoa
* 
*/
//http://localhost:8084/wsnIndoorBackPage
@Controller
@RequestMapping("/wsnIndoor")
public class BackMainController {
	@RequestMapping(method = RequestMethod.GET)
	public String main(){
		return "redirect:/wsnIndoor/login";
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value="login")
	public String login() {
		//TODO:校验登录
		return  "html/login";
	}
	
	@RequestMapping(method = RequestMethod.GET,value="logout")
	public String logout() {
		//TODO:清除cookie
		return  "html/login";
	}
}
