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
	 void setExpiresTime(Long l);
	 void setJsApiTicketExpiresTime(Long l);
	 void setCardApiTicketExpiresTime(Long l);
     boolean isAccessTokenExpired();
	 String getSecret();
	 void setAccessToken(String accessToken);
	 boolean isJsApiTicketExpired();
	 void setJsApiTicket(String jsapiTicket);
	 boolean autoRefreshToken();

	 
}
