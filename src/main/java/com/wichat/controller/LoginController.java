package com.wichat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class LoginController {
	
	@RequestMapping(value="/login")
	public String Login(){
		return "/login";
	}

	
}
