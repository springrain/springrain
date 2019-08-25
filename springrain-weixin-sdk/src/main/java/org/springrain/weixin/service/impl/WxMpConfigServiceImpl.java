package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;
import org.springrain.weixin.sdk.common.service.IWxMpConfigService;
import org.springrain.weixin.sdk.common.util.crypto.SHA1;
import org.springrain.weixin.sdk.mp.api.AccessTokenApi;
import org.springrain.weixin.sdk.mp.api.TicketApi;

import java.util.HashMap;
import java.util.Map;

@Service("wxMpConfigService")
public class WxMpConfigServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMpConfigService {


    @Override
    public IWxMpConfig expireAccessToken(IWxMpConfig wxmpconfig) {
        wxmpconfig.setAccessTokenExpiresTime(0L);
        // 缓存操作
        updateWxMpConfig(wxmpconfig);

        return wxmpconfig;
    }

    @Override
    public IWxMpConfig updateAccessToken(IWxMpConfig wxmpconfig) {

        // 缓存操作
        updateWxMpConfig(wxmpconfig);

        return wxmpconfig;
    }

    @Override
    public IWxMpConfig expireJsApiTicket(IWxMpConfig wxmpconfig) {
        wxmpconfig.setJsApiTicketExpiresTime(0L);

        // 缓存操作
        updateWxMpConfig(wxmpconfig);

        return wxmpconfig;
    }

    @Override
    public IWxMpConfig updateJsApiTicket(IWxMpConfig wxmpconfig) {

        // 缓存操作
        updateWxMpConfig(wxmpconfig);

        return wxmpconfig;
    }

    @Override
    public IWxMpConfig expireCardApiTicket(IWxMpConfig wxmpconfig) {

        wxmpconfig.setCardApiTicketExpiresTime(0L);
        // 缓存操作
        updateWxMpConfig(wxmpconfig);

        return wxmpconfig;
    }

    @Override
    public IWxMpConfig updateCardApiTicket(IWxMpConfig wxmpconfig) {
        // 缓存操作
        updateWxMpConfig(wxmpconfig);
        return wxmpconfig;

    }

    @Override
    public IWxMpConfig findWxMpConfigById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        IWxMpConfig wxMpConfig = null;
        try {
            wxMpConfig = super.getByCache(id, GlobalStatic.mpConfigCacheKey, WxMpConfig.class);
            if (wxMpConfig == null) {
                wxMpConfig = super.findById(id, WxMpConfig.class);
                AccessTokenApi.getAccessToken(wxMpConfig);
                TicketApi.getCardApiTicket(wxMpConfig);
                TicketApi.getJsApiTicket(wxMpConfig);
                super.putByCache(id, GlobalStatic.mpConfigCacheKey, wxMpConfig);
            }
        } catch (Exception e) {
            wxMpConfig = null;
            logger.error(e.getMessage(), e);
        }

        return wxMpConfig;
    }

    /**
     * 缓存处理,可以把配置进行缓存更新 @
     */
    @Override
    public IWxMpConfig updateWxMpConfig(IWxMpConfig wxmpconfig) {

        String id = wxmpconfig.getId();
        if (StringUtils.isBlank(id)) {
            return null;
        }

        try {
            super.putByCache(id, GlobalStatic.mpConfigCacheKey, wxmpconfig);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return wxmpconfig;
    }

    @Override
    public Map<String, String> findMpJsApiParam(IWxMpConfig wxMpConfig, String url)  {

        if (wxMpConfig == null || StringUtils.isBlank(url)) {
            return null;
        }

        Map<String, String> params = new HashMap<>();

        String jsapiTicket =wxMpConfig.getJsApiTicket();
        String nonceStr = RandomStringUtils.random(32, "123456789"); // 8位随机数
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String jsApiSignature = this.getSignature(nonceStr, jsapiTicket, timestamp, url);

        params.put("timestamp", timestamp);
        params.put("nonceStr", nonceStr);
        params.put("jsApiSignature", jsApiSignature);
        params.put("appId", wxMpConfig.getAppId());
        return params;
    }

    private String getSignature(String nonceStr, String jsapiTicket, String timestamp, String url) {

        StringBuilder sb = new StringBuilder();
        sb.append("jsapi_ticket=").append(jsapiTicket).append("&noncestr=").append(nonceStr).append("&timestamp=")
                .append(timestamp).append("&url=").append(url);

        return SHA1.gen(sb.toString());

    }



}
