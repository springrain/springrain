package org.springrain.weixin.service;

import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxMpConfig;

public interface IWxMpConfigService  extends IBaseSpringrainService{

	boolean autoRefreshToken(WxMpConfig wxmpconfig);
	
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
	
	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireCardapiTicket(WxMpConfig wxmpconfig);
	
	
	boolean isCardapiTicketExpired(WxMpConfig wxmpconfig);
   

	WxMpConfig updateCardapiTicket(WxMpConfig wxmpconfig);
	
	
	
	
	
	
	
	
}
