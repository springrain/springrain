package org.springrain.weixin.sdk.common.util;


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
import org.springrain.weixin.entity.WxCpConfig;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.cp.api.IWxCpService;
import org.springrain.weixin.sdk.cp.api.WxCpServiceImpl;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.api.impl.WxMpServiceImpl;

@SuppressWarnings("deprecation")
public class WechatBuilder {
	
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getCpService_'+#siteId")
	public static IWxCpService getCpService(String siteId){
		String corpId = "";
		String secret = "";
		IWxCpService service = new WxCpServiceImpl();
		WxCpConfig   config = new WxCpConfig();
		config.setCorpId(corpId);
		config.setCorpSecret(secret);
		//config.setExpiresTime(3600000);
		
		return service;
	}
	
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getMpService_'+#siteId")
	public static IWxMpService getMpService(String siteId){
		String appid = "";
		String secret = "";
		String token = "";
		String partnerId = "";
		String partnerKey = "";
		WxMpConfig config = new WxMpConfig();
		config.setAppId(appid); // 设置微信公众号的appid
		config.setSecret(secret); // 设置微信公众号的app corpSecret
		config.setToken(token);
		config.setPartnerId(partnerId);
		config.setPartnerKey(partnerKey);
		//config.setExpiresTime(3600000);
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
	        
			//config.setSSLContext(context);
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
		IWxMpService service = new WxMpServiceImpl();
		//service.setWxMpConfigStorage(config);
		return service;
	}
}
