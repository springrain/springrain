package org.springrain.weixin.sdk.common.wxconfig;

public interface IWxMiniappConfig  {
    String getId();// 业务Id
    void setId(String string);

    String getAppId();

    void setAppId(String appId);

    String getSecret();

    void setSecret(String secret);

    String getJsCode();
    void setJsCode(String jsCode);

    String getSessionKey();

    void setSessionKey(String sessionKey);

    String getAccessToken();

    void setAccessToken(String accessToken);

    void setAccessTokenExpiresTime(Long l);

    boolean isAccessTokenExpired();



}
