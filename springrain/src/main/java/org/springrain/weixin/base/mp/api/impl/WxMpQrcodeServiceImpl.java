package org.springrain.weixin.base.mp.api.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.common.util.http.SimplePostRequestExecutor;
import org.springrain.weixin.base.mp.api.WxMpQrcodeService;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.bean.result.WxMpQrCodeTicket;
import org.springrain.weixin.base.mp.util.http.QrCodeRequestExecutor;
import org.springrain.weixin.entity.WxMpConfig;

import com.google.gson.JsonObject;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxMpQrcodeServiceImpl implements WxMpQrcodeService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/qrcode";
  private WxMpService wxMpService;

  public WxMpQrcodeServiceImpl() {
  }

  @Override
  public WxMpQrCodeTicket qrCodeCreateTmpTicket(WxMpConfig wxmpconfig,int scene_id, Integer expire_seconds) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_SCENE");
    if (expire_seconds != null) {
      json.addProperty("expire_seconds", expire_seconds);
    }
    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_id", scene_id);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }

  @Override
  public WxMpQrCodeTicket qrCodeCreateLastTicket(WxMpConfig wxmpconfig,int scene_id) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_LIMIT_SCENE");
    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_id", scene_id);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }

  @Override
  public WxMpQrCodeTicket qrCodeCreateLastTicket(WxMpConfig wxmpconfig,String scene_str) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_LIMIT_STR_SCENE");
    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_str", scene_str);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }

  @Override
  public File qrCodePicture(WxMpConfig wxmpconfig,WxMpQrCodeTicket ticket) throws WxErrorException {
    String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    return wxMpService.execute(wxmpconfig,new QrCodeRequestExecutor(), url, ticket);
  }

  @Override
  public String qrCodePictureUrl(WxMpConfig wxmpconfig,String ticket, boolean needShortUrl) throws WxErrorException {
    String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
    try {
      String resultUrl = String.format(url,
              URLEncoder.encode(ticket, StandardCharsets.UTF_8.name()));
      if (needShortUrl) {
        return wxMpService.shortUrl(wxmpconfig,resultUrl);
      }

      return resultUrl;
    } catch (UnsupportedEncodingException e) {
      WxError error = WxError.newBuilder().setErrorCode(-1)
              .setErrorMsg(e.getMessage()).build();
      throw new WxErrorException(error);
    }
  }

  @Override
  public String qrCodePictureUrl(WxMpConfig wxmpconfig,String ticket) throws WxErrorException {
    return qrCodePictureUrl(wxmpconfig,ticket, false);
  }

}
