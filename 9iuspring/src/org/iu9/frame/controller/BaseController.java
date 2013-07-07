package org.iu9.frame.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.common.BaseLogger;
import org.iu9.frame.common.SessionUser;
import org.iu9.frame.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基础的Controller,所有的Controller必须继承此类
 * @copyright {@link 9iu.org}
 * @author 9iuspring<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.controller.BaseController
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
	
	@RequestMapping(value = "/login")
	public String login(Model model,HttpServletRequest request) throws Exception {
		String loginType=request.getParameter("logintype");
		if(StringUtils.isNotEmpty(loginType)){
			if("pc".equals(loginType)){
				request.getSession().setAttribute("cerp_session_pc", true);
			}
		
		}
		return "/login";
	}
	@RequestMapping(value = "/mobilelogin")
	public String mobilelogin(Model model) throws Exception {
		return "/mobilelogin";
	}
	
	@RequestMapping(value = "/noqx")
	public String noqx(Model model) throws Exception {
		return "/noqx";
	}
	
	@ExceptionHandler
	public String exp(HttpServletRequest request,Exception e){
		e.printStackTrace();
		request.setAttribute("e", e);
		logger.error(e);
		return "/error";
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
