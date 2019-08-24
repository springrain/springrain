package org.springrain.weixin.sdk.miniapp.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfig;
import org.springrain.weixin.sdk.common.service.WxConsts;
import org.springrain.weixin.sdk.mp.bean.template.WxMpTemplate;
import org.springrain.weixin.sdk.mp.bean.template.WxMpTemplateIndustry;
import org.springrain.weixin.sdk.mp.bean.template.WxMpTemplateMessage;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappService;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappTemplateMsgService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * <pre>
 * Created by springrain on 2017/1/8.
 * @author <a href="http://git.oschina.net/chunanyong/springrain">springrain(springrain)</a>
 * </pre>
 */


public class WxMiniappTemplateMsgServiceImpl implements IWxMiniappTemplateMsgService {
  public static final String API_URL_PREFIX = WxConsts.mpapiurl+"/cgi-bin/template";
  private static final JsonParser JSON_PARSER = new JsonParser();

  @Resource
  private IWxMiniappService wxMiniappService;

  public WxMiniappTemplateMsgServiceImpl() {
  }
  
  public WxMiniappTemplateMsgServiceImpl(IWxMiniappService wxMiniappService) {
	  this.wxMiniappService=wxMiniappService;
  }

  @Override
  public String sendTemplateMsg(IWxMiniappConfig wxminiappconfig, WxMpTemplateMessage templateMessage) throws WxErrorException {
    String url = WxConsts.mpapiurl+"/cgi-bin/message/wxopen/template/send";
    String responseContent = wxMiniappService.post(wxminiappconfig,url, templateMessage.toJson());
    final JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() == 0) {
      return jsonObject.get("msgid").getAsString();
    }
    throw new WxErrorException(WxError.fromJson(responseContent));
  }

  @Override
  public boolean setIndustry(IWxMiniappConfig wxminiappconfig, WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
    if (null == wxMpIndustry.getPrimaryIndustry() || null == wxMpIndustry.getPrimaryIndustry().getId()
      || null == wxMpIndustry.getSecondIndustry() || null == wxMpIndustry.getSecondIndustry().getId()) {
      throw new IllegalArgumentException("行业Id不能为空，请核实");
    }

    String url = API_URL_PREFIX + "/api_set_industry";
    wxMiniappService.post(wxminiappconfig,url, wxMpIndustry.toJson());
    return true;
  }

  @Override
  public WxMpTemplateIndustry getIndustry(IWxMiniappConfig wxminiappconfig) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_industry";
    String responseContent = wxMiniappService.get(wxminiappconfig,url, null);
    return WxMpTemplateIndustry.fromJson(responseContent);
  }

  @Override
  public String addTemplate(IWxMiniappConfig wxminiappconfig, String shortTemplateId) throws WxErrorException {
    String url = API_URL_PREFIX + "/api_add_template";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id_short", shortTemplateId);
    String responseContent = wxMiniappService.post(wxminiappconfig,url, jsonObject.toString());
    final JsonObject result = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (result.get("errcode").getAsInt() == 0) {
      return result.get("template_id").getAsString();
    }

    throw new WxErrorException(WxError.fromJson(responseContent));
  }

  @Override
  public List<WxMpTemplate> getAllPrivateTemplate(IWxMiniappConfig wxminiappconfig) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_all_private_template";
    return WxMpTemplate.fromJson(wxMiniappService.get(wxminiappconfig,url, null));
  }

  @Override
  public boolean delPrivateTemplate(IWxMiniappConfig wxminiappconfig, String templateId) throws WxErrorException {
    String url = API_URL_PREFIX + "/del_private_template";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    String responseContent = wxMiniappService.post(wxminiappconfig,url, jsonObject.toString());
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(error);
  }

}
