package org.springrain.frame.shiro;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.service.IStaticHtmlService;
/**
 * 记录访问日志的过滤器
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
		}else{
		    response.setContentType("text/html;charset="+GlobalStatic.defaultCharset);
		    response.setCharacterEncoding(GlobalStatic.defaultCharset);
			response.getWriter().write(IOUtils.toString(htmlFile.toURI(), GlobalStatic.defaultCharset));
		}
		
		
	}

	
}
