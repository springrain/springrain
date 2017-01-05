package org.springrain.weixin.base.common.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.weixin.base.cp.api.WxCpInMemoryConfigStorage;
import org.springrain.weixin.base.cp.api.WxCpService;
import org.springrain.weixin.base.cp.api.WxCpServiceImpl;
import org.springrain.weixin.base.mp.api.WxMpInMemoryConfigStorage;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.api.impl.WxMpServiceImpl;

@SuppressWarnings("deprecation")
public class WechatBuilder {
	
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getCpService_'+#siteId")
	public static WxCpService getCpService(String siteId){
		String corpId = "";
		String secret = "";
		WxCpService service = new WxCpServiceImpl();
		WxCpInMemoryConfigStorage  config = new WxCpInMemoryConfigStorage();
		config.setCorpId(corpId);
		config.setCorpSecret(secret);
		config.setExpiresTime(3600000);
		
		return service;
	}
	
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getMpService_'+#siteId")
	public static WxMpService getMpService(String siteId){
		String appid = "";
		String secret = "";
		String token = "";
		String partnerId = "";
		String partnerKey = "";
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(appid); // 设置微信公众号的appid
		config.setSecret(secret); // 设置微信公众号的app corpSecret
		config.setToken(token);
		config.setPartnerId(partnerId);
		config.setPartnerKey(partnerKey);
		config.setExpiresTime(3600000);
		//设置证书校验信息
		KeyStore keyStore;
		try {
			String p12FilePath = "";
			String mchid = "";
			keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(p12FilePath);
            keyStore.load(instream, mchid.toCharArray());
	        
            //SSLContext context = SSLContexts.custom().loadKeyMaterial(keyStore, mchid.toCharArray()).build();
            
	        SSLContext context = SSLContextBuilder.create().loadKeyMaterial(keyStore, mchid.toCharArray()).build();
	        
			config.setSSLContext(context);
		} catch (KeyStoreException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
		WxMpService service = new WxMpServiceImpl();
		service.setWxMpConfigStorage(config);
		return service;
	}
}
