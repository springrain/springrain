package org.springrain.weixin.sdk.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import org.springrain.weixin.sdk.common.util.ToStringUtils;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.util.List;
/**
 *
 * @author springrain
 *
 */
public class WxMpKfList {
  @SerializedName("kf_list")
  private List<WxMpKfInfo> kfList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxMpKfInfo> getKfList() {
    return this.kfList;
  }

  public void setKfList(List<WxMpKfInfo> kfList) {
    this.kfList = kfList;
  }

  public static WxMpKfList fromJson(String json) {
    return WxJsonBuilder.fromJson(json, WxMpKfList.class);
  }
}
