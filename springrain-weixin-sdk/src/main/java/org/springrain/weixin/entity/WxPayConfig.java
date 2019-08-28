package org.springrain.weixin.entity;

import org.springrain.weixin.sdk.common.wxconfig.IWxPayConfig;

import javax.persistence.Table;

@Table(name = "wx_payconfig")
public class WxPayConfig implements IWxPayConfig {


    private String id;
    private String appId;
    private String certificateFile;
    private String mchId;
    private String key;
    private String notifyUrl;
    private Boolean useSandbox;
    private String signType;


    @Override
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
    public String getAccessToken() {
        return null;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }

    @Override
    public void setAccessTokenExpiresTime(Long l) {

    }

    @Override
    public boolean isAccessTokenExpired() {
        return false;
    }

    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public void setSecret(String secret) {

    }

    @Override
    public String getCertificateFile() {
        return certificateFile;
    }

    @Override
    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    @Override
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public Boolean getUseSandbox() {
        return useSandbox;
    }

    public void setUseSandbox(Boolean useSandbox) {
        this.useSandbox = useSandbox;
    }

    @Override
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }


}
