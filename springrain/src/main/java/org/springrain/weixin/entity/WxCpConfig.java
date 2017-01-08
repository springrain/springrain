package org.springrain.weixin.entity;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.api.IWxConfig;

public class WxCpConfig   extends BaseEntity implements IWxConfig {
	private static final long serialVersionUID = 1L;
	
	   private volatile String id;
	
	  private volatile String appId;
	  private volatile String secret;
	  private volatile String partnerId;
	  private volatile String partnerKey;
	  private volatile String token;
	  private volatile String aesKey;
	 

	  private volatile String oauth2redirectUri;

	  private volatile String httpProxyHost;
	  private volatile Integer httpProxyPort;
	  private volatile String httpProxyUsername;
	  private volatile String httpProxyPassword;

	  
	  
	  private volatile String certificateFile ;
	  private volatile String tmpDirFile;
	  
	  private volatile String corpId;
	  private volatile String corpSecret;
	  private volatile Integer agentId;
	  
	  
	  
	  private volatile String accessToken;
	  private volatile Long expiresTime;
	  
	  private volatile String jsApiTicket;
	  private volatile Long jsApiTicketExpiresTime;
	  
	  private volatile String cardApiTicket;
	  private volatile Long cardApiTicketExpiresTime;
	  
	  
	  @Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getJsApiTicket() {
		return jsApiTicket;
	}
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	
	public String getCardApiTicket() {
		return cardApiTicket;
	}
	public void setCardApiTicket(String cardApiTicket) {
		this.cardApiTicket = cardApiTicket;
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
	
	
	@Transient
	public Long getExpiresTime() {
		return expiresTime;
	}
	public void setExpiresTime(Long expiresTime) {
		this.expiresTime =  System.currentTimeMillis() + (expiresTime - 600) * 1000L;//预留10分钟
	}
	
	
	@Transient
	public Long getCardApiTicketExpiresTime() {
		return cardApiTicketExpiresTime;
	}
	public void setCardApiTicketExpiresTime(Long cardApiTicketExpiresTime) {
		//预留10分钟
		this.cardApiTicketExpiresTime = System.currentTimeMillis() + (cardApiTicketExpiresTime - 600) * 1000L;//预留10分钟
	}

	@Transient
	public Long getJsApiTicketExpiresTime() {
		return jsApiTicketExpiresTime;
	}
	public void setJsApiTicketExpiresTime(Long jsapiTicketExpiresTime) {
		this.jsApiTicketExpiresTime =  System.currentTimeMillis() + (jsApiTicketExpiresTime - 600) * 1000L;//预留10分钟
	}
	
	
	
	@Transient
	public boolean isAccessTokenExpired() {
		 return System.currentTimeMillis() > this.expiresTime;
	}
	@Transient
	public boolean isJsApiTicketExpired() {
	    return System.currentTimeMillis() > this.jsApiTicketExpiresTime;
	  }
	@Transient
	public boolean isCardApiTicketExpired() {
	    return System.currentTimeMillis() > this.cardApiTicketExpiresTime;
	  }
	@Transient
	public boolean autoRefreshToken() {
	    return true;
	  }
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getCorpSecret() {
		return corpSecret;
	}
	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

}
