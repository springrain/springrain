package org.springrain.weixin.base.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import org.springrain.weixin.base.common.util.ToStringUtils;
import org.springrain.weixin.base.mp.util.json.WxMpGsonBuilder;

import java.util.List;
/**
 *
 * @author Binary Wang
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
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpKfList.class);
  }
}
