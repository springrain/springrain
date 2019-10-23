package org.springrain.weixin.entity;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.wxconfig.IWxCpConfig;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_cpconfig")
public class WxCpConfig extends BaseEntity implements IWxCpConfig {
    private static final long serialVersionUID = 1L;

    private volatile String id;

    // 应用密钥
    private java.lang.String secret;
    // 开发者Id
    private java.lang.String appId;

    private String accessToken;
    private Long accessTokenExpiresTime = 0L;

    @Override
    @Id
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }


    @Override
    @Transient
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Transient
    public Long getAccessTokenExpiresTime() {
        return accessTokenExpiresTime;
    }

    @Override
    public void setAccessTokenExpiresTime(Long accessTokenExpiresTime) {
        this.accessTokenExpiresTime = accessTokenExpiresTime;
    }

    @Override
    public void setExpiresIn(Integer expiresIn) {
        // 生产遇到接近过期时间时,access_token在某些服务器上会提前失效,设置时间短一些
        // https://developers.weixin.qq.com/community/develop/doc/0008cc492503e8e04dc7d619754c00
        this.accessTokenExpiresTime = System.currentTimeMillis() + ((expiresIn / 12) * 1000L);
    }

    @Override
    @Transient
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.accessTokenExpiresTime;
    }


    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public void setSecret(String secret) {

    }


}
