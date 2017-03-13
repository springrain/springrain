package org.springrain.weixin.shirofilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.util.InputSafeUtils;

@Component("wxmpautologin")
public class WxMpAutoLoginFilter extends OncePerRequestFilter {
	private   Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String userAgent = req.getHeader("user-agent").toLowerCase();
		
		if(!userAgent.contains("micromessenger")){//不是微信客户端
			chain.doFilter(request, response);
			return;
		}
		
		
		
		
		String siteId=InputSafeUtils.substringByURI(req.getRequestURI(), "/s_");
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
			logger.error(e.getMessage(),e);
		}
		
	}
	
	
	
	

}
