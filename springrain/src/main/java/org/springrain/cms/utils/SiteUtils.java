package org.springrain.cms.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SiteUtils {
	
	public static String getSiteURLPath(HttpServletRequest request){
		String path = request.getContextPath(); 
		if("/".equals(path)){
			path="";
		}
		String basePath = request.getScheme()+"://"+request.getServerName();
		if(80-request.getServerPort()!=0){
		  basePath=basePath+":"+request.getServerPort();
		}
		basePath=basePath+path;
		
		return basePath;
	}
	
	
	
	public static  String getRequestURL(HttpServletRequest request)throws Exception{
		StringBuffer uri=request.getRequestURL();
		String param=request.getQueryString();
		if(StringUtils.isNotBlank(param)){
			uri=uri.append("?").append(param);
		}
		return uri.toString();
	}
	
	
	
	
}
