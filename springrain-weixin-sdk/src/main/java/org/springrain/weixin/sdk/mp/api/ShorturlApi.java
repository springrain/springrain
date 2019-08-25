/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp.api;

import com.jfinal.weixin.sdk.utils.HttpClientUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 将一条长链接转成短链接 API
 * 文档地址：http://mp.weixin.qq.com/wiki/6/d2ec191ffdf5a596238385f75f95ecbe.html
 */
public class ShorturlApi {
    private static String apiUrl = WxConsts.mpapiurl + "/cgi-bin/shorturl?access_token=";

    public static ApiResult getShorturl(String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(apiUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
     * @return ApiResult 短连接信息
     */
    public static ApiResult getShortUrl(String longUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("action", "long2short");
        params.put("long_url", longUrl);
        return getShorturl(JsonUtils.writeValueAsString(params));
    }
}
