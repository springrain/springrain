package org.springrain.rpc.grpcimpl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc;
import org.springrain.rpc.grpcauto.GrpcTransactionNoticeServiceGrpc.GrpcTransactionNoticeServiceBlockingStub;
import org.springrain.rpc.grpcauto.NoticeRequest;
import org.springrain.rpc.grpcauto.NoticeResponse;
import org.springrain.rpc.springbind.RemoteRpcTxDto;
import org.springrain.rpc.springbind.RpcStaticVariable;
import org.springrain.rpc.util.GRPCSerializeUtils;

import com.google.protobuf.ByteString;

/**
 * 集成自动产生的java类,自定义自己的实现.总体思路是
 * 请求的class,方法,和参数做成二进制,通过grpc传递,实际是二次序列化,对性能有损耗,但是方便......
 * 
 * 事务的逻辑应该都在服务端实现,入口一定是个service,必须保证彻底分离,不能混合......
 * 如果混合了,就需要在客户端监听事务提交,做为全局事务的提交,客户端的事务是spring的事务,很难操作,有个监听器,没有试
 * 所以,还是建议service彻底分开吧,别和web混搭了,入口只会是一个事务方法.
 * rpc调用完成,通过rpc返回结果,线程是等待状态,等待事务的提交或者回滚通知. 通过全局变量,两个线程共享数据.
 * 限制:不能使用spring事务.
 * 
 * 
 * @author caomei
 *
 */
public class CommonGrpcService extends GrpcCommonServiceGrpc.GrpcCommonServiceImplBase {

	private static final Logger logger = LoggerFactory.getLogger(CommonGrpcService.class);

	private ApplicationContext applicationContext = null;

	// 事务匹配的规则
	String pattern = ".*ServiceImpl.(save|update|delete)(.*)";
	// 创建 Pattern 对象
	Pattern r = Pattern.compile(pattern);

	public CommonGrpcService(ApplicationContext applicationContext) {

		this.applicationContext = applicationContext;
	}

	/**
	 * <pre>
	 * 处理请求
	 * </pre>
	 */
	@Override
	public void commonHandle(org.springrain.rpc.grpcauto.CommonRequest commonRequest,
			io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.CommonResponse> responseObserver) {

		// 把请求反序列化成正常对象,GrpcRequest
		GrpcCommonRequest grpcRequest = GRPCSerializeUtils.deserialize(commonRequest);

		// String beanName = grpcRequest.getBeanName();
		// 需要调用的类
		String className = grpcRequest.getClazz();
		// 获取获取参数
		Object[] args = grpcRequest.getArgs();
		//spring bean name
		String beanName=grpcRequest.getBeanName();


		Object bean = null;

		// 入口方法是否有事务
		boolean notx = false;
		try {
			if ((beanName != null) && (!"".equals(beanName))) {// 先按照beanName查找
				bean = getBeanByName(beanName);
			}

			if (bean == null) {// 按照类型找到springbean
				bean = getBean(Class.forName(className));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return;
		}




		// 初始化需要返回的对象
		GrpcCommonResponse grpcResponse = new GrpcCommonResponse();

		// 默认不是开始事务,如果txGroupId为空,可以认为是事务的开始方法.
		Integer transactionStart = 0;

		// 事务的入口,作为提交事务的依据,先判断是否有参数传递进来
		String txGroupId = grpcRequest.getTxGroupId();

		// 产生随机的事务Id
		String txId = UUID.randomUUID().toString();


		if (txGroupId == null || "".equals(txGroupId)) {
			txGroupId = RpcStaticVariable.txGroupIdLocal.get();
		}
		// 事务的入口,作为提交事务的依据
		if (txGroupId == null || "".equals(txGroupId)) {
			txGroupId = UUID.randomUUID().toString();
			transactionStart = 1;

			// 方法是不是没有事务
			notx = isnotx(bean.getClass().getName() + "." + grpcRequest.getMethod());
			if (notx) {// 如果没有事务
				txGroupId = "notx_" + txGroupId;
			} else if (grpcRequest.getAutocommit()) {// 如果有事务,而且是自动提交
				txGroupId = "autocommit_" + txGroupId;
			}
			RpcStaticVariable.txGroupIdLocal.set(txGroupId);
		}


		// 入口方法没有事务
		if (txGroupId.startsWith("notx_")) {
			notx = true;
		} else if (txGroupId.startsWith("autocommit_")) { // 入口方法有事务并且是自动提交
			grpcRequest.setAutocommit(true);
		}

		try {
			// 入口方法没有事务
			if (notx) {

				// 执行service的方法
				Object result = invokeMethod(bean, grpcRequest.getMethod(), args);

				// 返回事务对象id
				grpcResponse.setTxId(txId);
				// 返回事务组Id
				grpcResponse.setTxGroupId(txGroupId);
				// 设置结果状态
				grpcResponse.success(result);

				// 序列化需要返回的结果
				ByteString bytes = GRPCSerializeUtils.serialize(grpcResponse);
				// 封装成grpc传递的对象
				CommonResponse commonResponse = CommonResponse.newBuilder().setResponse(bytes).build();
				// grpc下一步处理
				responseObserver.onNext(commonResponse);
				// 完成传输
				responseObserver.onCompleted();

				if (1 - transactionStart == 0) {// 如果是入口,不需要额外处理,清理静态资源
					RpcStaticVariable.grpcConditionMap.remove(txId);
					RpcStaticVariable.grpcLockMap.remove(txId);
					RpcStaticVariable.txGroupIdLocal.remove();
					RpcStaticVariable.grpcTxOperationMap.remove(txId);
					RpcStaticVariable.remoteRpcTxLocal.remove();
				}

				return;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// String message = e.getClass().getName() + ": " + e.getMessage();
			// grpcResponse.error(message, e, e.getStackTrace());
			return;
		}

		// 手动开启事务
		PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class);

		TransactionStatus status = null;

		// 超时时间
		Integer timeout = grpcRequest.getTimeout();
		if (timeout == null) {
			timeout = 10000;
		}

		Lock lock = null;
		Condition condition = null;

		// 用于测试不使用分布式事务
		// transactionStart = 1;

		try {



			// 1.获取事务定义
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			// 2.设置事务隔离级别
			def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
			// 3.设置事务传播特性
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			// 设置超时时间
			def.setTimeout(timeout);
			// 3.获得事务状态
			status = transactionManager.getTransaction(def);

			// 执行service的方法
			Object result = invokeMethod(bean, grpcRequest.getMethod(), args);

			// 返回事务对象id
			grpcResponse.setTxId(txId);
			//返回事务组Id
			grpcResponse.setTxGroupId(txGroupId);
			// 设置结果状态
			grpcResponse.success(result);


			// 序列化需要返回的结果
			ByteString bytes = GRPCSerializeUtils.serialize(grpcResponse);
			// 封装成grpc传递的对象
			CommonResponse commonResponse = CommonResponse.newBuilder().setResponse(bytes).build();
			// grpc下一步处理
			responseObserver.onNext(commonResponse);
			// 完成传输
			responseObserver.onCompleted();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 事务回滚
			rollbackTransaction(transactionManager, status, grpcRequest.getAutocommit());
			// String message = e.getClass().getName() + ": " + e.getMessage();
			// grpcResponse.error(message, e, e.getStackTrace());
			unlockCondition(lock, txId);
			return;
		}
		try {

			// 如果是自动提交,不考虑分布式事务,不需要额外处理,直接提交事务
			if (grpcRequest.getAutocommit()) {
				commitTransaction(transactionManager, status, grpcRequest.getAutocommit());
				return;
			}

			if (1 - transactionStart == 0) {// 如果是入口,不需要额外处理,直接提交事务
				commitTransaction(transactionManager, status, grpcRequest.getAutocommit());
				return;
			}

			// 如果不是入口的方法,就需要锁定,等待入口的指令唤醒.

			lock = new ReentrantLock();
			condition = lock.newCondition();

			// 设置线程condition
			RpcStaticVariable.grpcConditionMap.put(txId, condition);
			// 设置线程lock
			RpcStaticVariable.grpcLockMap.put(txId, lock);

			lock.lock();// 请求锁
			// 等待线程被唤醒,超时自动解锁
			condition.await(timeout, TimeUnit.MILLISECONDS);

			// 1是提交事务,其他值是 回滚事务
			Integer operation = RpcStaticVariable.grpcTxOperationMap.get(txId);
			if (1 - operation == 0) {// 提交事务
				commitTransaction(transactionManager, status, grpcRequest.getAutocommit());
			} else {
				rollbackTransaction(transactionManager, status, grpcRequest.getAutocommit());
			}


		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		} finally {
			unlockCondition(lock, txId);
			RpcStaticVariable.grpcTxOperationMap.remove(txId);
			if (1 - transactionStart == 0) {
				RpcStaticVariable.txGroupIdLocal.remove();
			}
		}
	}

	// 提交事务
	private void commitTransaction(PlatformTransactionManager transactionManager, TransactionStatus status,
			boolean autocommit) {

		if (autocommit) {// 如果是自动提交事务
			// 提交事务
			transactionManager.commit(status);
			RpcStaticVariable.remoteRpcTxLocal.remove();
			return;
		}

		List<RemoteRpcTxDto> list = RpcStaticVariable.remoteRpcTxLocal.get();
		if (list == null || list.isEmpty() || list.size() < 1) {
			// 提交事务
			transactionManager.commit(status);
			return;
		}

		for (int i = list.size() - 1; i >= 0; i--) {
			RemoteRpcTxDto dto = list.get(i);
			// 获取事务对象
			String txId = dto.getTxId();
			GrpcTransactionNoticeServiceBlockingStub stub = GrpcClient
					.getTransactionNoticeBlockingStub(dto.getRpcHost(), dto.getRpcPort());
			NoticeRequest noticeRequest = NoticeRequest.newBuilder().setTxId(txId).setTxGroupId(dto.getTxGroupId())
					.setOperation(1).build();// 提交的通知
			NoticeResponse noticeResponse = GrpcClient.sendNotice(stub, noticeRequest);
		}

		// 提交事务
		transactionManager.commit(status);
		RpcStaticVariable.remoteRpcTxLocal.remove();
	}

	// 回滚事务
	private void rollbackTransaction(PlatformTransactionManager transactionManager, TransactionStatus status,
			boolean autocommit) {

		if (autocommit) {// 如果是自动提交事务
			// 回滚事务
			transactionManager.rollback(status);
			RpcStaticVariable.remoteRpcTxLocal.remove();
			return;
		}

		List<RemoteRpcTxDto> list = RpcStaticVariable.remoteRpcTxLocal.get();
		if (list == null || list.isEmpty() || list.size() < 1) {
			// 提交事务
			transactionManager.rollback(status);
			return;
		}

		for (int i = list.size() - 1; i >= 0; i--) {
			RemoteRpcTxDto dto = list.get(i);
			// 获取事务对象
			String txId = dto.getTxId();
			GrpcTransactionNoticeServiceBlockingStub stub = GrpcClient
					.getTransactionNoticeBlockingStub(dto.getRpcHost(), dto.getRpcPort());
			NoticeRequest noticeRequest = NoticeRequest.newBuilder().setTxId(txId).setTxGroupId(dto.getTxGroupId())
					.setOperation(0).build();// 回滚的通知
			NoticeResponse noticeResponse = GrpcClient.sendNotice(stub, noticeRequest);
		}

		// 回滚事务
		transactionManager.rollback(status);
		RpcStaticVariable.remoteRpcTxLocal.remove();
	}

	private void unlockCondition(Lock lock, String txId) {
		RpcStaticVariable.grpcConditionMap.remove(txId);
		RpcStaticVariable.grpcLockMap.remove(txId);
		RpcStaticVariable.txGroupIdLocal.remove();
		if (lock == null) {
			return;
		}
		lock.unlock();

	}



	/**
	 * 获取 Service Bean
	 */
	private Object getBean(Class clazz) {

		try {
			Object bean = applicationContext.getBean(clazz);
			return bean;
		} catch (BeansException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	private Object getBeanByName(String beanName) {

		try {
			Object bean = applicationContext.getBean(beanName);
			return bean;
		} catch (BeansException e) {
			return null;
		}
	}

	/**
	 * 获取参数类型
	 */
	/*
	 * private Class[] getParameterTypes(Object[] parameters) { if (parameters ==
	 * null) { return null; } Class[] clazzArray = new Class[parameters.length];
	 * 
	 * 
	 * for (int i = 0; i < parameters.length; i++) { if (parameters[i] == null) {
	 * clazzArray[i] = null; continue; } clazzArray[i] = parameters[i].getClass();
	 * 
	 * } return clazzArray; }
	 */
	/**
	 * 判断调用的方法是否没有事务
	 * 
	 * @param methodPath
	 * @return
	 */
	private boolean isnotx(String methodPath) {

		if (methodPath == null) {
			return true;
		}

		boolean matches = r.matcher(methodPath).matches();
		return !matches;
	}
	
	
	
	/**
	 * 反射调用方法,也可以使用MethodUtils, 直接反射会造成形参和实参不对应的时候找不到方法,例如形参Object,参数Entity
	 * 
	 * @param bean
	 * @param method
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private Object invokeMethod(Object bean, Method method, Object[] args) throws Exception {

			FastClass serviceFastClass = FastClass.create(bean.getClass());
		FastMethod serviceFastMethod = serviceFastClass.getMethod(method);
			Object result = serviceFastMethod.invoke(bean, args);
			return result;

	}


	

}
