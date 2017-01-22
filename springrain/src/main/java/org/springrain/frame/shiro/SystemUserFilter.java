package org.springrain.frame.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;

/**
 * 系统用户的过滤器，主要设置登陆界面
 * @author caomei
 */

@Component("systemuser")
public class SystemUserFilter extends BaseUserFilter {
	public SystemUserFilter(){
		//跳转到登录界面
	    super.setLoginUrl("/system/login");
	}
	
	@Override
	 protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		 boolean access=super.isAccessAllowed(request, response, mappedValue);
		 if(!access){
			 return access;
		 } 
		 
		 //已经登录用户,验证Referer
		 HttpServletRequest req=(HttpServletRequest) request;
		 
		 Object obj=req.getSession().getAttribute(GlobalStatic.tokenKey);
		 if(obj==null||(!obj.toString().startsWith("system_"))){//tokenKey必须是system_开头
			 Subject subject = SecurityUtils.getSubject();
		        if (subject != null) {           
		            subject.logout();
		        }
		    return false;
		 }
	
		String token=obj.toString();

		String userToken=req.getParameter(GlobalStatic.tokenKey);
		if(!token.equals(userToken)){
			try {
				WebUtils.issueRedirect(request, response, "/unauth");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		 return access;
	}

}
