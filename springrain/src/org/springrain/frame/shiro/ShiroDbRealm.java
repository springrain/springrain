package org.springrain.frame.shiro;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springrain.demo.entity.User;
import org.springrain.demo.service.IUserRoleMenuService;
/**
 * 关于shiro的缓存,我在这里说下.<br/>
 * 可以禁用shiro的缓存,调用spring的缓存,这样就省去了缓存的整合.<br/>
 * 其他框架遇到缓存问题,同样的思路解决.
 * @author 9iu.org
 *
 */
//认证数据库存储
@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {
	public Logger logger = Logger.getLogger(getClass());
	@Resource
	IUserRoleMenuService userRoleMenuService;
	public static final String HASH_ALGORITHM = "MD5";
	public static final int HASH_INTERATIONS = 1;
	private static final int SALT_SIZE = 8;
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
		if(StringUtils.isBlank(userId)){
			return null;
		}
		// 添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		try {
			sazi.addRoles(userRoleMenuService.getRolesAsString(userId));
			sazi.addStringPermissions(userRoleMenuService.getPermissionsAsString(userId));
		} catch (Exception e) {
			logger.error(e);
		}
	
		
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
			user = userRoleMenuService.findLoginUser(upToken.getUsername(), null);
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
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
		matcher.setHashIterations(HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}
}
