package org.springrain.frame.shiro;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.ReturnDatas;


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
		 //System.out.println(referer+":"+req.getHeader("X-Requested-With"));
		 if(StringUtils.isBlank(referer)||(!referer.contains(request.getServerName()))){
			 Subject subject = SecurityUtils.getSubject();
		        if (subject != null) {           
		            subject.logout();
		        }
			 return false;
		 }
		 
		 
		 
		 return access;
	 }
	
	@Override
	  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		     HttpServletRequest req=(HttpServletRequest) request;
		     HttpServletResponse res=(HttpServletResponse) response;
		     if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {// ajax请求,返回JSON
		    	 res.setCharacterEncoding("UTF-8");
		    	 res.setContentType("application/json;charset=UTF-8");
			     PrintWriter out = res.getWriter();
			     ReturnDatas returnDatas=ReturnDatas.getErrorReturnDatas();
			     returnDatas.setStatusCode("relogin");
			     returnDatas.setMessage("登录超时,请重新登录");
			     out.println(JsonUtils.writeValueAsString(returnDatas));
			     out.flush();
			     out.close();
			     return false;
			    }
		     
		     
		     //正常http请求
	         String loginUrl= getLoginUrl();	
	         StringBuffer url=req.getRequestURL();
	         String query=req.getQueryString();
	         if(StringUtils.isNotBlank(query)){
	        	 url=url.append("?").append(query);
	         }
	        
	         Map<String,String> parMap=new HashMap<String,String>();
	         parMap.put("gotourl", url.toString());
		
	         WebUtils.issueRedirect(request, response, loginUrl,parMap);
	        return false;
	    }

}
