/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package org.springrain.weixin.sdk.mp.api;

import com.jfinal.kit.HttpKit;
import com.jfinal.weixin.sdk.utils.HttpClientUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息 API
 * 文档地址：http://mp.weixin.qq.com/wiki/17/304c1885ea66dbedf7dc170d84999a9d.html
 */
public class TemplateMsgApi {
    private static String sendApiUrl = WxConsts.mpapiurl + "/cgi-bin/message/template/send?access_token=";
    private static String setIndustryUrl = WxConsts.mpapiurl + "/cgi-bin/template/api_set_industry?access_token=";
    private static String getIndustryUrl = WxConsts.mpapiurl + "/cgi-bin/template/get_industry?access_token=";
    private static String getTemplateIdUrl = WxConsts.mpapiurl + "/cgi-bin/template/api_add_template?access_token=";
    private static String getAllTemplateUrl = WxConsts.mpapiurl + "/cgi-bin/template/get_all_private_template?access_token=";
    private static String delTemplateUrl = WxConsts.mpapiurl + "/cgi-bin/template/del_private_template?access_token=";

    /**
     * 发送模板消息
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult send(String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(sendApiUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 设置所属行业
     *
     * @param industry_id1 公众号模板消息所属行业编号
     * @param industry_id2 公众号模板消息所属行业编号
     * @return {ApiResult}
     */
    public static ApiResult setIndustry(String industry_id1, String industry_id2) {
        String url = setIndustryUrl + wxmpconfig.getAccessToken();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("industry_id1", industry_id1);
        params.put("industry_id2", industry_id2);

        String jsonResult = HttpClientUtils.sendHttpPost(url, JsonUtils.writeValueAsString(params));
        return new ApiResult(jsonResult);
    }

    /**
     * 获取设置的行业信息
     *
     * @return {ApiResult}
     */
    public static ApiResult getIndustry() {
        return new ApiResult(HttpKit.get(getIndustryUrl + wxmpconfig.getAccessToken()));
    }

    /**
     * 获得模板ID
     *
     * @param templateIdShort 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @return {ApiResult}
     */
    public static ApiResult getTemplateId(String templateIdShort) {
        String url = getTemplateIdUrl + wxmpconfig.getAccessToken();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("template_id_short", templateIdShort);

        String json = HttpKit.post(url, JsonUtils.writeValueAsString(params));
        return new ApiResult(json);
    }

    /**
     * 获取模板列表
     *
     * @return {ApiResult}
     */
    public static ApiResult getAllTemplate() {
        return new ApiResult(HttpKit.get(getAllTemplateUrl + wxmpconfig.getAccessToken()));
    }

    /**
     * 删除模板
     *
     * @param templateId 公众帐号下模板消息ID
     * @return {ApiResult}
     */
    public static ApiResult delTemplateById(String templateId) {
        String url = delTemplateUrl + wxmpconfig.getAccessToken();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("template_id", templateId);

        String json = HttpKit.post(url, JsonUtils.writeValueAsString(params));
        return new ApiResult(json);
    }

}


