package org.springrain.system.shiro.jwt;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.system.manager.entity.User;
import org.springrain.system.manager.service.IUserRoleMenuService;

/**
 * TODO 在此加入类描述
 * @copyright 
 * @author 程相羽
 * @version 2019年3月6日 上午10:11:40
 * @see org.springrain.system.shiro.jwt.JwtRealm
 */
public class JwtRealm extends AuthorizingRealm {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IUserRoleMenuService userRoleMenuService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String jwtToken = principals.toString();
		//超时判断
		Date expireTime = JwtUtils.getExpireDate(jwtToken);
		if(expireTime.getTime() < System.currentTimeMillis()) {
			return null;
		}
		
		// 添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		try {
			String userId = JwtUtils.getId(jwtToken);
			sazi.addStringPermissions(userRoleMenuService.getPermissionsAsString(userId));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sazi;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
			throws AuthenticationException {
		
		String jwtToken = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userName = JwtUtils.getUsername(jwtToken);
        if (userName == null) {
            throw new AuthenticationException("token无效");
        }
        
        Integer userType = JwtUtils.getUserType(jwtToken);
        User user = null;
		try {
			user = userRoleMenuService.findLoginUser(userName, null, userType);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
 
        if (!JwtUtils.verify(jwtToken, userName, user.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }
 
        return new SimpleAuthenticationInfo(jwtToken, jwtToken, "jwt_realm");
	}
	
	
}
