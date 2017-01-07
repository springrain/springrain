package org.springrain.weixin.service;

import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxMpConfig;

public interface IWxMpConfigService  extends IBaseSpringrainService{

	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireAccessToken(WxMpConfig wxmpconfig);
	
	
   boolean isAccessTokenExpired(WxMpConfig wxmpconfig);
   
   
   WxMpConfig updateAccessToken(WxMpConfig wxmpconfig);
   
   
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireJsapiTicket(WxMpConfig wxmpconfig);
	
	
	boolean isJsapiTicketExpired(WxMpConfig wxmpconfig);
   

	WxMpConfig updateJsapiTicket(WxMpConfig wxmpconfig);
	
	
	
	boolean autoRefreshToken(WxMpConfig wxmpconfig);
	
	
	
	
	
	
	
}
