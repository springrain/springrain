package org.springrain.frame.shiro;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;
@Component("frontuser")
public class FrontUserFilter extends UserFilter {
	
	public FrontUserFilter(){
		//跳转到登录界面
		super.setLoginUrl("/login");
	}

}
