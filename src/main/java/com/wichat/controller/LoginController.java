package com.wichat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wichat.ChatServer;
import com.wichat.controller.base.BaseController;

@Controller
@RequestMapping(value="/admin")
public class LoginController extends BaseController{
	
	@RequestMapping(value="/login")
	public String Login(){
		System.out.println("login .");
		ChatServer c = new ChatServer();
		return "/login";
	}

	
}
