/**
 * Copyright (c) 2011-2015, Unas 小强哥 (unas@qq.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp.api;

import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpClientUtils;

/**
 * 获取用户基本信息(UnionID机制)
 * <p>
 * http://mp.weixin.qq.com/wiki/1/8a5ce6257f1d3b2afb20f83e72b72ce9.html
 */
public class SnsApi {
    private static String getUserInfo = WxConsts.mpapiurl + "/sns/userinfo";

    /**
     * 获取用户个人信
     *
     * @param accessToken 调用凭证access_token
     * @param openId      普通用户的标识，对当前开发者帐号唯一
     * @return ApiResult
     */
    public static ApiResult getUserInfo(String accessToken, String openId) {
        ParaMap pm = ParaMap.create("access_token", accessToken).put("openid", openId).put("lang", "zh_CN");
        return new ApiResult(HttpClientUtils.sendHttpGet(getUserInfo, pm.getData()));
    }

}