package org.springrain.weixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springrain.weixin.sdk.common.service.IWxCpConfigService;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfigService;
import org.springrain.weixin.sdk.common.service.IWxMpConfigService;
import org.springrain.weixin.sdk.cp.api.IWxCpService;
import org.springrain.weixin.sdk.cp.api.WxCpServiceImpl;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappMaterialService;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappPayService;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappService;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappTemplateMsgService;
import org.springrain.weixin.sdk.miniapp.api.impl.WxMiniappMaterialServiceImpl;
import org.springrain.weixin.sdk.miniapp.api.impl.WxMiniappPayServiceImpl;
import org.springrain.weixin.sdk.miniapp.api.impl.WxMiniappServiceImpl;
import org.springrain.weixin.sdk.miniapp.api.impl.WxMiniappTemplateMsgServiceImpl;
import org.springrain.weixin.sdk.mp.api.*;
import org.springrain.weixin.sdk.mp.api.impl.*;

import javax.annotation.Resource;

@Configuration("configuration-WeiXinConfig")
public class WeiXinConfig {

    @Resource
    private IWxMpConfigService wxMpConfigService;
    @Resource
    private IWxCpConfigService wxCpConfigService;
    @Resource
    private IWxMiniappConfigService wxMiniappConfigService;

    /**
     * @return
     */

    @Bean("wxMpService")
    public IWxMpService wxMpService() {
        IWxMpService wxMpService = new WxMpServiceImpl(wxMpConfigService);
        return wxMpService;
    }

    @Bean("wxMpCardService")
    public IWxMpCardService wxMpCardService() {
        IWxMpCardService wxMpCardService = new WxMpCardServiceImpl(wxMpService(), wxMpConfigService);
        return wxMpCardService;
    }

    @Bean("wxMpDataCubeService")
    public IWxMpDataCubeService wxMpDataCubeService() {
        IWxMpDataCubeService wxMpDataCubeService = new WxMpDataCubeServiceImpl(wxMpService());
        return wxMpDataCubeService;
    }

    @Bean("wxMpKefuService")
    public IWxMpKefuService wxMpKefuService() {
        IWxMpKefuService wxMpKefuService = new WxMpKefuServiceImpl(wxMpService());
        return wxMpKefuService;
    }

    @Bean("wxMpMaterialService")
    public IWxMpMaterialService wxMpMaterialService() {
        IWxMpMaterialService wxMpMaterialService = new WxMpMaterialServiceImpl(wxMpService());
        return wxMpMaterialService;
    }

    @Bean("wxMpMenuService")
    public IWxMpMenuService wxMpMenuService() {
        IWxMpMenuService wxMpMenuService = new WxMpMenuServiceImpl(wxMpService());
        return wxMpMenuService;
    }

    @Bean("wxMpPayService")
    public IWxMpPayService wxMpPayService() {
        IWxMpPayService wxMpPayService = new WxMpPayServiceImpl(wxMpService());
        return wxMpPayService;
    }

    @Bean("wxMpQrcodeService")
    public IWxMpQrcodeService wxMpQrcodeService() {
        IWxMpQrcodeService wxMpQrcodeService = new WxMpQrcodeServiceImpl(wxMpService());
        return wxMpQrcodeService;
    }

    @Bean("wxMpUserService")
    public IWxMpUserService wxMpUserService() {
        IWxMpUserService wxMpUserService = new WxMpUserServiceImpl(wxMpService());
        return wxMpUserService;
    }

    @Bean("wxMpTemplateMsgService")
    public IWxMpTemplateMsgService wxMpTemplateMsgService() {
        IWxMpTemplateMsgService wxMpTemplateMsgService = new WxMpTemplateMsgServiceImpl(wxMpService());
        return wxMpTemplateMsgService;
    }

    @Bean("wxCpService")
    public IWxCpService wxCpService() {
        IWxCpService wxCpService = new WxCpServiceImpl(wxCpConfigService);
        return wxCpService;
    }

    @Bean("wxMiniappService")
    public IWxMiniappService wxMiniappService() {
        IWxMiniappService wxMiniappService = new WxMiniappServiceImpl(wxMiniappConfigService);
        return wxMiniappService;
    }

    @Bean("wxMiniappPayService")
    public IWxMiniappPayService wxMiniappPayService() {
        IWxMiniappPayService wxMiniappPayService = new WxMiniappPayServiceImpl(wxMiniappService());
        return wxMiniappPayService;
    }

    @Bean("wxMiniappTemplateMsgService")
    public IWxMiniappTemplateMsgService wxMiniappTemplateMsgService() {
        IWxMiniappTemplateMsgService wxMiniappTemplateMsgService = new WxMiniappTemplateMsgServiceImpl(wxMiniappService());
        return wxMiniappTemplateMsgService;
    }

    @Bean("wxMiniappMaterialService")
    public IWxMiniappMaterialService wxMiniappMaterialService() {
        IWxMiniappMaterialService wxMiniappMaterialService = new WxMiniappMaterialServiceImpl(wxMiniappService());
        return wxMiniappMaterialService;
    }

}
