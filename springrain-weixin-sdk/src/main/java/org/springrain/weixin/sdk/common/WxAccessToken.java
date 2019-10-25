package org.springrain.weixin.sdk.common;

public class WxAccessToken implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


    private String accessToken;
    private Long accessTokenExpiresTime = 0L;
    private Integer expiresIn;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getAccessTokenExpiresTime() {
        return accessTokenExpiresTime;
    }


    public void setAccessTokenExpiresTime(Long accessTokenExpiresTime) {
        this.accessTokenExpiresTime = accessTokenExpiresTime;
    }

    public Integer getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        // 生产遇到接近过期时间时,access_token在某些服务器上会提前失效,设置时间短一些
        // https://developers.weixin.qq.com/community/develop/doc/0008cc492503e8e04dc7d619754c00
        this.accessTokenExpiresTime = System.currentTimeMillis() + ((expiresIn / 12) * 1000L);
    }


    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.accessTokenExpiresTime;
    }


}
