package org.springrain.weixin.sdk.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import org.springrain.weixin.sdk.common.util.ToStringUtils;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.util.List;

/**
 * @author springrain
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
        return WxJsonBuilder.fromJson(json,
                WxMpKfSessionList.class);
    }

    public List<WxMpKfSession> getKfSessionList() {
        return this.kfSessionList;
    }

    public void setKfSessionList(List<WxMpKfSession> kfSessionList) {
        this.kfSessionList = kfSessionList;
    }
}
