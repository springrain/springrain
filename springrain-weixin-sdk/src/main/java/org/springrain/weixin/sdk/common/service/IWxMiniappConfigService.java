package org.springrain.weixin.sdk.common.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;

@RpcServiceAnnotation(implpackage = "weixin.service.impl")
public interface IWxMiniappConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxMiniappConfig findWxMiniappConfigById(String id);


    /**
     * 更新WxMiniappConfig,可以进行缓存处理
     *
     * @param wxminiappconfig
     * @return
     */
    IWxMiniappConfig updateWxMiniappConfig(IWxMiniappConfig wxminiappconfig);


    /**
     * 更新 expireAccessToken
     *
     * @param wxminiappconfig
     * @return
     */
    IWxMiniappConfig expireAccessToken(IWxMiniappConfig wxminiappconfig);


    /**
     * 更新 updateAccessToken
     *
     * @param wxminiappconfig
     * @return
     */
    IWxMiniappConfig updateAccessToken(IWxMiniappConfig wxminiappconfig);


    /**
     * 获取自定义的APIAccessToken
     *
     * @param wxminiappconfig
     * @return
     */
    WxAccessToken getCustomAPIAccessToken(IWxMiniappConfig wxminiappconfig);


}