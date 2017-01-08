package org.springrain.weixin.base.mp.api.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springrain.weixin.base.common.api.WxConsts;
import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.bean.result.WxMediaUploadResult;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.common.util.fs.FileUtils;
import org.springrain.weixin.base.common.util.http.MediaDownloadRequestExecutor;
import org.springrain.weixin.base.common.util.http.MediaUploadRequestExecutor;
import org.springrain.weixin.base.common.util.json.WxGsonBuilder;
import org.springrain.weixin.base.mp.api.IWxMpMaterialService;
import org.springrain.weixin.base.mp.api.IWxMpService;
import org.springrain.weixin.base.mp.bean.material.WxMediaImgUploadResult;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterial;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialArticleUpdate;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialCountResult;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialFileBatchGetResult;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialNews;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialUploadResult;
import org.springrain.weixin.base.mp.bean.material.WxMpMaterialVideoInfoResult;
import org.springrain.weixin.base.mp.util.http.MaterialDeleteRequestExecutor;
import org.springrain.weixin.base.mp.util.http.MaterialNewsInfoRequestExecutor;
import org.springrain.weixin.base.mp.util.http.MaterialUploadRequestExecutor;
import org.springrain.weixin.base.mp.util.http.MaterialVideoInfoRequestExecutor;
import org.springrain.weixin.base.mp.util.http.MaterialVoiceAndImageDownloadRequestExecutor;
import org.springrain.weixin.base.mp.util.http.MediaImgUploadRequestExecutor;
import org.springrain.weixin.base.mp.util.json.WxMpGsonBuilder;
import org.springrain.weixin.entity.WxMpConfig;

/**
 * Created by springrain on 2017/1/8.
 */


@Service("wxMpMaterialService")
public class WxMpMaterialServiceImpl implements IWxMpMaterialService {
  private static final String MEDIA_API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/media";
  private static final String MATERIAL_API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/material";
  
  @Resource
  private IWxMpService wxMpService;

  public WxMpMaterialServiceImpl() {
  }
  public WxMpMaterialServiceImpl(IWxMpService wxMpService) {
	  this.wxMpService=wxMpService;
  }

  @Override
  public WxMediaUploadResult mediaUpload(WxMpConfig wxmpconfig,String mediaType, String fileType, InputStream inputStream) throws WxErrorException {
    try {
      return this.mediaUpload(wxmpconfig,mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
    } catch (IOException e) {
      e.printStackTrace();
      throw new WxErrorException(WxError.newBuilder().setErrorMsg(e.getMessage()).build());
    }
  }

  @Override
  public WxMediaUploadResult mediaUpload(WxMpConfig wxmpconfig,String mediaType, File file) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/upload?type=" + mediaType;
    return this.wxMpService.execute(wxmpconfig,new MediaUploadRequestExecutor(), url, file);
  }

  @Override
  public File mediaDownload(WxMpConfig wxmpconfig,String media_id) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/get";
    return this.wxMpService.execute(wxmpconfig,
      new MediaDownloadRequestExecutor(new File(wxmpconfig.getTmpDirFile())),
      url,
      "media_id=" + media_id);
  }

  @Override
  public WxMediaImgUploadResult mediaImgUpload(WxMpConfig wxmpconfig,File file) throws WxErrorException {
    String url = MEDIA_API_URL_PREFIX + "/uploadimg";
    return this.wxMpService.execute(wxmpconfig,new MediaImgUploadRequestExecutor(), url, file);
  }

  @Override
  public WxMpMaterialUploadResult materialFileUpload(WxMpConfig wxmpconfig,String mediaType, WxMpMaterial material) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/add_material?type=" + mediaType;
    return this.wxMpService.execute(wxmpconfig,new MaterialUploadRequestExecutor(), url, material);
  }

  @Override
  public WxMpMaterialUploadResult materialNewsUpload(WxMpConfig wxmpconfig,WxMpMaterialNews news) throws WxErrorException {
    if (news == null || news.isEmpty()) {
      throw new IllegalArgumentException("news is empty!");
    }
    String url = MATERIAL_API_URL_PREFIX + "/add_news";
    String responseContent = this.wxMpService.post(wxmpconfig,url, news.toJson());
    return WxMpMaterialUploadResult.fromJson(responseContent);
  }

  @Override
  public InputStream materialImageOrVoiceDownload(WxMpConfig wxmpconfig,String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxMpService.execute(wxmpconfig,new MaterialVoiceAndImageDownloadRequestExecutor(new File(wxmpconfig.getTmpDirFile())), url, media_id);
  }

  @Override
  public WxMpMaterialVideoInfoResult materialVideoInfo(WxMpConfig wxmpconfig,String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxMpService.execute(wxmpconfig,new MaterialVideoInfoRequestExecutor(), url, media_id);
  }

  @Override
  public WxMpMaterialNews materialNewsInfo(WxMpConfig wxmpconfig,String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_material";
    return this.wxMpService.execute(wxmpconfig,new MaterialNewsInfoRequestExecutor(), url, media_id);
  }

  @Override
  public boolean materialNewsUpdate(WxMpConfig wxmpconfig,WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/update_news";
    String responseText = this.wxMpService.post(wxmpconfig,url, wxMpMaterialArticleUpdate.toJson());
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return true;
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public boolean materialDelete(WxMpConfig wxmpconfig,String media_id) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/del_material";
    return this.wxMpService.execute(wxmpconfig,new MaterialDeleteRequestExecutor(), url, media_id);
  }

  @Override
  public WxMpMaterialCountResult materialCount(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/get_materialcount";
    String responseText = this.wxMpService.get(wxmpconfig,url, null);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialCountResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialNewsBatchGetResult materialNewsBatchGet(WxMpConfig wxmpconfig,int offset, int count) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", WxConsts.MATERIAL_NEWS);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxMpService.post(wxmpconfig,url, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialNewsBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialFileBatchGetResult materialFileBatchGet(WxMpConfig wxmpconfig,String type, int offset, int count) throws WxErrorException {
    String url = MATERIAL_API_URL_PREFIX + "/batchget_material";
    Map<String, Object> params = new HashMap<>();
    params.put("type", type);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxMpService.post(wxmpconfig,url, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialFileBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

}
