package com.wichat.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;
import com.wichat.controller.base.BaseController;
import com.wichat.utils.QRCodeGenerateUtil;
import com.wichat.utils.VerifyCodeUtil;

@Controller
@RequestMapping(value="/tools")
public class ToolsController extends BaseController{

	@RequestMapping(value="/varifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response){
		BufferedImage image = VerifyCodeUtil.generateImageCode(0, 4, "", 149, 30, 4, true, Color.white, null, null);
		try {
			ImageIO.write(image, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/code")
	public void qrCode(HttpServletResponse response,HttpServletRequest request){
		try {
			QRCodeGenerateUtil.generateCodeToStream("www.baidu.com", "link", 100, response);
		} catch (IOException | WriterException e) {
			e.printStackTrace();
		}
	}
}
