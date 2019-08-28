package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxMiniappConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.springrain.weixin.sdk.mp.AccessTokenApi;
import org.springrain.weixin.service.IWxMiniappConfigService;

@Service("wxMiniappConfigService")
public class WxMiniappConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMiniappConfigService {

    private String cacheKeyPrefix="wxminiapp_config_";

    @Override
    public IWxMiniappConfig findWxMiniappConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxMiniappConfig wxMiniappConfig = null;
        try {
            wxMiniappConfig = super.getByCache(cacheKeyPrefix+id, GlobalStatic.wxConfigCacheKey, WxMiniappConfig.class);
            if (wxMiniappConfig == null) {
                wxMiniappConfig = super.findById(id, WxMiniappConfig.class);

            }
            if (wxMiniappConfig == null) {
                return null;
            }

            if(!wxMiniappConfig.isAccessTokenExpired()){
                AccessTokenApi.getAccessToken(wxMiniappConfig);
            }

            super.putByCache(cacheKeyPrefix+id, GlobalStatic.wxConfigCacheKey, wxMiniappConfig);

        } catch (Exception e) {
            wxMiniappConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxMiniappConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxMiniappConfig updateWxMiniappConfig(WxMiniappConfig wxminiappconfig) {

        String id = wxminiappconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.update(wxminiappconfig);
            super.putByCache(id, GlobalStatic.wxConfigCacheKey, wxminiappconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxminiappconfig;
    }


}
