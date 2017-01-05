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
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpKfOnlineList.class);
  }
}
