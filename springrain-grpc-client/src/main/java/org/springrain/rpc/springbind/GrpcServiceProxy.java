package org.springrain.rpc.springbind;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springrain.rpc.annotation.RpcServiceMethodAnnotation;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc.GrpcCommonServiceBlockingStub;
import org.springrain.rpc.grpcimpl.GrpcClient;
import org.springrain.rpc.grpcimpl.GrpcCommonException;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;

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

		// 获取方法上的RpcServiceMethodAnnotation注解内容
		RpcServiceMethodAnnotation rpcServiceMethodAnnotation = method.getAnnotation(RpcServiceMethodAnnotation.class);
		if (rpcServiceMethodAnnotation != null) {
			grpRequest.setTimeout(rpcServiceMethodAnnotation.timeout());
			grpRequest.setAutocommit(rpcServiceMethodAnnotation.autocommit());

		}


		// 服务端又调用其他RPC服务时,就是客户端了,groupId是在作为服务端解析时放到了txGroupIdLocal,再请求其他RPC的时候,先获取一下txGroupId,
		// 如果参数中有groupId,说明是后续方法,不是事务的入口,服务端线程会等待解锁
		String txGroupId = RpcStaticVariable.txGroupIdLocal.get();
		if (txGroupId != null) {
			grpRequest.setTxGroupId(txGroupId);
			// 如果入口方法是autocommit_开头,覆盖前面的设置为true
			if (txGroupId.startsWith("autocommit_")) {
				grpRequest.setAutocommit(true);
			}
		}

		GrpcCommonServiceBlockingStub blockingStub = GrpcClient.getCommonServiceBlockingStub(rpcHost, rpcPort);

		// grpc客户端.发起请求
		GrpcCommonResponse grpcResponse = GrpcClient.commonHandle(blockingStub, grpRequest);

		// 处理异常,异常是再服务端抛出的,直接在服务端代码里回滚了.如果还未兼容spring事务,因为需要在客户端事务监听里,回滚所有事务.
		if (500 == grpcResponse.getStatus()) {
			Throwable throwable = grpcResponse.getException();
			GrpcCommonException exception = new GrpcCommonException(throwable.getClass().getName() + ": " + throwable.getMessage());
			StackTraceElement[] exceptionStackTrace = exception.getStackTrace();
			StackTraceElement[] responseStackTrace = grpcResponse.getStackTrace();
			StackTraceElement[] allStackTrace = Arrays.copyOf(exceptionStackTrace,
					exceptionStackTrace.length + responseStackTrace.length);
			System.arraycopy(responseStackTrace, 0, allStackTrace, exceptionStackTrace.length,
					responseStackTrace.length);
			exception.setStackTrace(allStackTrace);
			throw exception;
		}
		
		// 如果是立即提交事务,就不需要再做任何处理了.
		if (grpRequest.getAutocommit()) {
			// 返回结果
			return grpcResponse.getResult();
		}

		// 一个事务周期,可能会有多次请求rpc的情况,记录到remoteRpcTxLocal,用于事务提交或者回滚时,通知其他RPC服务.
		// 可以写个这个这或者el表达式,验证方法是否需要事务操作.如果在事务内,就有事务.
		List<RemoteRpcTxDto> listRemoteRpcTxDto = RpcStaticVariable.remoteRpcTxLocal.get();
		if (listRemoteRpcTxDto == null) {
			listRemoteRpcTxDto = new ArrayList<>();
			RpcStaticVariable.remoteRpcTxLocal.set(listRemoteRpcTxDto);
		}
		RemoteRpcTxDto dto = new RemoteRpcTxDto();
		dto.setRpcHost(rpcHost);
		dto.setRpcPort(rpcPort);
		dto.setTxGroupId(grpcResponse.getTxGroupId());
		dto.setTxId(grpcResponse.getTxId());
		dto.setVersionCode(grpcResponse.getVersionCode());

		// 请求主体作为依据,主要是事务相关,入口的请求方法为依据
		dto.setTimeout(grpRequest.getTimeout());
		dto.setAutocommit(grpRequest.getAutocommit());
		listRemoteRpcTxDto.add(dto);

		// 因为暂时不兼容spring的事务,也要求彻底分离,所以提交事务实际是在服务端完成的

		// 返回结果
		return grpcResponse.getResult();
	}

}
