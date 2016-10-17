package org.springrain.weixin.common.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * 接受
 * @author caomei
 *
 */

public class WeiXinRequestParameterDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//请求的URL,变量使用 ##name## 这样替换
	private String requestUrl;
	
	//请求的参数
	private Map parameter;
	
	//请求方法,默认GET
	private String requestMethod="GET";
	


	public String getRequestUrl() {
		return requestUrl;
	}


	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}


	public Map getParameter() {
		return parameter;
	}


	public void setParameter(Map parameter) {
		this.parameter = parameter;
	}


	public String getRequestMethod() {
		return requestMethod;
	}


	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	
	
	

	

}
