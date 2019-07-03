package org.springrain.rpc.springbind;

import org.springrain.rpc.sessionuser.ShiroUser;

/**
 * 可以抽成配置文件,测试使用
 * 
 * @author caomei
 *
 */
public class RpcStaticVariable {
	public static String rpcHost = "127.0.0.1";
	public static Integer rpcPort = 5551;

	public static Boolean isRpcServer = true;
	public static Boolean isRpcClient = true;

	// 存放sessionUser对象
	public static ThreadLocal<ShiroUser> shiroUserLocal = new ThreadLocal<>();




}
