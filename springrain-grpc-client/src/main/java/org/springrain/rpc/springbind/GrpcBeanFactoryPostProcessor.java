package org.springrain.rpc.springbind;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;

/**
 * 在spring初始化之前,通过beanFactory先注入需要代理的bean,不然springbean初始化会异常.
 * 需要实现EnvironmentAware,setEnvironment
 * 这样才能正常获取到Environment变量,参考:https://blog.csdn.net/xiejx618/article/details/50413412
 * 
 * @author caomei
 *
 */
@Component("grpcBeanFactoryPostProcessor")
public class GrpcBeanFactoryPostProcessor implements BeanFactoryPostProcessor, EnvironmentAware {
	private static final Logger logger = LoggerFactory.getLogger(GrpcBeanFactoryPostProcessor.class);


	private Environment environment;


	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		try {
			// 初始化代理bean,必须在bean加载前处理好,因为spring初始化找不到实现会报错,提前把接口的实现注册上去就可以了.
			initRpcServiceImpl(beanFactory);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 扫描RpcServiceAnnotation的注解接口,找到默认实现,如果没有,就启动RPC代理
	 * 
	 * @throws Exception
	 */
	private void initRpcServiceImpl(ConfigurableListableBeanFactory beanFactory) throws Exception {
		
		
		String basepackagepath = environment.getProperty("springrain.basepackagepath");
		basepackagepath = basepackagepath.replaceAll("\\.", "/");

		String classPath = basepackagepath + "/**/service/*.class";


		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
		Resource[] resources = pmrpr
				.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + classPath);

		for (Resource resource : resources) {

			URI uri = resource.getURI();
			String rpcServiceClassName = uri.toString();

			rpcServiceClassName = rpcServiceClassName.substring(rpcServiceClassName.lastIndexOf(basepackagepath),
					rpcServiceClassName.lastIndexOf(".class"));
			rpcServiceClassName = rpcServiceClassName.replaceAll("/", ".");

			Class<?> clazz = Class.forName(rpcServiceClassName);

			if ((!clazz.isInterface()) || (!clazz.isAnnotationPresent(RpcServiceAnnotation.class))) {// 只处理有@RpcService注解的
																										// 接口
				continue;
			}

			// 根据包名规则,组装接口默认实现的class路径,如果类存在,就认为是本机加载,找不到就启用RPC
			String classSimpleName = clazz.getSimpleName();
			String classImplSimpleName = classSimpleName.substring(1, classSimpleName.length()) + "Impl";
			String rpcServiceImplClassName = rpcServiceClassName.replace(".service.", ".service.impl.");
			rpcServiceImplClassName = rpcServiceImplClassName.substring(0, rpcServiceImplClassName.lastIndexOf("."))
					+ "." + classImplSimpleName;

			try {
				Class rpcServiceImplClass = Class.forName(rpcServiceImplClassName);
				if (rpcServiceImplClass != null) {
					continue;
				}

			} catch (Exception e) {
				logger.error("未找到接口" + clazz.getName() + "的实现类" + rpcServiceImplClassName + ",开始RPC调用远程实现");
			}
			
			/*
			 * // 因为有远程调用的service,设置seata为启用状态. if (GlobalStatic.seataGlobalEnable) { if
			 * (!GlobalStatic.seataEnable) { GlobalStatic.seataEnable = true; } } else {//
			 * 如果全局禁用seata,就设置为false GlobalStatic.seataEnable = false; }
			 */
			

			RpcServiceAnnotation rpcServiceAnnotation = clazz.getAnnotation(RpcServiceAnnotation.class);

			String rpcHost = rpcServiceAnnotation.rpcHost();
			Integer rpcPort = rpcServiceAnnotation.rpcPort();
			String beanName = rpcServiceAnnotation.beanName();

			if (rpcHost == null || rpcHost.equals("")) {
				rpcHost = RpcStaticVariable.rpcHost;
			}

			if (rpcPort == null || rpcPort <= 0) {
				rpcPort = RpcStaticVariable.rpcPort;
			}

			if (beanName == null || beanName.equals("")) {
				beanName = clazz.getName();
			}
			// 开始GRPC请求调用
			GrpcCommonRequest grpcRequest = new GrpcCommonRequest();
			grpcRequest.setClazz(clazz.getName());
			grpcRequest.setBeanName(beanName);
			grpcRequest.setTimeout(rpcServiceAnnotation.timeout());
			grpcRequest.setVersionCode(rpcServiceAnnotation.versionCode());
			grpcRequest.setAutocommit(rpcServiceAnnotation.autocommit());

			// 创建接口实现的GRPC代理类
			// Object invoker = new Object();
			InvocationHandler invocationHandler = new GrpcServiceProxy<>(rpcHost, rpcPort, grpcRequest);
			Object proxy = Proxy.newProxyInstance(RpcServiceAnnotation.class.getClassLoader(), new Class[] { clazz },
					invocationHandler);
			// 手动注册 springbean
			beanFactory.registerSingleton(beanName, proxy);

		}

		/**
		 * // 初始化seata注册 if (GlobalStatic.seataEnable) {
		 * RMClient.init(GlobalStatic.seataApplicationId,
		 * GlobalStatic.seataTransactionServiceGroup);
		 * TMClient.init(GlobalStatic.seataApplicationId,
		 * GlobalStatic.seataTransactionServiceGroup); }
		 */

	}


	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
