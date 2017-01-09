package org.springrain.weixin.sdk.common.api;

public interface IWxConfig {
	 String getId();//业务Id
	 String getAppId();
	 String getToken();
	 String getAccessToken();
	 String getAesKey();
	 String getHttpProxyHost();
	 Integer getHttpProxyPort();
	 String getHttpProxyUsername();
	 String getHttpProxyPassword();
	 String getJsApiTicket();
	 String getCardApiTicket();
	 String getCertificateFile();
	 String getTmpDirFile();
	 
}
