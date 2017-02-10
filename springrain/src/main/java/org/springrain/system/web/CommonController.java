package org.springrain.system.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.CaptchaUtils;
import org.springrain.frame.util.GlobalStatic;

@Controller
public class CommonController extends BaseController  {
	

		
		/**
		 * 没有权限
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/unauth")
		public String unauth(Model model) throws Exception {
				return "/unauth";
		}
		
		/**
		 * token错误
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/tokenerror")
		public String tokenerror(Model model) throws Exception {
				return "/tokenerror";
		}
		
		
		/**
		 * token错误
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/error")
		public String error(Model model) throws Exception {
				return "/error";
		}
		
		
		
		
		
		
		
		/**
		 * 生成验证码
		 * 
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping("/getCaptcha")
		public void getCaptcha(HttpSession session,HttpServletResponse response) throws IOException {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);

			StringBuffer code = new StringBuffer();
			BufferedImage image = CaptchaUtils.genRandomCodeImage(code);
			session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
			session.setAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM, code.toString());

			// 将内存中的图片通过流动形式输出到客户端
			ImageIO.write(image, "JPEG", response.getOutputStream());
			return;
		}
		
}
