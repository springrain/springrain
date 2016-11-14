package org.springrain.frame.shiro;

import java.io.File;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * 记录访问日志的过滤器
 * @author caomei
 *
 */


@Component("statichtml")
public class FrameStaticHtmlFilter extends OncePerRequestFilter {
	public Logger logger = LoggerFactory.getLogger(getClass());

	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		File htmlFile = new File("static/1.html");  
		if(!htmlFile.exists()){
			chain.doFilter(request, response);
		}else{
		     response.setContentType("text/html;charset=UTF-8");
		     request.setCharacterEncoding("UTF-8");
			 response.getWriter().write(IOUtils.toString(htmlFile.toURI(), "UTF-8"));
		}
		
		
	}
	
	
}
