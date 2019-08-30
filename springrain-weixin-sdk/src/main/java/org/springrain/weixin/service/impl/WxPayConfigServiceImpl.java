package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxPayConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.springrain.weixin.sdk.mp.AccessTokenApi;
import org.springrain.weixin.service.IWxPayConfigService;

@Service("wxPayConfigService")
public class WxPayConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxPayConfigService {

    private String cacheKeyPrefix = "wxpay_config_";

    @Override
    public IWxPayConfig findWxPayConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxPayConfig wxPayConfig = null;
        try {
            wxPayConfig = super.getByCache(cacheKeyPrefix + id, GlobalStatic.wxConfigCacheKey, WxPayConfig.class);
            if (wxPayConfig == null) {
                wxPayConfig = super.findById(id, WxPayConfig.class);

            }
            if (wxPayConfig == null) {
                return null;
            }

            if (!wxPayConfig.isAccessTokenExpired()) {
                AccessTokenApi.getAccessToken(wxPayConfig);
            }

            super.putByCache(cacheKeyPrefix + id, GlobalStatic.wxConfigCacheKey, wxPayConfig);

        } catch (Exception e) {
            wxPayConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxPayConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxPayConfig updateWxPayConfig(WxPayConfig wxpayappconfig) {

        String id = wxpayappconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }
        try {
            super.update(wxpayappconfig);
            super.putByCache(cacheKeyPrefix + id, GlobalStatic.wxConfigCacheKey, wxpayappconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxpayappconfig;
    }


}
