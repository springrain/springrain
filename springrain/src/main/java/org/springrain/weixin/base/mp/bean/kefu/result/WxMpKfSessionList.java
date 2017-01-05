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
public class WxMpKfSessionList {
  /**
   * 会话列表
   */
  @SerializedName("sessionlist")
  private List<WxMpKfSession> kfSessionList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpKfSessionList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json,
        WxMpKfSessionList.class);
  }

  public List<WxMpKfSession> getKfSessionList() {
    return this.kfSessionList;
  }

  public void setKfSessionList(List<WxMpKfSession> kfSessionList) {
    this.kfSessionList = kfSessionList;
  }
}
