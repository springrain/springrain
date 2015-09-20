package org.springrain.frame.shiro;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;
@Component("systemuser")
public class SystemUserFilter extends UserFilter {
	public SystemUserFilter(){
		//跳转到登录界面
	    super.setLoginUrl("/system/login");
	}

}
