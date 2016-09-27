package com.wichat.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wichat.controller.base.BaseController;
import com.wichat.entity.User;
import com.wichat.service.UserService;

@Controller
@RequestMapping(value="/admin")
public class LoginController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String Login(){
		return "/login";
	}
	@RequestMapping(value="/index")
	public String Index(){
		return "/index";
	}
	
	
	@RequestMapping(value="/userLogin")
	public String userLogin(@RequestParam String account,@RequestParam String password,Model model){
		User user = login(account, password);
		if(ObjectUtils.isEmpty(user)){
			return "redirect:/admin/login";
		}else{
			log.debug(user.toString());
			return "redirect:/admin/index";
		}
	}
	
	public User login(String account,String password){
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("password", password);
		return userService.queryByCondition(params);
	}
	
	

	
}
