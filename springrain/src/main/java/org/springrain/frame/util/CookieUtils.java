package org.springrain.frame.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
	private CookieUtils(){
		throw new IllegalAccessError("工具类不能实例化");
	}
	

	public static String getCookieValue(HttpServletRequest request,String key){
		Cookie[] cookies = request.getCookies();
		
		if(cookies==null){
			return null;
		}
		
		
		for (Cookie cookie:cookies) {
			if(cookie.getName().equalsIgnoreCase(key)){ //获取键 
			    return cookie.getValue();    //获取值 
			}
		}
		
		
		return null;
	}
}
