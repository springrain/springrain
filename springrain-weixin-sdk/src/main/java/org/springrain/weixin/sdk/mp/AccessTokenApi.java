/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp;

import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;

import java.util.Map;

/**
 * 认证并获取 access_token API
 * https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/Nontax_Bill/API_list.html
 *
 */
public class AccessTokenApi {

    // "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String accessTokenUrl = WxConsts.mpapiurl + "/cgi-bin/token?grant_type=client_credential";
    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     * @return AccessToken accessToken
     */
    public static String getAccessToken(IWxMpConfig wxmpconfig) {
        String apiurl=accessTokenUrl+"&appid="+wxmpconfig.getAppId()+"&secret="+wxmpconfig.getSecret();
        String jsonResult= HttpClientUtils.sendHttpGet(apiurl);
        Map map= JsonUtils.readValue(jsonResult, Map.class);

        String accessToken=(String)map.get("access_token");

        wxmpconfig.setAccessToken(accessToken);
        wxmpconfig.setAccessTokenExpiresTime(Long.valueOf((int)map.get("expires_in")));

        return accessToken;
    }


}
