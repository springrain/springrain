package org.springrain.rpc.grpcimpl;

import java.io.Serializable;

/**
 * 封装返回的对象. 序列化成二进制,然后再经过grpc传输
 * 
 * @author caomei
 *
 */
public class GrpcCommonResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 响应状态
	 */
	private int status;

	/**
	 * 信息提示
	 */
	private String message;

	/**
	 * 返回结果
	 */
	private Object result;

	/**
	 * 服务端异常
	 */
	private Throwable exception;

	/**
	 * 异常堆栈信息
	 */
	private StackTraceElement[] stackTrace;
	
	// 返回的事务Id
	private String txId = null;
	
	// 事务组Id,一次完整的请求链,groupId是唯一的.
	private String txGroupId = null;

	// 版本的编号,用于处理不同的版本
	private Integer versionCode;



	void error(String message, Throwable exception, StackTraceElement[] stackTrace) {
		this.status = 500;
		this.message = message;
		this.exception = exception;
		this.stackTrace = stackTrace;
	}

	void success(Object result) {
		this.status = 200;
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Object getResult() {
		return result;
	}

	public Throwable getException() {
		return exception;
	}

	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
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

	public Integer getVersionCode() {
		return versionCode;
	}



	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}



}
