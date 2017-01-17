package org.springrain.cms.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class SiteUtils {
	private static ThreadLocal<Map<String,String>> siteThreadLocal = new ThreadLocal<Map<String,String>>(); 
	
	public static void setSiteInfo(Map<String,String> map){
		if(map==null){
			return;
		}
		siteThreadLocal.set(map);
	} 
	
	
	private static String getMapValue(String key){
	Map<String, String> map = siteThreadLocal.get();
		
		if(map==null){
			return null;
		}
		
		return map.get(key);
	}
	
	
	public static String getCurrentSiteId(){
		return getMapValue("siteId");
	}
	
	public static String getCurrentChannelId(){
		return getMapValue("channelId");
	}
	public static String getCurrentContentId(){
		return getMapValue("contentId");
	}
	
	
	public static void removeSiteInfo(){
		 siteThreadLocal.remove();
	}
	
	
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
