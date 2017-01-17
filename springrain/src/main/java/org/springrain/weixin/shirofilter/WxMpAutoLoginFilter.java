package org.springrain.weixin.shirofilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String siteId=SiteUtils.getSiteId();
		if(StringUtils.isBlank(siteId)){
			return;
		}
		
		
		String openId = "";
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse rep = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			openId = (String) session.getAttribute("openId");
			if (StringUtils.isNotBlank(openId)) {
				chain.doFilter(request, response);
				return;
			}
			
			
			String url = SiteUtils.getRequestURL(req);
			
			
		    req.getRequestDispatcher("/wx/mpautologin/"+siteId+"/oauth2?url=" + url).forward(request, response);
		    
			//rep.sendRedirect(SiteUtils.getSiteURLPath(req)+"/wx/mpautologin/"+siteId+"/oauth2?url="+ url);
		    
		    
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
