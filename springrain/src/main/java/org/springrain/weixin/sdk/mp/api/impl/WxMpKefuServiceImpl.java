package org.springrain.weixin.sdk.mp.api.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.WxConsts;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.bean.result.WxMediaUploadResult;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.util.http.MediaUploadRequestExecutor;
import org.springrain.weixin.sdk.mp.api.IWxMpKefuService;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.bean.kefu.WxMpKefuMessage;
import org.springrain.weixin.sdk.mp.bean.kefu.request.WxMpKfAccountRequest;
import org.springrain.weixin.sdk.mp.bean.kefu.request.WxMpKfSessionRequest;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfList;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfMsgList;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfOnlineList;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfSessionGetResult;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfSessionList;
import org.springrain.weixin.sdk.mp.bean.kefu.result.WxMpKfSessionWaitCaseList;

import com.google.gson.JsonObject;

/**
 *
 * @author springrain
 *
 */
@Service("wxMpKefuService")
public class WxMpKefuServiceImpl implements IWxMpKefuService {
//	 private final Logger log = LoggerFactory.getLogger(getClass());
  private static final String API_URL_PREFIX = WxConsts.mpapiurl+"/customservice";
  private static final String API_URL_PREFIX_WITH_CGI_BIN = WxConsts.mpapiurl+"/cgi-bin/customservice";
  
  //生产环境应该是spring注入
  @Resource
  private IWxMpService wxMpService;

  public WxMpKefuServiceImpl() {
  }
  
  public WxMpKefuServiceImpl(IWxMpService wxMpService) {
	  this.wxMpService=wxMpService;
  }
  
  

  @Override
  public boolean sendKefuMessage(WxMpConfig wxmpconfig,WxMpKefuMessage message)
      throws WxErrorException {
    String url = WxConsts.mpapiurl+"/cgi-bin/message/custom/send";
    String responseContent = wxMpService.post(wxmpconfig,url, message.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfList kfList(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getkflist";
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxMpKfList.fromJson(responseContent);
  }

  @Override
  public WxMpKfOnlineList kfOnlineList(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getonlinekflist";
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxMpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMpConfig wxmpconfig,WxMpKfAccountRequest request)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/add";
    String responseContent = wxMpService.post(wxmpconfig,url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMpConfig wxmpconfig,WxMpKfAccountRequest request)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/update";
    String responseContent = wxMpService.post(wxmpconfig,url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxMpConfig wxmpconfig,WxMpKfAccountRequest request) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/inviteworker";
    String responseContent = wxMpService.post(wxmpconfig,url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(WxMpConfig wxmpconfig,String kfAccount, File imgFile)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/uploadheadimg?kf_account=" + kfAccount;
    WxMediaUploadResult responseContent = wxMpService
        .execute(wxmpconfig,new MediaUploadRequestExecutor(), url, imgFile);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(WxMpConfig wxmpconfig,String kfAccount) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/del?kf_account=" + kfAccount;
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(WxMpConfig wxmpconfig,String openid, String kfAccount)
      throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/create";
    String responseContent = wxMpService.post(wxmpconfig,url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(WxMpConfig wxmpconfig,String openid, String kfAccount)
      throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/close";
    String responseContent = wxMpService.post(wxmpconfig,url, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfSessionGetResult kfSessionGet(WxMpConfig wxmpconfig,String openid)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsession?openid=" + openid;
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxMpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionList kfSessionList(WxMpConfig wxmpconfig,String kfAccount)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsessionlist?kf_account=" + kfAccount;
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxMpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionWaitCaseList kfSessionGetWaitCase(WxMpConfig wxmpconfig)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getwaitcase";
    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxMpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(WxMpConfig wxmpconfig,Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException {
    if(number > 10000){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法参数请求，每次最多查询10000条记录！").build());
    }

    if(startTime.after(endTime)){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("起始时间不能晚于结束时间！").build());
    }

    String url = API_URL_PREFIX + "/msgrecord/getmsglist";

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000); //starttime	起始时间，unix时间戳
    param.addProperty("endtime", endTime.getTime() / 1000); //endtime	结束时间，unix时间戳，每次查询时段不能超过24小时
    param.addProperty("msgid", msgId); //msgid	消息id顺序从小到大，从1开始
    param.addProperty("number", number); //number	每次获取条数，最多10000条

    String responseContent = wxMpService.post(wxmpconfig,url, param.toString());

    return WxMpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(WxMpConfig wxmpconfig,Date startTime, Date endTime) throws WxErrorException {
    int number = 10000;
    WxMpKfMsgList result =  kfMsgList(wxmpconfig,startTime,endTime, 1L, number);

    if(result != null && result.getNumber() == number){
      Long msgId = result.getMsgId();
      WxMpKfMsgList followingResult =  kfMsgList(wxmpconfig,startTime,endTime, msgId, number);
      while(followingResult != null  && followingResult.getRecords().size() > 0){
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = kfMsgList(wxmpconfig,startTime,endTime, followingResult.getMsgId(), number);
      }
    }

    return result;
  }

}
