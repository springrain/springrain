package org.springrain.rpc.grpcimpl;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 接受请求,所有的请求都封装成springbean的调用. 序列化成二进制,然后再经过grpc传输
 * 
 * @author caomei
 *
 */
public class GrpcCommonRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 接口
	 */
	private String clazz;

	/**
	 * spring 的bean Name
	 */
	private String beanName;

	/**
	 * 方法
	 */
	private Method method;

	/**
	 * service 方法参数
	 */
	private Object[] args;

	private Integer type = 0;

	// 事务Id,每次RPC请求,都会产生新的事务Id
	private String txId = null;

	// 事务组Id,一次完整的请求链,groupId是唯一的.
	private String txGroupId = null;

	// 版本的编号,用于处理不同的版本
	private Integer versionCode;

	// 超时时间,超时之后,事务回滚
	private Integer timeout;

	// 事务自动提交,默认true,如果是false就需要等待入口通知提交.
	private Boolean autocommit;

	public String getClazz() {
		return clazz;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public Method getMethod() {
		return method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getTxGroupId() {
		return txGroupId;
	}

	public void setTxGroupId(String txGroupId) {
		this.txGroupId = txGroupId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public Boolean getAutocommit() {
		return autocommit;
	}

	public void setAutocommit(Boolean autocommit) {
		this.autocommit = autocommit;
	}

}