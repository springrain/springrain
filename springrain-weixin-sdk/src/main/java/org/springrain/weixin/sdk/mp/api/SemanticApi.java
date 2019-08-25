package org.springrain.weixin.sdk.mp.api;

import com.jfinal.weixin.sdk.utils.HttpClientUtils;

/**
 * 语义接口
 *
 * @author L.cm
 * 文档地址：http://mp.weixin.qq.com/wiki/0/0ce78b3c9524811fee34aba3e33f3448.html
 */
public class SemanticApi {

    private static String semanticUrl = WxConsts.mpapiurl + "/semantic/semproxy/search?access_token=";

    /**
     * 发送语义理解请求
     *
     * @param jsonStr POST数据格式：JSON
     * @return ApiResult
     */
    public static ApiResult search(String jsonStr) {
        String url = semanticUrl + wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(url, jsonStr);
        return new ApiResult(jsonResult);
    }

}
