package org.springrain.weixin.sdk.common.api;

import org.springrain.weixin.entity.WxCpConfig;

public interface IWxCpConfigService  {
	
	
	/**
	 * 根据ID查找微信配置,可以进行缓存处理
	 * @param id
	 * @return
	 */
	WxCpConfig findWxCpConfigById(String id);
	
	
	/**
	 * 更新WxCpConfig,可以进行缓存处理
	 * @param wxcpconfig
	 * @return
	 */
	WxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig);
	

	
	/**
	 * 更新 expireAccessToken
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireAccessToken(WxCpConfig wxcpconfig);
	
	
	/**
	 * 更新 updateAccessToken
	 * @param wxMpConfig
	 * @return
	 */
	
    WxCpConfig updateAccessToken(WxCpConfig wxcpconfig);
   
   
    /**
	 * 更新 expireJsApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireJsApiTicket(WxCpConfig wxcpconfig);
   
	/**
	 * 更新 updateJsApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig updateJsApiTicket(WxCpConfig wxcpconfig);
	
	/**
	 * 更新 expireCardapiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig expireCardapiTicket(WxCpConfig wxcpconfig);
   
	
	/**
	 * 更新 updateCardapiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxCpConfig updateCardapiTicket(WxCpConfig wxcpconfig);
	
	
}
