package org.springrain.alipay.entity;

import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;

import java.io.Serializable;

public class AliPayConfig implements IAliPayConfig, Serializable {

    private String privateKey;
    private String aliPayPublicKey;
    private String appId;
    private String serviceUrl;
    private String charset = "UTF-8";
    private String signType = "RSA2";
    private String format = "json";
    private String appCertPath;
    private String aliPayCertPath;
    private String aliPayRootCertPath;


    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAppCertPath() {
        return appCertPath;
    }

    public void setAppCertPath(String appCertPath) {
        this.appCertPath = appCertPath;
    }

    public String getAliPayCertPath() {
        return aliPayCertPath;
    }

    public void setAliPayCertPath(String aliPayCertPath) {
        this.aliPayCertPath = aliPayCertPath;
    }

    public String getAliPayRootCertPath() {
        return aliPayRootCertPath;
    }

    public void setAliPayRootCertPath(String aliPayRootCertPath) {
        this.aliPayRootCertPath = aliPayRootCertPath;
    }


}
