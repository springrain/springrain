/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.open;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpClientUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;
import org.apache.commons.lang3.StringUtils;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 网页授权获取 access_token API
 */
public class SnsAccessTokenApi {
    private static String url = WxConsts.mpapiurl + "/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
    private static String authorize_uri = WxConsts.mpopenurl + "/connect/oauth2/authorize";
    private static String qrconnect_url = WxConsts.mpopenurl + "/connect/qrconnect";

    /**
     * 生成Authorize链接
     *
     * @param appId        应用id
     * @param redirect_uri 回跳地址
     * @param snsapiBase   snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String appId, String redirect_uri, boolean snsapiBase) {
        return getAuthorizeURL(wxmpconfig,appId, redirect_uri, null, snsapiBase);
    }

    /**
     * 生成Authorize链接
     *
     * @param appId       应用id
     * @param redirectUri 回跳地址
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param snsapiBase  snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig,String appId, String redirectUri, String state, boolean snsapiBase) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirectUri);
        // snsapi_base（不弹出授权页面，只能拿到用户openid）
        // snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        if (snsapiBase) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }
        if (StringUtils.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        String para = PaymentKit.packageSign(params, false);
        return authorize_uri + "?" + para;
    }


    /**
     * 生成网页二维码授权链接
     *
     * @param appId        应用id
     * @param redirect_uri 回跳地址
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig,String appId, String redirect_uri) {
        return getQrConnectURL(wxmpconfig,appId, redirect_uri, null);
    }

    /**
     * 生成网页二维码授权链接
     *
     * @param appId        应用id
     * @param redirect_uri 回跳地址
     * @param state        重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig,String appId, String redirect_uri, String state) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        params.put("scope", "snsapi_login");
        if (StringUtils.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        String para = PaymentKit.packageSign(params, false);
        return qrconnect_url + "?" + para;
    }

    /**
     * 通过code获取access_token
     *
     * @param code   第一步获取的code参数
     * @param appId  应用唯一标识
     * @param secret 应用密钥AppSecret
     * @return SnsAccessToken
     */
    public static SnsAccessToken getSnsAccessToken(IWxMpConfig wxmpconfig,String appId, String secret, String code) {
        final String accessTokenUrl = url.replace("{appid}", appId).replace("{secret}", secret).replace("{code}", code);
        String json = HttpClientUtils.sendHttpGet(accessTokenUrl);
        return new SnsAccessToken(json);
    }

}
