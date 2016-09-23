package com.wichat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wichat.controller.base.BaseController;

@Controller
@RequestMapping(value="/admin")
public class LoginController extends BaseController{
	
	@RequestMapping(value="/index")
	public String Index(){
		return "/login";
	}
	
	@RequestMapping(value="/login")
	public String Login(){
		
		
		
		return "/index";
	}

	
}
