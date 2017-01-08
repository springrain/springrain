package org.springrain.weixin.entity;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.base.common.api.IWxConfig;

public class WxCpConfig   extends BaseEntity implements IWxConfig {
	private static final long serialVersionUID = 1L;
	
	
	  private volatile String appId;
	  private volatile String secret;
	  private volatile String partnerId;
	  private volatile String partnerKey;
	  private volatile String token;
	  private volatile String accessToken;
	  private volatile String aesKey;
	  private volatile Long expiresTime;

	  private volatile String oauth2redirectUri;

	  private volatile String httpProxyHost;
	  private volatile Integer httpProxyPort;
	  private volatile String httpProxyUsername;
	  private volatile String httpProxyPassword;

	  private volatile String jsapiTicket;
	  private volatile Long jsapiTicketExpiresTime;

	  private volatile String cardApiTicket;
	  private volatile Long cardApiTicketExpiresTime;
	  
	  private volatile String certificateFile ;
	  
	  private volatile String tmpDirFile;
	  
	  
	  
	  
	  
	  
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	public Long getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(Long expiresTime) {
		this.expiresTime = expiresTime;
	}
	public String getOauth2redirectUri() {
		return oauth2redirectUri;
	}
	public void setOauth2redirectUri(String oauth2redirectUri) {
		this.oauth2redirectUri = oauth2redirectUri;
	}
	public String getHttpProxyHost() {
		return httpProxyHost;
	}
	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}
	public Integer getHttpProxyPort() {
		return httpProxyPort;
	}
	public void setHttpProxyPort(Integer httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}
	public String getHttpProxyUsername() {
		return httpProxyUsername;
	}
	public void setHttpProxyUsername(String httpProxyUsername) {
		this.httpProxyUsername = httpProxyUsername;
	}
	public String getHttpProxyPassword() {
		return httpProxyPassword;
	}
	public void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public Long getJsapiTicketExpiresTime() {
		return jsapiTicketExpiresTime;
	}
	public void setJsapiTicketExpiresTime(Long jsapiTicketExpiresTime) {
		this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
	}
	public String getCardApiTicket() {
		return cardApiTicket;
	}
	public void setCardApiTicket(String cardApiTicket) {
		this.cardApiTicket = cardApiTicket;
	}
	public Long getCardApiTicketExpiresTime() {
		return cardApiTicketExpiresTime;
	}
	public void setCardApiTicketExpiresTime(Long cardApiTicketExpiresTime) {
		this.cardApiTicketExpiresTime = cardApiTicketExpiresTime;
	}
	
	public String getCertificateFile() {
		return certificateFile;
	}
	public void setCertificateFile(String certificateFile) {
		this.certificateFile = certificateFile;
	}
	public String getTmpDirFile() {
		return tmpDirFile;
	}
	public void setTmpDirFile(String tmpDirFile) {
		this.tmpDirFile = tmpDirFile;
	}

}
