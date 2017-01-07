package org.springrain.weixin.base.common.api;

import javax.net.ssl.SSLContext;

public interface IWxConfig {
	
	 String getAppId();
	 String getToken();
	 String getAccessToken();
	 String getAesKey();
	 String getHttpProxyHost();
	 Integer getHttpProxyPort();
	 String getHttpProxyUsername();
	 String getHttpProxyPassword();
	 String getJsapiTicket();
	 String getCardApiTicket();
	 SSLContext getSslContext();
	 
}
