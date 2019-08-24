package org.springrain.weixin.sdk.mp.util.http;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxConfig;
import org.springrain.weixin.sdk.common.util.http.RequestExecutor;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialNews;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MaterialNewsInfoRequestExecutor implements RequestExecutor<WxMpMaterialNews, String> {

    public MaterialNewsInfoRequestExecutor() {
        super();
    }

    @Override
    public WxMpMaterialNews execute(IWxConfig wxconfig, String uri, String materialId) throws WxErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (wxconfig.getHttpProxyHost() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(wxconfig.getHttpProxyHost(), wxconfig.getHttpProxyPort())).build();
            httpPost.setConfig(config);
        }

        Map<String, String> params = new HashMap<>();
        params.put("media_id", materialId);
        httpPost.setEntity(new StringEntity(WxJsonBuilder.toJson(params)));

        String responseContent = HttpClientUtils.sendHttpPost(httpPost);
        WxError error = WxError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        } else {
            return WxJsonBuilder.fromJson(responseContent, WxMpMaterialNews.class);
        }


    }

}
