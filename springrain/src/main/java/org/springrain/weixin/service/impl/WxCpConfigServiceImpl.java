package org.springrain.weixin.service.impl;

import org.springframework.stereotype.Service;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxCpConfig;
import org.springrain.weixin.service.IWxCpConfigService;


@Service("wxCpConfigService")
public class WxCpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxCpConfigService  {

	@Override
	public WxCpConfig expireAccessToken(WxCpConfig wxmpconfig) {
		return wxmpconfig;
	}

	@Override
	public boolean isAccessTokenExpired(WxCpConfig wxmpconfig) {
		return true;
	}

	@Override
	public WxCpConfig updateAccessToken(WxCpConfig wxmpconfig) {
		return wxmpconfig;
	}

	@Override
	public WxCpConfig expireJsapiTicket(WxCpConfig wxmpconfig) {
		return null;
	}

	@Override
	public boolean isJsapiTicketExpired(WxCpConfig wxmpconfig) {
		return true;
	}

	@Override
	public WxCpConfig updateJsapiTicket(WxCpConfig wxmpconfig) {
		return null;
	}

	@Override
	public boolean autoRefreshToken(WxCpConfig wxmpconfig) {
		return true;
	}

	
	
	

	
	
	
	
	
	
	

}
