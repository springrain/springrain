package org.springrain.frame.shiro;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;

/**
 * 系统用户的过滤器，主要设置登陆界面
 * @author caomei
 */

@Component("systemuser")
public class SystemUserFilter extends UserFilter {
	public SystemUserFilter(){
		//跳转到登录界面
	    super.setLoginUrl("/system/login");
	}

}
