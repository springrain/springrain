package org.springrain.rpc.springbind;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springrain.rpc.annotation.RpcServiceMethodAnnotation;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc.GrpcCommonServiceBlockingStub;
import org.springrain.rpc.grpcimpl.GrpcClient;
import org.springrain.rpc.grpcimpl.GrpcCommonException;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;
import org.springrain.rpc.sessionuser.SessionUser;

import io.seata.core.context.RootContext;

/**
 * 代理grpc的service服务
 * 
 * seata处理分布式事务,假设调用链是 A-->B-->C-->D
 * 
 * 需要测试的事务场景 1.单体项目事务测试 2.远程调用,事务测试
 * 3.远程调用,spring本地事务混合调用.例如服务A内update方法,方法内有远程调用了B服务的update方法.
 * 4.远程调用,多次修改造成的数据冲突,回滚时会不会有问题
 * 
 * @author caomei
 *
 * @param <T>
 */
public class GrpcServiceProxy<T> implements InvocationHandler {

	private GrpcCommonRequest grpcCommonRequest;

	private String rpcHost;

	private Integer rpcPort;

	// 事务匹配的规则
	String pattern = ".*Service.(save|update|delete)(.*)";
	// 创建 Pattern 对象
	Pattern r = Pattern.compile(pattern);

	public GrpcServiceProxy(String rpcHost, Integer rpcPort, GrpcCommonRequest grpcRequest) {
		this.rpcHost = rpcHost;
		this.rpcPort = rpcPort;
		this.grpcCommonRequest = grpcRequest;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// 初始化请求数据
		GrpcCommonRequest grpRequest = new GrpcCommonRequest();
		grpRequest.setArgs(args);
		grpRequest.setMethod(method.getName());
		grpRequest.setBeanName(grpcCommonRequest.getBeanName());
		grpRequest.setClazz(grpcCommonRequest.getClazz());
		grpRequest.setTimeout(grpcCommonRequest.getTimeout());
		grpRequest.setVersionCode(grpcCommonRequest.getVersionCode());
		grpRequest.setAutocommit(grpcCommonRequest.getAutocommit());
		grpRequest.setArgTypes(method.getParameterTypes());


		// 获取方法上的RpcServiceMethodAnnotation注解内容
		RpcServiceMethodAnnotation rpcServiceMethodAnnotation = method.getAnnotation(RpcServiceMethodAnnotation.class);
		if (rpcServiceMethodAnnotation != null) {
			grpRequest.setTimeout(rpcServiceMethodAnnotation.timeout());
			grpRequest.setAutocommit(rpcServiceMethodAnnotation.autocommit());

		}
		// 传递shiroUser对象
		if (SessionUser.getShiroUser() != null) {
			grpRequest.setShiroUser(SessionUser.getShiroUser());
		}

		// 需要考虑是调用链入口还是中间节点

		// 获取全局的xid
		String txGroupId = RootContext.getXID();

		if (StringUtils.isNotBlank(txGroupId)) {// 如果有全局事务
			// 设置xid
			grpRequest.setTxGroupId(txGroupId);
		}

		GrpcCommonServiceBlockingStub blockingStub = GrpcClient.getCommonServiceBlockingStub(rpcHost, rpcPort);

		// grpc客户端.发起请求
		GrpcCommonResponse grpcResponse = GrpcClient.commonHandle(blockingStub, grpRequest);

		// 处理异常,异常是在服务端抛出的.
		if (grpcResponse.getStatus() >= 400) {
			Throwable throwable = grpcResponse.getException();
			// 业务调用本身的异常

			GrpcCommonException exception = new GrpcCommonException(
					throwable.getClass().getName() + ": " + throwable.getMessage());
			StackTraceElement[] exceptionStackTrace = exception.getStackTrace();
			StackTraceElement[] responseStackTrace = grpcResponse.getStackTrace();
			StackTraceElement[] allStackTrace = Arrays.copyOf(exceptionStackTrace,
					exceptionStackTrace.length + responseStackTrace.length);
			System.arraycopy(responseStackTrace, 0, allStackTrace, exceptionStackTrace.length,
					responseStackTrace.length);
			exception.setStackTrace(allStackTrace);



		}


		// 返回结果
		return grpcResponse.getResult();
	}

	/**
	 * 判断调用的方法是否没有事务.暂时未找到spring的验证方法,临时实现,待完善.
	 * 
	 * @param methodPath
	 * @return
	 */
	private boolean isSpringTx(String methodPath) {

		if (methodPath == null) {
			return false;
		}

		boolean matches = r.matcher(methodPath).matches();
		return matches;
	}

}
