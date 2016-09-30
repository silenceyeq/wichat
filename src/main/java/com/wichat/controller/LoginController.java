package com.wichat.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wichat.constant.WiChat;
import com.wichat.controller.base.BaseController;
import com.wichat.entity.User;
import com.wichat.entity.UserToken;
import com.wichat.service.UserService;
import com.wichat.service.UserTokenService;
import com.wichat.utils.AESHelper;
import com.wichat.utils.MD5Util;
import com.wichat.utils.VerifyCodeUtil;

@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes attr) {

		User user = getUserByCookies(request, response);
		if (!StringUtils.isEmpty(user)) {
			model.addAttribute("account", user.getAccount());
			model.addAttribute("password", user.getPassword());
		}
		return "/login";
	}

	@RequestMapping(value = "/index")
	public String Index(HttpServletRequest request, HttpServletResponse response,RedirectAttributes attr,Model model) {
		User user = (User) request.getSession().getAttribute("user");
		if(!StringUtils.isEmpty(user)){
			model.addAttribute("user",user);
		}
		return "/index";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "redirect:/admin/login";
	}

	@RequestMapping(value = "/userLogin")
	public String userLogin(@RequestParam String account, @RequestParam String password, String isRememberPassowrd,
			Model model,RedirectAttributes attr, HttpServletRequest request, HttpServletResponse response) {
		User user = login(account, password,isRememberPassowrd,request,response);
		if (ObjectUtils.isEmpty(user)) {
			attr.addFlashAttribute("error_msg","password error !");
			return "redirect:/admin/login";
		} else {
			return "redirect:/admin/index";
		}
	}

	private User login(String account, String password,String isRememberPassowrd,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("account", account);
		User user = userService.queryByCondition(params);
		
		// 如果用户不存在，则直接注册
		if(StringUtils.isEmpty(user)){
			user = new User();
			user.setAccount(account);
			user.setPassword(password);
		}else{
			params.put("password", password);
			user = userService.queryByCondition(params);
		}
		
		if(!StringUtils.isEmpty(user) && user != null){
			request.getSession().setAttribute("user", user);
			if ("1".equals(isRememberPassowrd)) {
				String token = AESHelper.AESEncrypt(user.getAccount()+" "+user.getPassword()+WiChat.WICHAT);
				Cookie cookieToken = new Cookie("WICHAT_USER_TOKEN", token);
				cookieToken.setMaxAge(60 * 60 * 2);
				response.addCookie(cookieToken);
			}
		}
		return user;
	}

	private User getUserByCookies(HttpServletRequest request, HttpServletResponse response) {

		User user = null;
		try {
			Cookie[] cookies = request.getCookies();
			String tokenValue = null;
			for (int i = 0; i < cookies.length; i++) {				Cookie cookie = cookies[i];
				if ("WICHAT_USER_TOKEN".equals(cookie.getName())) {
					tokenValue = cookie.getValue();
				}
			}
			if(!StringUtils.isEmpty(tokenValue)){
				user = new User();
				String[] tmp = AESHelper.AESDecrypt(tokenValue).split(" ");
				user.setAccount(tmp[0].toString());
				user.setPassword(tmp[1].replace("wichat", ""));
			}
		} catch (NullPointerException e) {
			log.error("cookies is null.");
		}
		return user;
	}
	
	public static void main(String[] args) {
		String i = AESHelper.AESEncrypt("VW+HlvwkkouH8W7bAhphMQ==");
		System.out.println(i);
	}
}
