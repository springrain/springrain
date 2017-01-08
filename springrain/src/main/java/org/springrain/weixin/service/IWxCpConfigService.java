package org.springrain.weixin.service;

import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxCpConfig;

public interface IWxCpConfigService  extends IBaseSpringrainService{

	boolean autoRefreshToken(WxCpConfig wxmpconfig);
	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireAccessToken(WxCpConfig wxmpconfig);
	
	
   boolean isAccessTokenExpired(WxCpConfig wxmpconfig);
   
   
   WxCpConfig updateAccessToken(WxCpConfig wxmpconfig);
   
   
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireJsapiTicket(WxCpConfig wxmpconfig);
	
	
	boolean isJsapiTicketExpired(WxCpConfig wxmpconfig);
   

	WxCpConfig updateJsapiTicket(WxCpConfig wxmpconfig);
	
	
	
	
	
	
	
	
	
}
