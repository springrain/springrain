package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
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
	public WxMpConfig updateAccessToken(WxMpConfig wxmpconfig)  {
		
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		
		
		return wxmpconfig;
	}

	@Override
	public WxMpConfig expireJsApiTicket(WxMpConfig wxmpconfig)  {
		wxmpconfig.setJsApiTicketExpiresTime(0L);
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		
		
		return wxmpconfig;
	}



	@Override
	public WxMpConfig updateJsApiTicket(WxMpConfig wxmpconfig)  {
		
		//缓存操作
		updateWxMpConfig(wxmpconfig);
				
		return wxmpconfig;
	}

	
	@Override
	public WxMpConfig expireCardApiTicket(WxMpConfig wxmpconfig)  {
		
		wxmpconfig.setCardApiTicketExpiresTime(0L);
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		
		return wxmpconfig;
	}



	@Override
	public WxMpConfig updateCardApiTicket(WxMpConfig wxmpconfig)  {
		//缓存操作
		updateWxMpConfig(wxmpconfig);
		return wxmpconfig;
		
	}

	
	
	@Override
	public WxMpConfig findWxMpConfigById(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		WxMpConfig wxMpConfig=null;
		try {
			wxMpConfig = super.getByCache(GlobalStatic.springrainweixinCacheKey, id, WxMpConfig.class);
		} catch (Exception e) {
			wxMpConfig=null;
			logger.error(e.getMessage());
		}
		if(wxMpConfig!=null){
			return wxMpConfig;
		}
		
		//从数据库查询
		//WxMpConfig config=super.findById(id, WxMpConfig.class);
		
		//这里是为了测试
		 wxMpConfig=new WxMpConfig();
		
		return wxMpConfig;
	}
	
	/**
	 * 缓存处理,可以把配置进行缓存更新
	 * @
	 */
	@Override
	public WxMpConfig updateWxMpConfig(WxMpConfig wxmpconfig) {
		
		String id=wxmpconfig.getId();
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		try {
			super.putByCache(GlobalStatic.springrainweixinCacheKey, id, wxmpconfig);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return wxmpconfig;
	}
	

}
