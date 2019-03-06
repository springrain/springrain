package org.springrain.system.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt令牌
 * 
 * @copyright
 * @author 程相羽
 * @version 2019年3月6日 上午9:52:12
 * @see org.springrain.system.shiro.jwt.JwtToken
 */
public class JwtToken implements AuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1394323958441779458L;
	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
