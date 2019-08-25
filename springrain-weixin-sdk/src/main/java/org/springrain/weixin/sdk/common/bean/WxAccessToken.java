package org.springrain.weixin.sdk.common.bean;


import org.springrain.frame.util.JsonUtils;

import java.io.Serializable;
import java.util.Map;

public class WxAccessToken implements Serializable {
    private static final long serialVersionUID = 8709719312922168909L;

    private String accessToken;

    private int expiresIn = -1;

    private Integer errcode;        // 出错时有值
    private String errmsg;            // 出错时有值


    private String json;


    public WxAccessToken() {
    }

    public WxAccessToken(String jsonStr) {

        this.json = jsonStr;

        try {
            Map<String, Object> temp = JsonUtils.readValue(jsonStr, Map.class);
            accessToken = (String) temp.get("access_token");
            expiresIn = (Integer) temp.get("expires_in");
            errcode = (Integer) temp.get("errcode");
            errmsg = (String) temp.get("errmsg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }


    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
