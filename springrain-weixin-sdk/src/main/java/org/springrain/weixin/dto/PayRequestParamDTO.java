package org.springrain.weixin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @descriptions: js api支付请求参数
 * @author: xiaohua
 * @date: 2024/3/29 18:24
 * @version: 1.0
 */
public class PayRequestParamDTO implements Serializable {
    /**
     * appid
     */
    private String appId;
    /**
     * 时间戳
     */
    private String timeStamp;
    /**
     * 随机字符串
     */
    private String nonceStr;
    /**
     * 预支付信息,id
     */
    @JsonProperty("package")
    private String packageStr;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 微信签名
     */
    private String paySign;
    /**
     * 商户id
     */
    private String partenrid;
    /**
     * 预支付id
     */
    private String prepayid;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getPartenrid() {
        return partenrid;
    }

    public void setPartenrid(String partenrid) {
        this.partenrid = partenrid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
}
