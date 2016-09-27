package com.wichat.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wichat.controller.base.BaseController;
import com.wichat.entity.User;
import com.wichat.entity.UserToken;
import com.wichat.service.UserService;
import com.wichat.service.UserTokenService;
import com.wichat.utils.MD5Util;
import com.wichat.utils.VerifyCodeUtil;

@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private UserTokenService userTokenService;

	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = autoLogin(request, response);
		if (!StringUtils.isEmpty(user)) {
			model.addAttribute("account", user.getAccount());
			model.addAttribute("password", user.getPassword());
		}
		return "/login";
	}

	@RequestMapping(value = "/index")
	public String Index(HttpServletRequest request, HttpServletResponse response) {
		return "/index";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", null);
		return "redirect:/admin/login";
	}

	@RequestMapping(value = "/userLogin")
	public String userLogin(@RequestParam String account, @RequestParam String password, String isAutoLogin,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		User user = login(account, password);
		if (ObjectUtils.isEmpty(user)) {
			return "redirect:/admin/login";
		} else {

			session.setAttribute("userInfo", user);
			if ("1".equals(isAutoLogin)) {

				String agent = MD5Util.MD5Encode(user.getNickname() + "wichat", "UTF-8");
				String token = MD5Util.MD5Encode(agent + user.getId() + user.getPassword() + "wichat", "UTF-8");
				Cookie cookieAgent = new Cookie("WICHAT_USER_AGENT", agent);
				Cookie cookieToken = new Cookie("WICHAT_USER_TOKEN", token);
				cookieAgent.setMaxAge(60 * 60 * 2);
				cookieToken.setMaxAge(60 * 60 * 2);
				response.addCookie(cookieAgent);
				response.addCookie(cookieToken);

				UserToken userToken;
				userToken = userTokenService.getById(user.getId());
				if (StringUtils.isEmpty(userToken)) {
					userToken = new UserToken();
					userToken.setUserId(user.getId());
					userToken.setUserAgent(cookieAgent.getValue());
					userToken.setToken(cookieToken.getValue());
					userToken.setCreateTime(new Date());
					userToken.setExpires(60 * 60 * 2);
					userTokenService.insert(userToken);
				} else {
					userToken.setUserAgent(cookieAgent.getValue());
					userToken.setToken(cookieToken.getValue());
					userToken.setCreateTime(new Date());
					userToken.setExpires(60 * 60 * 2);
					userTokenService.updateByPrimaryKeySelective(userToken);
				}
			}
			return "redirect:/admin/index";
		}
	}
	
	@RequestMapping(value="/varifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response){
		
		BufferedImage image = VerifyCodeUtil.generateImageCode(0, 4, "", 149, 30, 4, true, Color.gray, null, null);
		try {
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private User login(String account, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("account", account);
		params.put("password", password);
		return userService.queryByCondition(params);
	}

	private User autoLogin(HttpServletRequest request, HttpServletResponse response) {

		User user = null;
		try {
			Cookie[] cookies = request.getCookies();
			String agentValue = null;
			String tokenValue = null;
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cookieName = cookie.getName();
					if ("WICHAT_USER_AGENT".equals(cookieName)) {
						agentValue = cookie.getValue();
					}
					if ("WICHAT_USER_TOKEN".equals(cookieName)) {
						tokenValue = cookie.getValue();
					}
				}
			}

			Map<String, Object> params = new HashMap<>();
			params.put("user_agent", agentValue);
			UserToken oriToken = userTokenService.queryByCondition(params);
			if (oriToken.getToken().equals(tokenValue)) {
				user = userService.getById(oriToken.getUserId());
				request.getSession().setAttribute("userInfo", user);
				return user;
			}
		} catch (NullPointerException e) {
			log.error("cookies is null.");
		}

		return user;
	}

}
