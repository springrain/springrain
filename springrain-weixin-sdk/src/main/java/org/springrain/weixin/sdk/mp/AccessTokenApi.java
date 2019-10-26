/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.sdk.common.WxAccessToken;
import org.springrain.weixin.sdk.common.WxCardTicket;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.WxJsTicket;
import org.springrain.weixin.sdk.common.wxconfig.IWxConfig;

import java.util.Map;

/**
 * 认证并获取 access_token API
 * https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/Nontax_Bill/API_list.html
 * <p>
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#62
 * <p>
 * 微信卡券接口签名凭证 api_ticket
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#54
 */
public class AccessTokenApi {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenApi.class);

    // "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String accessTokenUrl = WxConsts.mpapiurl + "/cgi-bin/token";

    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/ticket/getticket?access_token=";

    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     *
     * @return AccessToken accessToken
     */
    public static WxAccessToken getAccessToken(IWxConfig config) {
        String apiurl = accessTokenUrl + "?grant_type=client_credential&appid=" + config.getAppId() + "&secret=" + config.getSecret();
        String jsonResult = HttpClientUtils.sendHttpGet(apiurl);
        Map map = JsonUtils.readValue(jsonResult, Map.class);

        String accessToken = (String) map.get("access_token");

        if (StringUtils.isBlank(accessToken)) {
            logger.error("get accessToken error,config:" + JsonUtils.writeValueAsString(config) + ",apiurl:" + apiurl + ",jsonResult:" + jsonResult);
            return null;
        }

        WxAccessToken wxAccessToken = new WxAccessToken();
        wxAccessToken.setAppId(config.getAppId());
        wxAccessToken.setAccessToken(accessToken);
        wxAccessToken.setExpiresIn((Integer) map.get("expires_in"));

        return wxAccessToken;
    }


    /**
     * http GET请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     *
     * @param config
     * @return JsTicket
     */
    public static WxJsTicket getJsTicket(IWxConfig config) {
        String access_token = config.getAccessToken();
        String apiurl = apiUrl + access_token + "&type=jsapi";
        String jsonResult = HttpClientUtils.sendHttpPost(apiurl);

        Map map = JsonUtils.readValue(jsonResult, Map.class);

        String ticket = (String) map.get("ticket");

        if (StringUtils.isBlank(ticket)) {
            logger.error("get jsticket error,config:" + JsonUtils.writeValueAsString(config) + ",apiurl:" + apiurl + ",jsonResult:" + jsonResult);
            return null;
        }

        WxJsTicket wxJsTicket = new WxJsTicket();
        wxJsTicket.setAppId(config.getAppId());
        wxJsTicket.setJsTicket(ticket);
        wxJsTicket.setExpiresIn((Integer) map.get("expires_in"));
        return wxJsTicket;
    }

    public static WxCardTicket getCardTicket(IWxConfig config) {
        String access_token = config.getAccessToken();
        String apiurl = apiUrl + access_token + "&type=wx_card";
        String jsonResult = HttpClientUtils.sendHttpPost(apiurl);

        Map map = JsonUtils.readValue(jsonResult, Map.class);

        String ticket = (String) map.get("ticket");

        if (StringUtils.isBlank(ticket)) {
            logger.error("get cardticket error,config:" + JsonUtils.writeValueAsString(config) + ",apiurl:" + apiurl + ",jsonResult:" + jsonResult);
            return null;
        }

        WxCardTicket wxCardTicket = new WxCardTicket();
        wxCardTicket.setAppId(config.getAppId());
        wxCardTicket.setCardTicket(ticket);
        wxCardTicket.setExpiresIn((Integer) map.get("expires_in"));

        return wxCardTicket;
    }


}
