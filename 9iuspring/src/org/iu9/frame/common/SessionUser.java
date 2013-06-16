package org.iu9.frame.common;

import javax.servlet.http.HttpSession;

import org.iu9.frame.interceptor.FWInterceptor;



/**
 * 当前登录用户信息,可以在bean中调用获取当前登录用户信息,例如 SessionUser.getUserId()获取当前登录人的userId
 * @copyright {@link 9iu.org}
 * @author 9iuspring<9iuorg@gmail.com>
 * @version  2013-03-19 11:08:15
 * @see org.iu9.frame.common.SessionUser
 */
public class SessionUser {


	public SessionUser() {
	}

	public static HttpSession getSession() {
		HttpSession session = FWInterceptor.sessionLocal.get();
		return session;
	}
	
	private static String getSessionAttribute(String key){
		HttpSession session = FWInterceptor.sessionLocal.get();
		if(session==null)
		return null;
		Object obj =  session.getAttribute(key);
		if(obj!=null)
			return obj.toString();
		return null;
	}

	public static  String getUserId() {
		return getSessionAttribute("9iu_session_userId");
	}

	public static String getUserCode() {
		return getSessionAttribute("9iu_session_userCode");
	
	}

	public static String getUserName() {
		return getSessionAttribute("9iu_session_userName");
	}

	public static String getEmail() {
		return getSessionAttribute("9iu_session_email");
	}

	


}
