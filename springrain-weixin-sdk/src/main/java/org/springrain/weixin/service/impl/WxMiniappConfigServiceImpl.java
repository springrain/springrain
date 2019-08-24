package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.entity.WxMiniappConfig;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfig;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfigService;
import org.springrain.weixin.sdk.common.util.crypto.SHA1;

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

    private String getSignature(String nonceStr, String jsapiTicket, String timestamp, String url) {

        StringBuilder sb = new StringBuilder();
        sb.append("jsapi_ticket=").append(jsapiTicket).append("&noncestr=").append(nonceStr).append("&timestamp=")
                .append(timestamp).append("&url=").append(url);

        return SHA1.gen(sb.toString());

    }

    @Override
    public WxAccessToken getCustomAPIAccessToken(IWxMiniappConfig wxminiappconfig) {
        try {

            // 默认命名方式,建议继承这个wxMiniappConfigService重写这个方法,新Service请遵循默认的命名规则
            IWxMiniappConfigService wxMiniappConfigBean = (IWxMiniappConfigService) getBean(
                    "wxMiniappConfigService_" + wxminiappconfig.getId());
            if (wxMiniappConfigBean != null) {
                return wxMiniappConfigBean.getCustomAPIAccessToken(wxminiappconfig);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
