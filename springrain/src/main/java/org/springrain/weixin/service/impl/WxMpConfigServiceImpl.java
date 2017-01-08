package org.springrain.weixin.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.service.IWxMpConfigService;


@Service("wxMpConfigService")
public class WxMpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxMpConfigService  {

	@Override
	public WxMpConfig expireAccessToken(WxMpConfig wxmpconfig) {
		wxmpconfig.setExpiresTime(0L);
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		
		return wxmpconfig;
	}


	@Override
	public WxMpConfig updateAccessToken(WxMpConfig wxmpconfig) {
		
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		
		
		return wxmpconfig;
	}

	@Override
	public WxMpConfig expireJsApiTicket(WxMpConfig wxmpconfig) {
		wxmpconfig.setJsapiTicketExpiresTime(0L);
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		
		
		return wxmpconfig;
	}



	@Override
	public WxMpConfig updateJsApiTicket(WxMpConfig wxmpconfig) {
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		return wxmpconfig;
	}

	
	@Override
	public WxMpConfig expireCardApiTicket(WxMpConfig wxmpconfig) {
		
		wxmpconfig.setCardApiTicketExpiresTime(0L);
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		return wxmpconfig;
	}



	@Override
	public WxMpConfig updateCardApiTicket(WxMpConfig wxmpconfig) {
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		return wxmpconfig;
		
	}

	
	/**
	 * 缓存处理,可以把配置进行缓存更新
	 */
	@Override
	public WxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig) {
		
		
		return wxmpconfig;
	}
	
	

	
	
	
	
	
	
	

}
