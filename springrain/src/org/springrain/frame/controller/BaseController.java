package org.springrain.frame.controller;

import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springrain.demo.entity.User;
import org.springrain.frame.common.BaseLogger;
import org.springrain.frame.util.CaptchaUtils;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.GlobalStatic;

/**
 * 基础的Controller,所有的Controller必须继承此类
 * @copyright {@link 9iu.org}
 * @author springrain<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see org.springrain.frame.controller.BaseController
 * @param <T>
 */
@Controller
public class BaseController extends BaseLogger {

	public String messageurl = "/common/message";

	public String message = "message";

/**
 * 初始化映射格式.
 * @param binder
 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if(StringUtils.isNotBlank(value)){
					setValue(new SimpleDateFormat(DateUtils.DATE_FORMAT)
							.parse(value));
					}else{
						setValue(null);
					}
				} catch (Exception e) {
					setValue(null);
					logger.error(e);
				}
			}
			/*
			 * public String getAsText() { return new
			 * SimpleDateFormat("yyyy-MM-dd").format((Date) getValue()); }
			 */
		});
		
		binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if(StringUtils.isNotBlank(value)){
					setValue(Integer.valueOf(value));
					}else{
						setValue(null);
					}
				} catch (Exception e) {
					setValue(null);
					logger.error(e);
				}
			}
		});
		binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if(StringUtils.isNotBlank(value)){
					setValue(Long.valueOf(value));
					}else{
						setValue(null);
					}
				} catch (Exception e) {
					setValue(null);
					logger.error(e);
				}
			}
		});
		binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if(StringUtils.isNotBlank(value)){
					setValue(Double.valueOf(value));
					}else{
						setValue(null);
					}
				} catch (Exception e) {
					setValue(null);
					logger.error(e);
				}
			}
		});
		
		binder.registerCustomEditor(BigDecimal.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if(StringUtils.isNotBlank(value)){
					setValue(new BigDecimal(value));
					}else{
						setValue(null);
					}
				} catch (Exception e) {
					setValue(null);
					logger.error(e);
				}
			}
		});
		
		
	}
/**
 * 首页的映射
 * @param model
 * @return
 * @throws Exception
 */
	@RequestMapping(value = "/index")
	public String index(Model model) throws Exception {
			return "/index";
		
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest request) throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()){
			return "redirect:/index";
		}
		return "/login";
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String loginPost(User currUser,HttpSession session,Model model,HttpServletRequest request) throws Exception {
		Subject user = SecurityUtils.getSubject();
		  String code = (String) session.getAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		  if(StringUtils.isNotBlank(code)){
			  code=code.toLowerCase().toString();
		  }
		String submitCode = WebUtils.getCleanParam(request, GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		  if(StringUtils.isNotBlank(submitCode)){
			  submitCode=submitCode.toLowerCase().toString();
		  }
		if (StringUtils.isBlank(submitCode) ||StringUtils.isBlank(code)||!code.equals(submitCode)) {
			model.addAttribute("message", "验证码错误!");
			return "/login";
        }
		
		UsernamePasswordToken token = new UsernamePasswordToken(currUser.getAccount(),currUser.getPassword());
		
		token.setRememberMe(true);
		try {
			user.login(token);
		} catch (UnknownAccountException uae) {
			model.addAttribute("message", "账号不存在!");
			return "/login";
		} catch (IncorrectCredentialsException ice) {
			model.addAttribute("message", "密码错误!");
			return "/login";
		} catch (LockedAccountException lae) {
			model.addAttribute("message", "账号被锁定!");
			return "/login";
		} catch (Exception e) {
			model.addAttribute("message", "未知错误,请联系管理员.");
			return "/login";
		}
		int timeout=session.getMaxInactiveInterval();
		SecurityUtils.getSubject().getSession().setTimeout(timeout*1000);
		return "redirect:/index";
	}
	
	/**
	 * 没有权限
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) throws Exception {
		if(SecurityUtils.getSubject().isAuthenticated()==false){
			return "redirect:/login";
		}
			return "/unauth";
		
	}
	
	/**
	 * 退出
	 * @param request
	 */
	@RequestMapping(value="/logout")
    public void logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {           
            subject.logout();
        }
        request.getSession().invalidate();
    }
	
	@RequestMapping(value = "/mobilelogin")
	public String mobilelogin(Model model) throws Exception {
		return "/mobilelogin";
	}
	
	
	@ExceptionHandler
	public String exp(HttpServletRequest request,Exception e){
		e.printStackTrace();
		request.setAttribute("e", e);
		logger.error(e);
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

		CaptchaUtils tool = new CaptchaUtils();
		StringBuffer code = new StringBuffer();
		BufferedImage image = tool.genRandomCodeImage(code);
		session.removeAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM);
		session.setAttribute(GlobalStatic.DEFAULT_CAPTCHA_PARAM, code.toString());

		// 将内存中的图片通过流动形式输出到客户端
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return;
	}
	
	

	/**
	 * 公共下载方法
	 * 
	 * @param response
	 * @param file
	 *            下载的文件
	 * @param fileName
	 *            下载时显示的文件名
	 * @return
	 * @throws Exception
	 */
	public HttpServletResponse downFile(HttpServletResponse response,
			File file, String fileName,boolean delFile) throws Exception {
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		// 下面一步不可少
		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		response.addHeader("Content-disposition", "attachment;filename="
				+ fileName);// 设定输出文件头

		try {
			out = response.getOutputStream();
			in = new FileInputStream(file);
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();

		} catch (Exception e) {
			logger.error(e);
			throw new Exception("下载失败!");
		} finally {
			if(in!=null){  
			   in.close(); 
			}
			if(out!=null){
			    out.close();
			}
			if(delFile){
				file.delete(); 
			}
		}

		return response;
	}
}
