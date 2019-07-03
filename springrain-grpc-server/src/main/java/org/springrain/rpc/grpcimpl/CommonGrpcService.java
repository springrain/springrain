package org.springrain.rpc.grpcimpl;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcauto.GrpcCommonServiceGrpc;
import org.springrain.rpc.sessionuser.ShiroUser;
import org.springrain.rpc.springbind.RpcStaticVariable;
import org.springrain.rpc.util.FstSerializeUtils;

import com.google.protobuf.ByteString;

import io.seata.core.context.RootContext;

/**
 * 集成自动产生的java类,自定义自己的实现.总体思路是
 * 请求的class,方法,和参数做成二进制,通过grpc传递,实际是二次序列化,对性能有损耗,但是方便......
 * 
 * 使用seata实现数据库分布式事务
 * 
 * 
 * 
 * @author caomei
 *
 */
public class CommonGrpcService extends GrpcCommonServiceGrpc.GrpcCommonServiceImplBase {

	private static final Logger logger = LoggerFactory.getLogger(CommonGrpcService.class);

	private ApplicationContext applicationContext = null;

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
		GrpcCommonRequest grpcRequest = FstSerializeUtils.deserialize(commonRequest);

		// String beanName = grpcRequest.getBeanName();
		// 需要调用的类
		String className = grpcRequest.getClazz();
		// 获取获取参数
		Object[] args = grpcRequest.getArgs();
		// spring bean name
		String beanName = grpcRequest.getBeanName();
		// 当前登录用户对象信息
		ShiroUser shiroUser = grpcRequest.getShiroUser();

		Object bean = null;

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

		// 事务的入口,作为提交事务的依据,先判断是否有参数传递进来
		String txGroupId = grpcRequest.getTxGroupId();
		boolean bind = false;


		try {

			String xid = RootContext.getXID();

			if (StringUtils.isBlank(xid) && StringUtils.isNotBlank(txGroupId)) {
				RootContext.bind(txGroupId);
				bind = true;
			}

			// 执行service的方法
			Object result = invokeMethod(bean, grpcRequest.getMethod(), args, grpcRequest.getArgTypes(), shiroUser);

			// 设置结果状态
			grpcResponse.success(result);

			// 序列化需要返回的结果
			ByteString bytes = FstSerializeUtils.serialize(grpcResponse);
			// 封装成grpc传递的对象
			CommonResponse commonResponse = CommonResponse.newBuilder().setResponse(bytes).build();
			// grpc下一步处理
			responseObserver.onNext(commonResponse);
			// 完成传输
			responseObserver.onCompleted();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// String message = e.getClass().getName() + ": " + e.getMessage();
			// grpcResponse.error(message, e, e.getStackTrace());
		} finally {
			if (bind) {
				String unbindXid = RootContext.unbind();
				if (!txGroupId.equalsIgnoreCase(unbindXid)) {
					if (unbindXid != null) {
						RootContext.bind(unbindXid);
					}
				}
			}
		}

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
	 * 反射调用方法,也可以使用MethodUtils, 直接反射会造成形参和实参不对应的时候找不到方法,例如形参Object,参数Entity
	 * 
	 * @param bean
	 * @param method
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private Object invokeMethod(Object bean, String methodName, Object[] args, Class[] parameterTypes,
			ShiroUser shiroUser) throws Exception {

		if (shiroUser != null) {
			RpcStaticVariable.shiroUserLocal.set(shiroUser);
		}

		Method method = bean.getClass().getMethod(methodName, parameterTypes);
		// Object result = method.invoke(bean, args);

		// Method method = MethodUtils.getMatchingMethod(bean.getClass(), methodName,
		// parameterTypes);
		FastClass serviceFastClass = FastClass.create(bean.getClass());
		FastMethod serviceFastMethod = serviceFastClass.getMethod(method);
		Object result = serviceFastMethod.invoke(bean, args);

		RpcStaticVariable.shiroUserLocal.remove();
		return result;

	}

}
