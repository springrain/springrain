package org.springrain.weixin.sdk.common.util.json;

import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;
import org.springrain.weixin.sdk.common.bean.menu.WxMenu;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.bean.result.WxMediaUploadResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.Reader;

public class WxJsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
  }

  //public static Gson create() {
   // return INSTANCE.create();
  //}

  public static <T> T fromJson(String json, Class<T> clazz){
    return JsonUtils.readValue(json,clazz);
  }

  public static <T> T fromJson(Reader reader, Class<T> clazz){
    return JsonUtils.readValue(reader,clazz);
  }


  public static <T> T fromJson(InputStream stream, Class<T> clazz){
    return JsonUtils.readValue(stream,clazz);
  }

  public static String toJson(Object object){
    return JsonUtils.writeValueAsString(object);
  }



}
