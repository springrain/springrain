package org.springrain.weixin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxCpConfig;
import org.springrain.weixin.sdk.common.api.IWxCpConfigService;


@Service("wxCpConfigService")
public class WxCpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxCpConfigService  {

	@Override
	public WxCpConfig expireAccessToken(WxCpConfig wxcpconfig) {
		wxcpconfig.setExpiresTime(0L);
		
		//缓存操作
		updateWxCpConfig(wxcpconfig);
		
		
		return wxcpconfig;
	}


	@Override
	public WxCpConfig updateAccessToken(WxCpConfig wxcpconfig) {
		
		
		//缓存操作
		updateWxCpConfig(wxcpconfig);
		
		
		
		return wxcpconfig;
	}

	@Override
	public WxCpConfig expireJsApiTicket(WxCpConfig wxcpconfig) {
		wxcpconfig.setJsApiTicketExpiresTime(0L);
		
		//缓存操作
		updateWxCpConfig(wxcpconfig);
				
		
		
		return wxcpconfig;
	}



	@Override
	public WxCpConfig updateJsApiTicket(WxCpConfig wxcpconfig) {
		
		//缓存操作
		updateWxCpConfig(wxcpconfig);
				
		return wxcpconfig;
	}

	
	@Override
	public WxCpConfig expireCardapiTicket(WxCpConfig wxcpconfig) {
		
		wxcpconfig.setCardApiTicketExpiresTime(0L);
		//缓存操作
		updateWxCpConfig(wxcpconfig);
		
		return wxcpconfig;
	}



	@Override
	public WxCpConfig updateCardapiTicket(WxCpConfig wxcpconfig) {
		//缓存操作
		updateWxCpConfig(wxcpconfig);
		return wxcpconfig;
		
	}
	@Override
	public WxCpConfig findWxCpConfigById(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		WxCpConfig wxcpConfig=null;
		try {
			wxcpConfig = super.getByCache(GlobalStatic.springrainweixinCacheKey, id, WxCpConfig.class);
		} catch (Exception e) {
			wxcpConfig=null;
			logger.error(e.getMessage());
		}
		if(wxcpConfig!=null){
			return wxcpConfig;
		}
		
		//从数据库查询
		//WxCpConfig wxcpConfig=super.findById(id, WxCpConfig.class);
		
		//这里是为了测试
		 wxcpConfig=new WxCpConfig();
		
		return wxcpConfig;
	}
	
	
	/**
	 * 缓存处理,可以把配置进行缓存更新
	 */
	@Override
	public WxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig) {
		
		String id=wxcpconfig.getId();
		if(StringUtils.isBlank(id)){
			return null;
		}
		
		try {
			super.putByCache(GlobalStatic.springrainweixinCacheKey, id, WxCpConfig.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		
		return wxcpconfig;
	}


	
	

	
	
	
	
	
	
	

}
