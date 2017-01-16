package org.springrain.cms.utils;

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
}
