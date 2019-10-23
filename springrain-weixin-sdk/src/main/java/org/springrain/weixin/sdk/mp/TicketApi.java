/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp;

import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;

import java.util.Map;

/**
 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#62
 * <p>
 * 微信卡券接口签名凭证 api_ticket
 * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/JS-SDK.html#54
 */
public class TicketApi {

    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/ticket/getticket?access_token=";

    /**
     * http GET请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）
     *
     * @param wxmpconfig
     * @return JsTicket
     */
    public static String getJsApiTicket(IWxMpConfig wxmpconfig) {
        String access_token = wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(apiUrl + access_token + "&type=jsapi");

        Map map = JsonUtils.readValue(jsonResult, Map.class);

        String ticket = (String) map.get("ticket");

        wxmpconfig.setJsApiTicket(ticket);
        wxmpconfig.setJsApiTicketExpiresTime(Long.valueOf((int) map.get("expires_in")));

        return ticket;
    }

    public static String getCardApiTicket(IWxMpConfig wxmpconfig) {
        String access_token = wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(apiUrl + access_token + "&type=wx_card");

        Map map = JsonUtils.readValue(jsonResult, Map.class);

        String ticket = (String) map.get("ticket");

        wxmpconfig.setCardApiTicket(ticket);
        wxmpconfig.setJsApiTicketExpiresIn((Integer) map.get("expires_in"));

        return ticket;
    }


}
