package org.springrain.weixin.sdk.common.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;

@RpcServiceAnnotation(implpackage = "weixin.service.impl")
public interface IWxXcxConfigService {
	
	/**
	 * 根据ID查找微信配置,可以进行缓存处理
	 * @param id
	 * @return
	 */
	IWxXcxConfig findWxXcxConfigById(String id);
	
	
	/**
	 * 更新WxXcxConfig,可以进行缓存处理
	 * @param wxxcxconfig
	 * @return
	 */
	IWxXcxConfig updateWxXcxConfig(IWxXcxConfig wxxcxconfig);
	

	
	/**
	 * 更新 expireAccessToken
	 * @param wxMpConfig
	 * @return
	 */
	IWxXcxConfig expireAccessToken(IWxXcxConfig wxxcxconfig);
	

	/**
	 * 更新 updateAccessToken
	 * @param wxMpConfig
	 * @return
	 */
    IWxXcxConfig updateAccessToken(IWxXcxConfig wxxcxconfig);
   
   
	
	
	/**
	 * 获取自定义的APIAccessToken
	 * @param wxxcxconfig
	 * @return
	 */
	WxAccessToken getCustomAPIAccessToken(IWxXcxConfig wxxcxconfig);
	
	
	
}
