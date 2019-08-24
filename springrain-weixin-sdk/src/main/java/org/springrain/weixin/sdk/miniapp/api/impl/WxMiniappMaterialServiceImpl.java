package org.springrain.weixin.sdk.miniapp.api.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.bean.result.WxMediaUploadResult;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfig;
import org.springrain.weixin.sdk.common.service.WxConsts;
import org.springrain.weixin.sdk.common.util.fs.FileUtils;
import org.springrain.weixin.sdk.common.util.http.MediaDownloadRequestExecutor;
import org.springrain.weixin.sdk.common.util.http.MediaUploadRequestExecutor;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;
import org.springrain.weixin.sdk.mp.bean.material.WxMediaImgUploadResult;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterial;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialArticleUpdate;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialCountResult;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialFileBatchGetResult;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialNews;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialUploadResult;
import org.springrain.weixin.sdk.mp.bean.material.WxMpMaterialVideoInfoResult;
import org.springrain.weixin.sdk.mp.util.http.MaterialDeleteRequestExecutor;
import org.springrain.weixin.sdk.mp.util.http.MaterialNewsInfoRequestExecutor;
import org.springrain.weixin.sdk.mp.util.http.MaterialUploadRequestExecutor;
import org.springrain.weixin.sdk.mp.util.http.MaterialVideoInfoRequestExecutor;
import org.springrain.weixin.sdk.mp.util.http.MaterialVoiceAndImageDownloadRequestExecutor;
import org.springrain.weixin.sdk.mp.util.http.MediaImgUploadRequestExecutor;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappMaterialService;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappService;

/**
 * Created by springrain on 2017/1/8.
 */


public class WxMiniappMaterialServiceImpl implements IWxMiniappMaterialService {
	private final   Logger logger = LoggerFactory.getLogger(getClass());
	
	
  private static final String MEDIA_API_URL_PREFIX = WxConsts.mpapiurl+"/cgi-bin/media";
  private static final String MATERIAL_API_URL_PREFIX = WxConsts.mpapiurl+"/cgi-bin/material";
  
  private IWxMiniappService wxMiniappService;

  public WxMiniappMaterialServiceImpl() {
  }
  public WxMiniappMaterialServiceImpl(IWxMiniappService wxMiniappService) {
	  this.wxMiniappService=wxMiniappService;
  }

  @Override
  public WxMediaUploadResult mediaUpload(IWxMiniappConfig wxminiappconfig, String mediaType, String fileType, InputStream inputStream) throws WxErrorException {
    try {
      return this.mediaUpload(wxminiappconfig,mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
    } catch (IOException e) {
    	logger.error(e.getMessage(),e);
      throw new WxErrorException(WxError.newBuilder().setErrorMsg(e.getMessage()).build());
    }
  }
  
  @Override
  public WxMediaUploadResult mediaUpload(IWxMiniappConfig wxminiappconfig, File file) throws WxErrorException {
  	return mediaUpload(wxminiappconfig, "image", file);
  }

  @Override
  public WxMediaUploadResult mediaUpload(IWxMiniappConfig wxminiappconfig, String mediaType, File file) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/upload?type=" + mediaType;
    return wxMiniappService.execute(wxminiappconfig,new MediaUploadRequestExecutor(), url, file);
  }

  @Override
  public File mediaDownload(IWxMiniappConfig wxminiappconfig, String media_id) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/get";
    return wxMiniappService.execute(wxminiappconfig,
      new MediaDownloadRequestExecutor(new File(wxminiappconfig.getTmpDirFile())),
      url,
      "media_id=" + media_id);
  }

  @Override
  public WxMediaImgUploadResult mediaImgUpload(IWxMiniappConfig wxminiappconfig, File file) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/uploadimg";
    return wxMiniappService.execute(wxminiappconfig,new MediaImgUploadRequestExecutor(), url, file);
  }

  @Override
  public WxMpMaterialUploadResult materialFileUpload(IWxMiniappConfig wxminiappconfig, String mediaType, WxMpMaterial material) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/add_material?type=" + mediaType;
    return wxMiniappService.execute(wxminiappconfig,new MaterialUploadRequestExecutor(), url, material);
  }

  @Override
  public WxMpMaterialUploadResult materialNewsUpload(IWxMiniappConfig wxminiappconfig, WxMpMaterialNews news) throws WxErrorException {
    if (news == null || news.isEmpty()) {
      throw new IllegalArgumentException("news is empty!");
    }
    String url = MATERIAL_API_URL_PREFIX + "/add_news";
    String responseContent = this.wxMiniappService.post(wxminiappconfig,url, news.toJson());
    return WxMpMaterialUploadResult.fromJson(responseContent);
  }

  @Override
  public InputStream materialImageOrVoiceDownload(IWxMiniappConfig wxminiappconfig, String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return wxMiniappService.execute(wxminiappconfig,new MaterialVoiceAndImageDownloadRequestExecutor(new File(wxminiappconfig.getTmpDirFile())), url, media_id);
  }

  @Override
  public WxMpMaterialVideoInfoResult materialVideoInfo(IWxMiniappConfig wxminiappconfig, String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return wxMiniappService.execute(wxminiappconfig,new MaterialVideoInfoRequestExecutor(), url, media_id);
  }

  @Override
  public WxMpMaterialNews materialNewsInfo(IWxMiniappConfig wxminiappconfig, String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return wxMiniappService.execute(wxminiappconfig,new MaterialNewsInfoRequestExecutor(), url, media_id);
  }

  @Override
  public boolean materialNewsUpdate(IWxMiniappConfig wxminiappconfig, WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/update_news";
    String responseText = this.wxMiniappService.post(wxminiappconfig,url, wxMpMaterialArticleUpdate.toJson());
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return true;
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public boolean materialDelete(IWxMiniappConfig wxminiappconfig, String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/del_material";
    return wxMiniappService.execute(wxminiappconfig,new MaterialDeleteRequestExecutor(), url, media_id);
  }

  @Override
  public WxMpMaterialCountResult materialCount(IWxMiniappConfig wxminiappconfig) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_materialcount";
    String responseText = this.wxMiniappService.get(wxminiappconfig,url, null);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxJsonBuilder.fromJson(responseText, WxMpMaterialCountResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialNewsBatchGetResult materialNewsBatchGet(IWxMiniappConfig wxminiappconfig, int offset, int count) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", WxConsts.MATERIAL_NEWS);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxMiniappService.post(wxminiappconfig,url, WxJsonBuilder.toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxJsonBuilder.fromJson(responseText, WxMpMaterialNewsBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialFileBatchGetResult materialFileBatchGet(IWxMiniappConfig wxminiappconfig, String type, int offset, int count) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", type);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = wxMiniappService.post(wxminiappconfig,url, WxJsonBuilder.toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxJsonBuilder.fromJson(responseText, WxMpMaterialFileBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }


}
