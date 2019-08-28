/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.open;

import org.apache.commons.lang3.StringUtils;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 网页授权获取 access_token API
 */
public class SnsAccessTokenApi {
    private static String snsAccessTokenUrl = WxConsts.mpapiurl + "/sns/oauth2/access_token";
    private static String authorize_uri = WxConsts.mpopenurl + "/connect/oauth2/authorize";
    private static String qrconnect_url = WxConsts.mpopenurl + "/connect/qrconnect";

    /**
     * 生成Authorize链接
     *
     * @param redirect_uri 回跳地址
     * @param snsapiBase   snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String redirect_uri, boolean snsapiBase) {
        return getAuthorizeURL(wxmpconfig, redirect_uri, null, snsapiBase);
    }

    /**
     * 生成Authorize链接
     *
     * @param redirectUri 回跳地址
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param snsapiBase  snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(IWxMpConfig wxmpconfig, String redirectUri, String state, boolean snsapiBase) {


        String apiurl = authorize_uri + "?appid=" + wxmpconfig.getAppId() + "&response_type=code&redirect_uri=" + redirectUri;


        // snsapi_base（不弹出授权页面，只能拿到用户openid）
        // snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        if (snsapiBase) {
            apiurl = apiurl + "&scope=snsapi_base";
        } else {
            apiurl = apiurl + "&scope=snsapi_userinfo";
        }

        if (StringUtils.isBlank(state)) {
            apiurl = apiurl + "&state=wx#wechat_redirect";
        } else {
            apiurl = apiurl + "&state=" + state.concat("#wechat_redirect");
        }

        return apiurl;
    }


    /**
     * 生成网页二维码授权链接
     *
     * @param redirect_uri 回跳地址
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig, String redirect_uri) {
        return getQrConnectURL(wxmpconfig, redirect_uri, null);
    }

    /**
     * 生成网页二维码授权链接
     *
     * @param redirect_uri 回跳地址
     * @param state        重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return url
     */
    public static String getQrConnectURL(IWxMpConfig wxmpconfig, String redirect_uri, String state) {
        String apiurl = qrconnect_url + "?appid" + wxmpconfig.getAppId() + "&response_type=code&redirect_uri=" + redirect_uri + "&scope=snsapi_login";
        if (StringUtils.isBlank(state)) {
            apiurl = apiurl + "&state=wx#wechat_redirect";
        } else {
            apiurl = apiurl + "&state=" + state.concat("#wechat_redirect");
        }
        return apiurl;
    }

    /**
     * 通过code获取access_token
     *
     * @param code 第一步获取的code参数
     * @return SnsAccessToken
     */
    public static SnsAccessToken getSnsAccessToken(IWxMpConfig wxmpconfig, String code) {
        //?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code
        final String accessTokenUrl = snsAccessTokenUrl + "?appid=" + wxmpconfig.getAppId() + "&secret=" + wxmpconfig.getSecret() + "&code=" + code + "&grant_type=authorization_code";
        String json = HttpClientUtils.sendHttpGet(accessTokenUrl);
        return new SnsAccessToken(json);
    }

}
