package org.springrain.weixin.sdk.common.api;

public interface IWxMpConfig extends IWxConfig {



	String getPartnerId();
	void setPartnerId(String partnerId);
	
	
	String getPartnerKey();
	void setPartnerKey(String partnerKey);
	
	//开启oauth2.0认证,决定是否能够过去openId,0是关闭,1是开区
	 Integer getOauth2();
	 void setOauth2(Integer oauth2);





}
