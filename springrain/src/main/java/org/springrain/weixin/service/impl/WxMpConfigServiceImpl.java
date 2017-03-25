package org.springrain.weixin.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.util.crypto.SHA1;
import org.springrain.weixin.sdk.mp.api.IWxMpService;


@Service("wxMpConfigService")
public class WxMpConfigServiceImpl extends BaseSpringrainServiceImpl implements IWxMpConfigService {
	
	@Resource
	private IWxMpService wxMpService;
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


	@Override
	public Map<String, String> findMpJsapiParam(String siteId,
			HttpServletRequest request) throws WxErrorException {
		Map<String, String> params = new HashMap<String, String>();
		IWxMpConfig wxMpConfig = this.findWxMpConfigById(siteId);
		
		String jsapiTicket=wxMpService.getJsApiTicket(wxMpConfig);
		String nonceStr=RandomStringUtils.random(32, "123456789"); // 8位随机数
		String url = request.getRequestURL().toString()+(request.getQueryString()==null?"":"?"+request.getQueryString());
		String timestamp=System.currentTimeMillis() / 1000 + "";
		String jsApiSignature=this.getSignature(nonceStr, jsapiTicket, timestamp, url);
		
		params.put("timestamp", timestamp);
		params.put("nonceStr", nonceStr);
		params.put("jsApiSignature", jsApiSignature);
		params.put("appId", wxMpConfig.getAppId());
		return params;
	}


	private String getSignature(String nonceStr, String jsapiTicket,
			String timestamp, String url) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("noncestr", nonceStr);
		packageParams.put("jsapi_ticket", jsapiTicket);
		packageParams.put("timestamp", timestamp);
		packageParams.put("url", url);
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
				sb.append(k + "=" + v + "&");
		}
		return SHA1.gen(sb.toString().substring(0,sb.length()-1));
	}


	@Override
	public WxAccessToken getCustomAPIAccessToken(IWxMpConfig wxmpconfig) {
		
		
		return null;
	}
	

}
