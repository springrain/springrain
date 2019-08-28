package org.springrain.weixin.sdk.common.wxconfig;

public interface IWxMpConfig  {
    String getId();// 业务Id

    void setId(String string);

    String getAppId();

    void setAppId(String string);

    String getToken();

    void setToken(String token);

    String getAesKey();

    void setAesKey(String aesKey);



    //开启oauth2.0认证,是否能够获取openId,0是关闭,1是开启
    Integer getOauth2();

    void setOauth2(Integer oauth2);


    String getAccessToken();

    void setAccessToken(String accessToken);

    void setAccessTokenExpiresTime(Long l);

    boolean isAccessTokenExpired();

    String getSecret();

    void setSecret(String string);

    String getJsApiTicket();

    void setJsApiTicket(String jsapiTicket);

    void setJsApiTicketExpiresTime(Long l);

    boolean isJsApiTicketExpired();

    String getCardApiTicket();

    void setCardApiTicket(String cardApiTicket);

    void setCardApiTicketExpiresTime(Long l);

    boolean isCardApiTicketExpired();




}
