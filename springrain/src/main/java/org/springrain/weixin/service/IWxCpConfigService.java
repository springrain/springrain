package org.springrain.weixin.service;

import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxCpConfig;

public interface IWxCpConfigService  extends IBaseSpringrainService{
	
	/**
	 * 更新WxCpConfig,可以进行缓存处理
	 * @param wxcpconfig
	 * @return
	 */
	WxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig);
	

	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireAccessToken(WxCpConfig wxcpconfig);
	
	
    WxCpConfig updateAccessToken(WxCpConfig wxcpconfig);
   
   
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireJsApiTicket(WxCpConfig wxcpconfig);
   

	WxCpConfig updateJsApiTicket(WxCpConfig wxcpconfig);
	
	/**
	 * 失效
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireCardapiTicket(WxCpConfig wxcpconfig);
   
	WxCpConfig updateCardapiTicket(WxCpConfig wxcpconfig);
	
	
}
