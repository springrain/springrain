package org.springrain.weixin.entity;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.wxconfig.IWxCpConfig;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wx_cpconfig")
public class WxCpConfig extends BaseEntity implements IWxCpConfig {
    private static final long serialVersionUID = 1L;

    private volatile String id;

    private volatile String appId;
    private volatile String secret;
    private volatile String partnerId;
    private volatile String partnerKey;
    private volatile String token;
    private volatile String aesKey;
    private volatile Integer active;
    private volatile String siteId;

    private volatile String oauth2redirectUri;

    private volatile String certificateFile;
    private volatile String tmpDirFile;

    private volatile String corpId;
    private volatile String corpSecret;
    private volatile Integer agentId;

    private volatile String accessToken;
    private volatile Long accessTokenExpiresTime = 0L;

    private volatile String jsApiTicket;
    private volatile Long jsApiTicketExpiresTime = 0L;

    private volatile String cardApiTicket;
    private volatile Long cardApiTicketExpiresTime = 0L;

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
    public String getCorpId() {
        return null;
    }

    @Override
    public void setCorpId(String corpId) {

    }

    @Override
    public Integer getAgentId() {
        return null;
    }

    @Override
    public void setAgentId(Integer agentId) {

    }

    @Override
    public String getCorpSecret() {
        return null;
    }

    @Override
    public void setCorpSecret(String secret) {

    }

    @Override
    public String getOauth2redirectUri() {
        return null;
    }

    @Override
    public void setOauth2redirectUri(String oauth2redirectUri) {

    }


}
