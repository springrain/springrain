package org.springrain.weixin.sdk.mp.api;

import com.jfinal.weixin.sdk.utils.HttpClientUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 高级群发相关接口
 *
 * @author L.cm
 */
public class MessageApi {

    private static String sendAllUrl = WxConsts.mpapiurl + "/cgi-bin/message/mass/sendall?access_token=";
    private static String sendUrl = WxConsts.mpapiurl + "/cgi-bin/message/mass/send?access_token=";
    private static String previewUrl = WxConsts.mpapiurl + "/cgi-bin/message/mass/preview?access_token=";
    private static String getUrl = WxConsts.mpapiurl + "/cgi-bin/message/mass/get?access_token=";
    private static String deleteUrl = WxConsts.mpapiurl + "/cgi-bin/message/mass/delete?access_token=";

    private static ApiResult post(String baseUrl, String jsonStr) {
        String url = baseUrl + wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(url, jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 根据分组进行群发【订阅号与服务号认证后均可用】
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult sendAll(String jsonStr) {
        return post(sendAllUrl, jsonStr);
    }

    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult send(String jsonStr) {
        return post(sendUrl, jsonStr);
    }

    /**
     * 预览接口【订阅号与服务号认证后均可用】
     *
     * @param jsonStr json字符串
     * @return {ApiResult}
     */
    public static ApiResult preview(String jsonStr) {
        return post(previewUrl, jsonStr);
    }

    /**
     * 查询群发消息发送状态【订阅号与服务号认证后均可用】
     *
     * @param msgId 群发消息后返回的消息id
     * @return ApiResult
     */
    public static ApiResult get(String msgId) {
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("msg_id", msgId);

        return post(getUrl, JsonUtils.writeValueAsString(mapData));
    }

    /**
     * 删除群发【订阅号与服务号认证后均可用】
     * 由于技术限制，群发只有在刚发出的半小时内可以删除，发出半小时之后将无法被删除。
     *
     * @param msgId 群发消息后返回的消息id
     * @return ApiResult
     */
    public static ApiResult delete(String msgId) {
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("msg_id", msgId);

        return post(deleteUrl, JsonUtils.writeValueAsString(mapData));
    }

}