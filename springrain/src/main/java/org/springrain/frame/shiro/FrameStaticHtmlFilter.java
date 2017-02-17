package org.springrain.frame.shiro;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.service.IStaticHtmlService;
/**
 * 页面静态化的过滤器
 * @author caomei
 *
 */


@Component("statichtml")
public class FrameStaticHtmlFilter extends OncePerRequestFilter {
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IStaticHtmlService staticHtmlService;
	
	

	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
        HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		int i=uri.indexOf(contextPath);
		if(i>-1){
			uri=uri.substring(i+contextPath.length());
		}
		
		//cache key,可以根据URI从数据库进行查询资源Id
		String htmlPath=null;
		try {
			htmlPath = staticHtmlService.findHtmlPathByURI(uri);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
		if(StringUtils.isBlank(htmlPath)||"error".equals(htmlPath)){//缓存中不存在
			chain.doFilter(request, response);
			return;
		}
		
		File htmlFile = new File(htmlPath);  
		if(!htmlFile.exists()){
			chain.doFilter(request, response);
			return;
		}
		
		    response.setContentType("text/html;charset="+GlobalStatic.defaultCharset);
		    response.setCharacterEncoding(GlobalStatic.defaultCharset);
		    
		    // 读出文件到response  
            // 这里是先需要把要把文件内容先读到缓冲区  
            // 再把缓冲区的内容写到response的输出流供用户下载  
            FileInputStream fileInputStream = new FileInputStream(htmlFile);  
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);  
            BufferedReader reader = new BufferedReader (new InputStreamReader(bufferedInputStream,GlobalStatic.defaultCharset));
            PrintWriter writer = response.getWriter();
            
           char[] data = new char[1024];
           while( reader.read(data)!=-1){
        	   writer.write(data); 
            } 
           
           
           reader.close();
           bufferedInputStream.close();
           fileInputStream.close();
           writer.close();
          
		}

}
