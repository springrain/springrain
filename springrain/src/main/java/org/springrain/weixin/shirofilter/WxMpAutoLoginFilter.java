package org.springrain.weixin.shirofilter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springrain.cms.utils.SiteUtils;

@Component("wxmpautologin")
public class WxMpAutoLoginFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String siteId=getSiteId(req);
		if(StringUtils.isBlank(siteId)){
			return;
		}
		
		
		String openId = "";
		try {
			
			HttpSession session = req.getSession();
			openId = (String) session.getAttribute("openId");
			if (StringUtils.isNotBlank(openId)) {
				chain.doFilter(request, response);
				return;
			}
			
			
			String url = SiteUtils.getRequestURL(req);
			
			
		    req.getRequestDispatcher("/mp/mpautologin/"+siteId+"/oauth2?url=" + url).forward(request, response);
		    
			//rep.sendRedirect(SiteUtils.getSiteURLPath(req)+"/mp/mpautologin/"+siteId+"/oauth2?url="+ url);
		    
		    
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getSiteId(HttpServletRequest request) throws IOException, ServletException{
		   
		  
		   String requestURI = request.getRequestURI();
		   
		   int s_index=requestURI.indexOf("/s_");
		   if(s_index<0){
			   return null;
		   }
		   String siteId=requestURI.substring(s_index+1, requestURI.indexOf("/", s_index+1));
		   
		   //重新编码siteId,避免注入
		   siteId=URLEncoder.encode(siteId,"UTF-8");
		   
		   //避免注入
		   if(StringUtils.isBlank(siteId)||siteId.length()>30){
			   return null;
		   }
		
		   
		   return siteId;
		   
	   }
	
	

}
