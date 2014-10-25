package org.springrain.weixin.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.weixin.util.WeiXinUtils;
@Component("weiXinOauth2")
public class ShiroWeiXinOauth2Filter extends
		PermissionsAuthorizationFilter {
	
	
	public ShiroWeiXinOauth2Filter(){
		//setLoginUrl("/weixin/f/index");
		//setUnauthorizedUrl("/weixin/f/index");
	}
	
	
	
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		 Subject user = SecurityUtils.getSubject();
		 if(user.isAuthenticated()){
			 return true;
		 }
		
		HttpServletRequest req = (HttpServletRequest) request;

	
		String path = req.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		String redirect_uri=basePath+"/weixin/f/oauth2?1=1";
		String code = WeiXinUtils.oauth.getCode(redirect_uri);
		
		try {
			 Map<String, String> token = WeiXinUtils.oauth.getToken(code);
			 
			String access_token= token.get("access_token");
			String openId=token.get("openid");
			if(openId==null){
				return false;
			}
			WeiXinUtils.createShiroUserByOpenId(openId, request, response);
			

             return true;
			 
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return false;
		}
		

	}
}
