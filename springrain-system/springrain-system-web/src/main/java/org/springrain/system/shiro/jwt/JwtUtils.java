package org.springrain.system.shiro.jwt;

import java.util.Date;

import org.springrain.system.manager.entity.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * TODO 在此加入类描述
 * @copyright 
 * @author 程相羽
 * @version 2019年3月6日 上午10:33:47
 * @see org.springrain.system.shiro.jwt.JwtUtils
 */
public class JwtUtils  {
	
	private static final long EXPIRE_TIME = 5 * 60 * 1000;
	
	/**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            if(jwt != null) {
            	return true;
            }
            return false;
        } catch (Exception exception) {
            return false;
        }
    }
    
    
    public static String getId(String token) {
    	try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    /**
            * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    public static Integer getUserType(String token) {
    	try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userType").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    
    public static Date getExpireDate(String token) {
    	DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt();
    }
    
	/**
	 * 加密生成数据
	 * 
	 * @param object
	 * @param maxAge
	 * @return
	 */
	public static <T> String sign(User user) {
		try {
			Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
			String token = JWT.create()
					.withClaim("id", user.getId())
					.withClaim("account", user.getAccount())
					.withClaim("userType", user.getUserType())
					.withExpiresAt(date).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
