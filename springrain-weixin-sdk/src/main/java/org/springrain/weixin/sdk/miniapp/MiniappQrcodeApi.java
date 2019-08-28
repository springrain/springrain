package org.springrain.weixin.sdk.miniapp;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.SecUtils;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

public class MiniappQrcodeApi {
    private static final Logger logger = LoggerFactory.getLogger(MiniappQrcodeApi.class);

    private MiniappQrcodeApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    private static String getUnlimitedUrl = WxConsts.mpapiurl+"/wxa/getwxacodeunlimit?access_token=";


    public static ApiResult getUnlimited(IWxMiniappConfig config, Map parameters) throws Exception {
        String apiurl=getUnlimitedUrl+config.getAccessToken();

        HttpPost httpPost = new HttpPost(apiurl);
        httpPost.setEntity(new StringEntity(JsonUtils.writeValueAsString(parameters)));
        OutputStream os = null;
        try (final CloseableHttpResponse response = HttpClientUtils.getHttpClient().execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            Header[] contentTypeHeader = response.getHeaders("Content-Type");
            if (contentTypeHeader != null && contentTypeHeader.length > 0
                    && ContentType.APPLICATION_JSON.getMimeType()
                    .equals(ContentType.parse(contentTypeHeader[0].getValue()).getMimeType())) { //如果是json格式
               String responseContent = EntityUtils.toString(entity, "UTF-8");
               return new ApiResult(responseContent);
            }else{
                byte[] byteArray = EntityUtils.toByteArray(entity);
                String dirpath=GlobalStatic.rootDir+"/upload/miniappqrcode";
                File dir=new File(GlobalStatic.rootDir+"/upload/miniappqrcode");
                if (!dir.exists()){
                    dir.mkdirs();
                }
                String filePath=dirpath+"/"+ SecUtils.getUUID()+".jpg";
                File file=new File(filePath);
                os = new BufferedOutputStream(new FileOutputStream(file));
                os.write(byteArray);
                os.flush();
                ApiResult apiResult=new ApiResult();
                apiResult.setFile(file);
                return apiResult;
            }


        } finally {
            httpPost.releaseConnection();
            if (os!=null){
                os.close();
            }
        }

    }






}
