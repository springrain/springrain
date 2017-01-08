package org.springrain.weixin.sdk.mp.api.impl;

import java.text.Format;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.stereotype.Service;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.mp.api.IWxMpDataCubeService;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeArticleResult;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeArticleTotal;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeInterfaceResult;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeMsgResult;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeUserCumulate;
import org.springrain.weixin.sdk.mp.bean.datacube.WxDataCubeUserSummary;

import com.google.gson.JsonObject;

/**
 *  Created by springrain on 2017/1/8.
 * @author springrain (http://git.oschina.net/chunanyong/springrain)
 */
@Service("wxMpDataCubeService")
public class WxMpDataCubeServiceImpl implements IWxMpDataCubeService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/datacube";

  private final Format dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");

  
  //生产环境应该是spring bean
  @Resource
  private IWxMpService wxMpService;
  //private WxMpService wxMpService=new WxMpServiceImpl();

  public WxMpDataCubeServiceImpl() {
  }
  
  public WxMpDataCubeServiceImpl(IWxMpService wxMpService) {
	  this.wxMpService=wxMpService;
  }

  @Override
  public List<WxDataCubeUserSummary> getUserSummary(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusersummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeUserSummary.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeUserCumulate> getUserCumulate(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusercumulate";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeUserCumulate.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getArticleSummary(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getarticlesummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleTotal> getArticleTotal(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getarticletotal";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleTotal.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserRead(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getuserread";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserReadHour(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getuserreadhour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShare(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusershare";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeArticleResult> getUserShareHour(WxMpConfig wxmpconfig,Date beginDate, Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getusersharehour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeArticleResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsg(WxMpConfig wxmpconfig,Date beginDate, Date endDate)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsg";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgHour(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsghour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgWeek(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgweek";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgMonth(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgmonth";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDist(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdist";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdistweek";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getupstreammsgdistmonth";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeMsgResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummary(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getinterfacesummary";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }

  @Override
  public List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(WxMpConfig wxmpconfig,Date beginDate,
      Date endDate) throws WxErrorException {
    String url = API_URL_PREFIX + "/getinterfacesummaryhour";
    JsonObject param = new JsonObject();
    param.addProperty("begin_date", dateFormat.format(beginDate));
    param.addProperty("end_date", dateFormat.format(endDate));
    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());
    return WxDataCubeInterfaceResult.fromJson(responseContent);
  }
}
