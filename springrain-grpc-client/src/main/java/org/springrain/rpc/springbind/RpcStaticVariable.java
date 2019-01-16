package org.springrain.rpc.springbind;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

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

	// 存放 事务Id对应的 Condition
	public static Map<String, Condition> grpcConditionMap = new ConcurrentHashMap<>();
	// 存放 事务Id对应的 Lock
	public static Map<String, Lock> grpcLockMap = new ConcurrentHashMap<>();

	// 事务组的Id
	public static ThreadLocal<String> txGroupIdLocal = new ThreadLocal<>();
	
	// 记录rpc客户端调用的远程rpc信息,用于处理service方法内多次调用其他的service
	public static ThreadLocal<List<RemoteRpcTxDto>> remoteRpcTxLocal = new ThreadLocal<>();

	// 存放 事务是回滚还是提交的操作
	public static Map<String, Integer> grpcTxOperationMap = new ConcurrentHashMap<>();

}
