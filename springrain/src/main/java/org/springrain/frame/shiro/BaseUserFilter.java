package org.springrain.frame.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * 处理登录重定向和验证HTTP Referer,防御CSRF漏洞
 * @author caomei
 *
 */
public class BaseUserFilter extends UserFilter {
	
	@Override
	 protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		 boolean access=super.isAccessAllowed(request, response, mappedValue);
		 if(!access){
			 return access;
		 }
		 
		 //已经登录用户,验证Referer
		 HttpServletRequest req=(HttpServletRequest) request;
		 String referer=req.getHeader("Referer");
		 if(StringUtils.isBlank(referer)){
			 return false;
		 }
		 String basePath = request.getScheme()+"://"+request.getServerName();
		 if(!referer.startsWith(basePath)){
			 return false;
		 }
		 
		 return access;
	 }
	
	@Override
	  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		     HttpServletRequest req=(HttpServletRequest) request;
		     HttpServletResponse res=(HttpServletResponse) response;
	         String loginUrl= getLoginUrl();	
	         StringBuffer url=req.getRequestURL();
	         String query=req.getQueryString();
	         if(StringUtils.isNotBlank(query)){
	        	 url=url.append("?").append(query);
	         }
	        
	         Map<String,String> parMap=new HashMap<String,String>();
	         parMap.put("url", url.toString());
		
	         WebUtils.issueRedirect(request, response, loginUrl,parMap);
	        return false;
	    }

}
