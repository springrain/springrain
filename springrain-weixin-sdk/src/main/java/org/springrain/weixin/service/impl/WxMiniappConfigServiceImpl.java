package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxMiniappConfig;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;
import org.springrain.weixin.service.IWxMiniappConfigService;

@Service("wxMiniappConfigService")
public class WxMiniappConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMiniappConfigService {


    @Override
    public IWxMiniappConfig expireAccessToken(IWxMiniappConfig wxminiappconfig) {
        wxminiappconfig.setAccessTokenExpiresTime(0L);
        // 缓存操作
        updateWxMiniappConfig(wxminiappconfig);
        return wxminiappconfig;
    }

    @Override
    public IWxMiniappConfig updateAccessToken(IWxMiniappConfig wxminiappconfig) {

        // 缓存操作
        updateWxMiniappConfig(wxminiappconfig);

        return wxminiappconfig;
    }

    @Override
    public IWxMiniappConfig findWxMiniappConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxMiniappConfig wxMiniappConfig = null;
        try {
            wxMiniappConfig = super.getByCache(id, GlobalStatic.miniappConfigCacheKey, WxMiniappConfig.class);
            if (wxMiniappConfig == null) {
                wxMiniappConfig = super.findById(id, WxMiniappConfig.class);
                super.putByCache(id, GlobalStatic.miniappConfigCacheKey, wxMiniappConfig);
            }
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
    public IWxMiniappConfig updateWxMiniappConfig(IWxMiniappConfig wxminiappconfig) {

        String id = wxminiappconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.putByCache(id, GlobalStatic.miniappConfigCacheKey, wxminiappconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxminiappconfig;
    }



}
