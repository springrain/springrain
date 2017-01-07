package org.springrain.weixin.service.impl;

import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.service.IWxMpConfigService;

public class WxMpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxMpConfigService  {

	@Override
	public WxMpConfig expireAccessToken(WxMpConfig wxmpconfig) {
		
		return wxmpconfig;
	}

	@Override
	public boolean isAccessTokenExpired(WxMpConfig wxmpconfig) {
		return true;
	}

	@Override
	public WxMpConfig updateAccessToken(WxMpConfig wxmpconfig) {
		return wxmpconfig;
	}

	@Override
	public WxMpConfig expireJsapiTicket(WxMpConfig wxmpconfig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isJsapiTicketExpired(WxMpConfig wxmpconfig) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WxMpConfig updateJsapiTicket(WxMpConfig wxmpconfig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean autoRefreshToken(WxMpConfig wxmpconfig) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	

}
