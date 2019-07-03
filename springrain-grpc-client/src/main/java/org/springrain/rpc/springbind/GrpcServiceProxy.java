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
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import io.seata.tm.api.TransactionalExecutor;

/**
 * 代理grpc的service服务
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
		// 方法的全路径
		String methodPath = grpRequest.getClazz() + "." + grpRequest.getMethod();

		boolean istx = false;

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

		// 获取全局的xid
		String txGroupId = RootContext.getXID();

		if (StringUtils.isNotBlank(txGroupId)) {// 如果有全局事务
			istx = true;
			// 设置xid
			grpRequest.setTxGroupId(txGroupId);
		} else {// 如果没有全局事务,判断此方法是否具有spring事务
			istx = isSpringTx(methodPath);
		}
		// 1. 获取当前全局事务实例或创建新的实例
		GlobalTransaction tx = null;
		// 如果没有全局事务
		if (StringUtils.isBlank(txGroupId) && istx) {
			// 1. 获取当前全局事务实例或创建新的实例
			tx = GlobalTransactionContext.getCurrentOrCreate();

			// 2. 开启全局事务
			try {
				tx.begin(grpRequest.getTimeout(), methodPath);
				txGroupId = tx.getXid();
			} catch (TransactionException txe) {
				// 2.1 开启失败
				throw new TransactionalExecutor.ExecutionException(tx, txe, TransactionalExecutor.Code.BeginFailure);
			}
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

			try {
				// 全局回滚
				if (tx != null) {
					tx.rollback();
				}

				// 3.1 全局回滚成功：抛出原始业务异常
				throw new TransactionalExecutor.ExecutionException(tx, TransactionalExecutor.Code.RollbackDone,
						exception);

			} catch (TransactionException txe) {
				// 3.2 全局回滚失败：
				throw new TransactionalExecutor.ExecutionException(tx, txe, TransactionalExecutor.Code.RollbackFailure,
						throwable);

			}

		}

		// 4. 全局提交
		try {
			if (tx != null) {
				tx.commit();
			}

		} catch (TransactionException txe) {
			// 4.1 全局提交失败：
			throw new TransactionalExecutor.ExecutionException(tx, txe, TransactionalExecutor.Code.CommitFailure);

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
