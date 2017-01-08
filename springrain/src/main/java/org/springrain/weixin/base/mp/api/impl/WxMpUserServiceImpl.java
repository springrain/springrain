package org.springrain.weixin.base.mp.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.api.IWxMpService;
import org.springrain.weixin.base.mp.api.IWxMpUserService;
import org.springrain.weixin.base.mp.bean.WxMpUserQuery;
import org.springrain.weixin.base.mp.bean.result.WxMpUser;
import org.springrain.weixin.base.mp.bean.result.WxMpUserList;
import org.springrain.weixin.entity.WxMpConfig;

import com.google.gson.JsonObject;

/**
 * Created by springrain on 2017/1/8.
 */
@Service("wxMpUserService")
public class WxMpUserServiceImpl implements IWxMpUserService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user";
 
  @Resource
  private IWxMpService iWxMpService;

  public WxMpUserServiceImpl() {
  }
  public WxMpUserServiceImpl(IWxMpService iWxMpService) {
	  this.iWxMpService=iWxMpService;
  }

  @Override
  public void userUpdateRemark(WxMpConfig wxmpconfig,String openid, String remark) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/updateremark";
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.iWxMpService.post(wxmpconfig,url, json.toString());
  }

  @Override
  public WxMpUser userInfo(WxMpConfig wxmpconfig,String openid) throws WxErrorException {
    return this.userInfo(wxmpconfig,openid, null);
  }

  @Override
  public WxMpUser userInfo(WxMpConfig wxmpconfig,String openid, String lang) throws WxErrorException {
    String url = API_URL_PREFIX + "/info";
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.iWxMpService.get(wxmpconfig,url,
        "openid=" + openid + "&lang=" + lang);
    return WxMpUser.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList(WxMpConfig wxmpconfig,String next_openid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.iWxMpService.get(wxmpconfig,url,
        next_openid == null ? null : "next_openid=" + next_openid);
    return WxMpUserList.fromJson(responseContent);
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpConfig wxmpconfig,List<String> openids)
      throws WxErrorException {
    return this.userInfoList(wxmpconfig,new WxMpUserQuery(openids));
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpConfig wxmpconfig,WxMpUserQuery userQuery) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/batchget";
    String responseContent = this.iWxMpService.post(wxmpconfig,url,
        userQuery.toJsonString());
    return WxMpUser.fromJsonList(responseContent);
  }

}
