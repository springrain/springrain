package org.springrain.weixin.base.mp.api.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springrain.weixin.base.common.bean.menu.WxMenu;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.api.IWxMpMenuService;
import org.springrain.weixin.base.mp.api.IWxMpService;
import org.springrain.weixin.base.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import org.springrain.weixin.entity.WxMpConfig;

/**
 * Created by springrain on 2017/1/8.
 */

@Service("wxMpMenuService")
public class WxMpMenuServiceImpl implements IWxMpMenuService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu";
  private static Logger log = LoggerFactory.getLogger(WxMpMenuServiceImpl.class);

  @Resource
  private IWxMpService iWxMpService;

  public WxMpMenuServiceImpl() {
  }
  public WxMpMenuServiceImpl(IWxMpService iWxMpService) {
	  this.iWxMpService=iWxMpService;
  }

  @Override
  public void menuCreate(WxMpConfig wxmpconfig,WxMenu menu) throws WxErrorException {
    String menuJson = menu.toJson();
    String url = API_URL_PREFIX + "/create";
    if (menu.getMatchRule() != null) {
      url = API_URL_PREFIX + "/addconditional";
    }

    log.debug("开始创建菜单：{}", menuJson);

    String result =iWxMpService.post(wxmpconfig,url, menuJson);
    log.debug("创建菜单：{},结果：{}", menuJson, result);
  }

  @Override
  public void menuDelete(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = API_URL_PREFIX + "/delete";
    String result =iWxMpService.get(wxmpconfig,url, null);
    log.debug("删除菜单结果：{}", result);
  }

  @Override
  public void menuDelete(WxMpConfig wxmpconfig,String menuid) throws WxErrorException {
    String url = API_URL_PREFIX + "/delconditional";
    String result =iWxMpService.get(wxmpconfig,url, "menuid=" + menuid);
    log.debug("根据MeunId({})删除菜单结果：{}", menuid, result);
  }

  @Override
  public WxMenu menuGet(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    try {
      String resultContent =iWxMpService.get(wxmpconfig,url, null);
      return WxMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据
      if (e.getError().getErrorCode() == 46003) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMenu menuTryMatch(WxMpConfig wxmpconfig,String userid) throws WxErrorException {
    String url = API_URL_PREFIX + "/trymatch";
    try {
      String resultContent =iWxMpService.get(wxmpconfig,url, "user_id=" + userid);
      return WxMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据     46002 不存在的菜单版本
      if (e.getError().getErrorCode() == 46003
        || e.getError().getErrorCode() == 46002) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMpGetSelfMenuInfoResult getSelfMenuInfo(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
    String resultContent =iWxMpService.get(wxmpconfig,url, null);
    return WxMpGetSelfMenuInfoResult.fromJson(resultContent);
  }
}
