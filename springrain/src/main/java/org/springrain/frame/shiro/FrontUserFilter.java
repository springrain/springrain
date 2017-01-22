package org.springrain.frame.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.GlobalStatic;

/**
 * 前台用户的 过滤器，主要设置登陆界面
 * @author caomei
 *
 */


@Component("frontuser")
public class FrontUserFilter extends BaseUserFilter {
	
	public FrontUserFilter(){
		//跳转到登录界面
		super.setLoginUrl("/login");
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


