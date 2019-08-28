package org.springrain.weixin.entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "wx_miniappconfig")
public class WxMiniappConfig extends BaseEntity implements IWxMiniappConfig {
    private static final long serialVersionUID = 1L;



    //columns START
    /**
     * id
     */
    private java.lang.String id;
    /**
     * 站点Id
     */
    private java.lang.String siteId;


    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String string) {

    }

    @Override
    public String getAppId() {
        return null;
    }

    @Override
    public void setAppId(String appId) {

    }

    @Override
    public String getSecret() {
        return null;
    }

    @Override
    public void setSecret(String secret) {

    }

    @Override
    public String getJsCode() {
        return null;
    }

    @Override
    public void setJsCode(String jsCode) {

    }

    @Override
    public String getSessionKey() {
        return null;
    }

    @Override
    public void setSessionKey(String sessionKey) {

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
}
