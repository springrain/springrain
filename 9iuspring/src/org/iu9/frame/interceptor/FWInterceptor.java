package org.iu9.frame.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基本权限过滤和记录访问日志的拦截器
 * 
 * @copyright {@link 9iu.org}
 * @author 9iuspring<9iuorg@gmail.com>
 * @version 2013-03-19 11:08:15
 * @see org.iu9.frame.interceptor.FWInterceptor
 */
public class FWInterceptor implements HandlerInterceptor {

	private Log logger = LogFactory.getLog("fw");
	public static ThreadLocal<HttpSession> sessionLocal = new ThreadLocal<HttpSession>();
	private List<String> excludeurl;
	private List<String> ipList = null; // 允许String httpinvoke列表
	private String loginurl = "/login";
	private String noqx = "/noqx";

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (sessionLocal != null) {
			sessionLocal.remove();
		}

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*
		 * String ip = IPUtils.getClientAddress(request); String requestURL =
		 * request.getRequestURL().toString(); String query =
		 * request.getQueryString(); StringBuffer sb = new StringBuffer();
		 * HttpSession session = request.getSession(false); Object o_userName =
		 * null; Object o_userCode = null;
		 * 
		 * String sessionId = null; if (session != null){ sessionId =
		 * session.getId(); o_userCode =
		 * session.getAttribute("9iu_session_userCode"); o_userName =
		 * session.getAttribute("9iu_session_userName"); }
		 * 
		 * String[] noFomat = new String[] { ".js", ".css", ".gif", ".jpg",
		 * ".swf", ".png" }; if (RegexValidateUtils.validateUrl(requestURL,
		 * noFomat) == false){ return true; }
		 * 
		 * if (CollectionUtils.isNotEmpty(ipList)) { if
		 * (ipList.contains(ip)&&requestURL.contains("/http/")) { StringBuffer
		 * sblog = new StringBuffer();
		 * sblog.append("userCode==").append(o_userCode
		 * ).append(",userName==").append(o_userName).append( ",sessionID==" +
		 * sessionId + ",ip==").append(ip).append(
		 * ",fwURL==").append(requestURL); logger.info(sblog); return true; } }
		 * if (o_userCode == null||StringUtils.isBlank(o_userCode.toString())) {
		 * if (isExcludeurl(requestURL)==false) {
		 * response.sendRedirect(loginurl); return false; } }
		 * 
		 * String username = String.valueOf(o_userCode);
		 * sb.append("userCode==").
		 * append(username).append(",userName==").append(o_userName).append(
		 * ",sessionID==" + sessionId + ",ip==").append(ip).append(
		 * ",fwURL==").append(requestURL);
		 * 
		 * 
		 * 
		 * if (query != null) { sb.append("?").append(query); }
		 * logger.info(sb.toString());
		 * 
		 * 
		 * 
		 * String _url=requestURL;
		 * 
		 * // 已 /pre 结尾的默认是 去掉对比 例如:进入
		 * 
		 * if(requestURL.endsWith("/pre")){ _url=requestURL.substring(0,
		 * requestURL.length()-4); }
		 * 
		 * Boolean isAccess = true; //添加你自己的权限校验, 校验_url 是否具有权限
		 * 
		 * 
		 * if(isAccess==false){ response.sendRedirect(noqx); return false; }
		 * sessionLocal.set(session);
		 */
		return true;
	}

	public List<String> getExcludeurl() {
		return excludeurl;
	}

	public void setExcludeurl(List<String> excludeurl) {
		this.excludeurl = excludeurl;
	}

	public List<String> getIpList() {
		return ipList;
	}

	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}

	public boolean isExcludeurl(String url) {
		if (StringUtils.isBlank(url))
			return true;
		if (CollectionUtils.isEmpty(excludeurl)) {
			return false;
		}
		boolean flag = false;
		for (String s : excludeurl) {
			Pattern pattern = Pattern.compile(s);
			Matcher matcher = pattern.matcher(url);
			boolean isExclude = matcher.matches();
			if (isExclude) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	public String getLoginurl() {
		return loginurl;
	}

	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}

	public String getNoqx() {
		return noqx;
	}

	public void setNoqx(String noqx) {
		this.noqx = noqx;
	}

}
