package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;


@Service("wxMpConfigService")
public class WxMpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxMpConfigService {
	

	@Override
	public IWxMpConfig expireAccessToken(IWxMpConfig wxmpconfig) {
		wxmpconfig.setAccessTokenExpiresTime(0L);
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		
		return wxmpconfig;
	}


	@Override
	public IWxMpConfig updateAccessToken(IWxMpConfig wxmpconfig)  {
		
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		
		
		return wxmpconfig;
	}

	@Override
	public IWxMpConfig expireJsApiTicket(IWxMpConfig wxmpconfig)  {
		wxmpconfig.setJsApiTicketExpiresTime(0L);
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		
		
		return wxmpconfig;
	}



	@Override
	public IWxMpConfig updateJsApiTicket(IWxMpConfig wxmpconfig)  {
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		return wxmpconfig;
	}

	
	@Override
	public IWxMpConfig expireCardApiTicket(IWxMpConfig wxmpconfig)  {
		
		wxmpconfig.setCardApiTicketExpiresTime(0L);
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		return wxmpconfig;
	}



	@Override
	public IWxMpConfig updateCardApiTicket(IWxMpConfig wxmpconfig)  {
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		return wxmpconfig;
		
	}

	
	
	@Override
	public IWxMpConfig findWxMpConfigById(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		IWxMpConfig wxMpConfig=null;
		try {
			wxMpConfig = super.getByCache(id, id, IWxMpConfig.class);
			if(wxMpConfig==null){
				wxMpConfig = super.findById(id, WxMpConfig.class);
				super.putByCache(id, id, wxMpConfig);
			}
		} catch (Exception e) {
			wxMpConfig=null;
			logger.error(e.getMessage(),e);
		}
		
		return wxMpConfig;
	}
	
	/**
	 * 缓存处理,可以把配置进行缓存更新
	 * @
	 */
	@Override
	public IWxMpConfig updateWxMpConfig(IWxMpConfig wxmpconfig) {
		
		String id=wxmpconfig.getId();
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		try {
			super.putByCache(id, id, wxmpconfig);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		
		return wxmpconfig;
	}
	

}
