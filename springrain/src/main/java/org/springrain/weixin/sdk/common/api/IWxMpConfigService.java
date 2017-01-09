package org.springrain.weixin.sdk.common.api;

import org.springrain.weixin.entity.WxMpConfig;

public interface IWxMpConfigService {
	
	/**
	 * 根据ID查找微信配置,可以进行缓存处理
	 * @param id
	 * @return
	 */
	WxMpConfig findWxMpConfigById(String id);
	
	
	/**
	 * 更新WxMpConfig,可以进行缓存处理
	 * @param wxmpconfig
	 * @return
	 */
	WxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig);
	

	
	/**
	 * 更新 expireAccessToken
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireAccessToken(WxMpConfig wxmpconfig);
	

	/**
	 * 更新 updateAccessToken
	 * @param wxMpConfig
	 * @return
	 */
    WxMpConfig updateAccessToken(WxMpConfig wxmpconfig);
   
   
    /**
	 * 更新 expireJsApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireJsApiTicket(WxMpConfig wxmpconfig);
   

    /**
	 * 更新 updateJsApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig updateJsApiTicket(WxMpConfig wxmpconfig);
	
	
	/**
	 * 更新 expireCardApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig expireCardApiTicket(WxMpConfig wxmpconfig);
   
	
	/**
	 * 更新 updateCardApiTicket
	 * @param wxMpConfig
	 * @return
	 */
	WxMpConfig updateCardApiTicket(WxMpConfig wxmpconfig);
	
	
}
