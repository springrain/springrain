package org.iu9.frame.shiro;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.iu9.testdb1.entity.User;
import org.iu9.testdb1.service.IUserRoleMenuService;
import org.springframework.stereotype.Component;

//认证数据库存储
@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {
	public Logger logger = Logger.getLogger(getClass());
	@Resource
	IUserRoleMenuService userRoleMenuService;

	public ShiroDbRealm() {
		// super.setAuthenticationCacheName(authenticationCacheName);

	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principalCollection) {

		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
		// String userId = (String)
		// principalCollection.fromRealm(getName()).iterator().next();
		String userId = shiroUser.getId();
		// 取当前用户
		User user = null;
		try {
			user = userRoleMenuService.findUserAndMenu(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		// 添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		sazi.addRoles(user.getRolesAsString());
		sazi.addStringPermissions(user.getPermissionsAsString());
		return sazi;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		/*
		String pwd = new String(upToken.getPassword());
		if (StringUtils.isNotBlank(pwd)) {
			pwd = DigestUtils.md5Hex(pwd);
		}
*/
		// 调用业务方法
		User user = null;
		try {
			user = userRoleMenuService.findLoginUser(upToken.getUsername(), new String(upToken.getPassword()));
		} catch (Exception e) {
			logger.error(e);
		}

		if (user != null) {
			// 要放在作用域中的东西，请在这里进行操作
			// SecurityUtils.getSubject().getSession().setAttribute("c_user",
			// user);
			// byte[] salt = EncodeUtils.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(new ShiroUser(user),user.getPassword(),getName());
		}
		// 认证没有通过
		return null;
	}

}
