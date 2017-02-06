package org.springrain.frame.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.InputSafeUtils;

/**
 * 站点管理人员的过滤器,验证token
 * @author caomei
 */

@Component("siteuser")
public class SiteUserFilter extends BaseUserFilter {
	
	@Override
	 protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		 boolean access=super.isAccessAllowed(request, response, mappedValue);
		 
		 HttpServletRequest req=(HttpServletRequest) request;
		 if(!access){//登录失败了,需要设置一下需要跳转的登录URL
			 String siteId=InputSafeUtils.substringByURI(req.getRequestURI(), "/s_");
			 request.setAttribute(GlobalStatic.customLoginURLKey, "/s/"+siteId+"/login");
			 return access;
		 } 
		 
		
		 Object obj=req.getSession().getAttribute(GlobalStatic.tokenKey);
		 
		 if(obj==null||!(obj.toString().startsWith("s_")||obj.toString().startsWith("system_"))){//tokenKey必须是s_或者system_开头
			 request.setAttribute(GlobalStatic.errorTokentoURLKey, GlobalStatic.errorTokentoURL);
		     return false;
		 }
	
		String token=obj.toString();

		String userToken=req.getParameter(GlobalStatic.tokenKey);
		if(token.equals(userToken)){
			 return true;
		}
		
		request.setAttribute(GlobalStatic.errorTokentoURLKey, GlobalStatic.errorTokentoURL);
		
		return false;
		
	}

}
