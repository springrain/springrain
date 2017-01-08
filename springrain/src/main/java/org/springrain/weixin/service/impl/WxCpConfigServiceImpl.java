package org.springrain.weixin.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxCpConfig;
import org.springrain.weixin.service.IWxCpConfigService;


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
	public WxCpConfig expireJsapiTicket(WxCpConfig wxcpconfig) {
		wxcpconfig.setJsapiTicketExpiresTime(0L);
		
		//缓存操作
		updateWxCpConfig(wxcpconfig);
				
		
		
		return wxcpconfig;
	}



	@Override
	public WxCpConfig updateJsapiTicket(WxCpConfig wxcpconfig) {
		
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

	
	/**
	 * 缓存处理,可以把配置进行缓存更新
	 */
	@Override
	public WxCpConfig updateWxCpConfig(WxCpConfig wxcpconfig) {
		
		
		return wxcpconfig;
	}
	
	

	
	
	
	
	
	
	

}
