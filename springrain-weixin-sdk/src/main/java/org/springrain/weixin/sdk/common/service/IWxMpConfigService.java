package org.springrain.weixin.sdk.common.service;

import org.springrain.rpc.annotation.RpcServiceAnnotation;

import java.util.Map;

@RpcServiceAnnotation(implpackage = "weixin.service.impl")
public interface IWxMpConfigService {

    /**
     * 根据ID查找微信配置,可以进行缓存处理
     *
     * @param id
     * @return
     */
    IWxMpConfig findWxMpConfigById(String id);

    /**
     * 更新WxMpConfig,可以进行缓存处理
     *
     * @param wxmpconfig
     * @return
     */
    IWxMpConfig updateWxMpConfig(IWxMpConfig wxmpconfig);

    /**
     * 更新 expireAccessToken
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig expireAccessToken(IWxMpConfig wxmpconfig);

    /**
     * 更新 updateAccessToken
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig updateAccessToken(IWxMpConfig wxmpconfig);

    /**
     * 更新 expireJsApiTicket
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig expireJsApiTicket(IWxMpConfig wxmpconfig);

    /**
     * 更新 updateJsApiTicket
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig updateJsApiTicket(IWxMpConfig wxmpconfig);

    /**
     * 更新 expireCardApiTicket
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig expireCardApiTicket(IWxMpConfig wxmpconfig);

    /**
     * 更新 updateCardApiTicket
     *
     * @param wxMpConfig
     * @return
     */
    IWxMpConfig updateCardApiTicket(IWxMpConfig wxmpconfig);

    /**
     * 根据siteid和request查询jsapi配置信息
     *
     * @param siteId
     * @param request
     * @return
     * @throws Exception
     */
    Map<String, String> findMpJsApiParam(IWxMpConfig wxMpConfig, String url) throws Exception;


}
