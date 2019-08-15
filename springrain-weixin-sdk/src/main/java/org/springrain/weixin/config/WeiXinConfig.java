package org.springrain.weixin.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springrain.weixin.sdk.common.service.IWxCpConfigService;
import org.springrain.weixin.sdk.common.service.IWxMpConfigService;
import org.springrain.weixin.sdk.common.service.IWxXcxConfigService;
import org.springrain.weixin.sdk.cp.api.IWxCpService;
import org.springrain.weixin.sdk.cp.api.WxCpServiceImpl;
import org.springrain.weixin.sdk.mp.api.IWxMpCardService;
import org.springrain.weixin.sdk.mp.api.IWxMpDataCubeService;
import org.springrain.weixin.sdk.mp.api.IWxMpKefuService;
import org.springrain.weixin.sdk.mp.api.IWxMpMaterialService;
import org.springrain.weixin.sdk.mp.api.IWxMpMenuService;
import org.springrain.weixin.sdk.mp.api.IWxMpPayService;
import org.springrain.weixin.sdk.mp.api.IWxMpQrcodeService;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.api.IWxMpTemplateMsgService;
import org.springrain.weixin.sdk.mp.api.IWxMpUserService;
import org.springrain.weixin.sdk.mp.api.impl.WxMpCardServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpDataCubeServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpKefuServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpMaterialServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpMenuServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpPayServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpQrcodeServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpTemplateMsgServiceImpl;
import org.springrain.weixin.sdk.mp.api.impl.WxMpUserServiceImpl;
import org.springrain.weixin.sdk.xcx.api.IWxXcxMaterialService;
import org.springrain.weixin.sdk.xcx.api.IWxXcxPayService;
import org.springrain.weixin.sdk.xcx.api.IWxXcxService;
import org.springrain.weixin.sdk.xcx.api.IWxXcxTemplateMsgService;
import org.springrain.weixin.sdk.xcx.api.impl.WxXcxMaterialServiceImpl;
import org.springrain.weixin.sdk.xcx.api.impl.WxXcxPayServiceImpl;
import org.springrain.weixin.sdk.xcx.api.impl.WxXcxServiceImpl;
import org.springrain.weixin.sdk.xcx.api.impl.WxXcxTemplateMsgServiceImpl;

@Configuration("configuration-WeiXinConfig")
public class WeiXinConfig {

	@Resource
	private IWxMpConfigService wxMpConfigService;
	@Resource
	private IWxCpConfigService wxCpConfigService;
	@Resource
	private IWxXcxConfigService wxXcxConfigService;

	/**
	 * 
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

	@Bean("wxXcxService")
	public IWxXcxService wxXcxService() {
		IWxXcxService wxXcxService = new WxXcxServiceImpl(wxXcxConfigService);
		return wxXcxService;
	}

	@Bean("wxXcxPayService")
	public IWxXcxPayService wxXcxPayService() {
		IWxXcxPayService wxXcxPayService = new WxXcxPayServiceImpl(wxXcxService());
		return wxXcxPayService;
	}

	@Bean("wxXcxTemplateMsgService")
	public IWxXcxTemplateMsgService wxXcxTemplateMsgService() {
		IWxXcxTemplateMsgService wxXcxTemplateMsgService = new WxXcxTemplateMsgServiceImpl(wxXcxService());
		return wxXcxTemplateMsgService;
	}

	@Bean("wxXcxMaterialService")
	public IWxXcxMaterialService wxXcxMaterialService() {
		IWxXcxMaterialService wxXcxMaterialService = new WxXcxMaterialServiceImpl(wxXcxService());
		return wxXcxMaterialService;
	}

}
