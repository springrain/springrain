package org.springrain.weixin.sdk.mp.bean.kefu.result;

import java.util.List;

import org.springrain.weixin.sdk.common.util.ToStringUtils;

import com.google.gson.annotations.SerializedName;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

/**
 *
 * @author springrain
 *
 */
public class WxMpKfOnlineList {
  @SerializedName("kf_online_list")
  private List<WxMpKfInfo> kfOnlineList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxMpKfInfo> getKfOnlineList() {
    return this.kfOnlineList;
  }

  public void setKfOnlineList(List<WxMpKfInfo> kfOnlineList) {
    this.kfOnlineList = kfOnlineList;
  }

  public static WxMpKfOnlineList fromJson(String json) {
    return WxJsonBuilder.fromJson(json, WxMpKfOnlineList.class);
  }
}
