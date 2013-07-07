package org.iu9.frame.shiro;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
@Component("myRealm")
public class DbAuthRealm extends AuthorizingRealm {
	public Logger logger=Logger.getLogger(getClass());
	@Resource
	IUserRoleMenuService  userRoleMenuService;
	 public DbAuthRealm() {
		// super.setAuthenticationCacheName(authenticationCacheName);
		 
	 }
	/**
	 public DbAuthRealm() {
	       super();
	       // 设置认证token的实现类为用户名密码模式
	       this.setAuthenticationTokenClass(UsernamePasswordToken.class);
	       //设置验证方式，用户自行设定密码加密方式
	       this.setCredentialsMatcher(new CredentialsMatcher() {   
	           @Override
	           public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info) {
	              
	              UsernamePasswordToken upToken = (UsernamePasswordToken)token;
	              String account=upToken.getUsername();
	              String pwd = new String(upToken.getPassword());
	              if(StringUtils.isNotBlank(pwd)){
	            	  pwd=  DigestUtils.md5Hex(pwd);
	              }
	              
	              String userId=null;
				try {
					userId = userRoleMenuService.findLoginUserId(account, pwd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	           if(StringUtils.isBlank(userId)){
	        	   return false;
	           }
	           return true;
	              
	           }
	       });
	    }
	
	 */
	 //授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();  
		 //String userId = (String) principalCollection.fromRealm(getName()).iterator().next();
		  String userId=shiroUser.getId();
	       //取当前用户
	       User user=null;
		try {
			user = userRoleMenuService.findUserAndMenu(userId);
		} catch (Exception e) {
			logger.error(e);
		}
	       //添加角色及权限信息
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
		  String pwd = new String(upToken.getPassword());
          if(StringUtils.isNotBlank(pwd)){
        	  pwd=  DigestUtils.md5Hex(pwd);
          }
          
		  
	       //调用业务方法
	       User user=null;
		try {
			user = userRoleMenuService.findLoginUser(upToken.getUsername(), pwd);
		} catch (Exception e) {
			logger.error(e);
		}
	 
	       if(user != null) {
	           //要放在作用域中的东西，请在这里进行操作
	          // SecurityUtils.getSubject().getSession().setAttribute("c_user", user);
	    	   //byte[] salt = EncodeUtils.decodeHex(user.getSalt());  
	          return new SimpleAuthenticationInfo(new ShiroUser(user),user.getPassword(), this.getName());
	       }
	       //认证没有通过
	       return null;
	}

}
