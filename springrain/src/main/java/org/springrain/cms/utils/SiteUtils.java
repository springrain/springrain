package org.springrain.cms.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SiteUtils {
	private static ThreadLocal<String> siteThreadLocal = new ThreadLocal<String>(); 
	
	public static void setSiteId(String siteId){
		if(StringUtils.isBlank(siteId)){
			return;
		}
		siteThreadLocal.set(siteId);
	} 
	
	public static String getSiteId(){
		return siteThreadLocal.get();
	}
	public static void removeSiteId(){
		 siteThreadLocal.remove();
	}
	
	
	public static String getSiteDomain(HttpServletRequest request){
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName();
		if(80==request.getServerPort()){
			basePath=basePath;
		}else{
			basePath=basePath+":"+request.getServerPort();
		}
		basePath=basePath+path+"/";
		
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
