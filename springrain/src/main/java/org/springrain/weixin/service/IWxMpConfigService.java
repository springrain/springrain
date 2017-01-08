package org.springrain.weixin.service;

import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxMpConfig;

public interface IWxMpConfigService  extends IBaseSpringrainService{
	
	/**
	 * 更新WxMpConfig,可以进行缓存处理
	 * @param wxmpconfig
	 * @return
	 */
	WxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig);
	

	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireAccessToken(WxMpConfig wxmpconfig);
	
	
    WxMpConfig updateAccessToken(WxMpConfig wxmpconfig);
   
   
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireJsApiTicket(WxMpConfig wxmpconfig);
   

	WxMpConfig updateJsApiTicket(WxMpConfig wxmpconfig);
	
	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireCardApiTicket(WxMpConfig wxmpconfig);
   
	WxMpConfig updateCardApiTicket(WxMpConfig wxmpconfig);
	
	
}
