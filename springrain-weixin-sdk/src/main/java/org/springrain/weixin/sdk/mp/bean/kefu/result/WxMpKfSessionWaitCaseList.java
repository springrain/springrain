package org.springrain.weixin.sdk.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import org.springrain.weixin.sdk.common.util.ToStringUtils;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.util.List;

/**
 * @author springrain
 */
public class WxMpKfSessionWaitCaseList {
    /**
     * count 未接入会话数量
     */
    @SerializedName("count")
    private Long count;

    /**
     * waitcaselist 未接入会话列表，最多返回100条数据
     */
    @SerializedName("waitcaselist")
    private List<WxMpKfSession> kfSessionWaitCaseList;

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }

    public static WxMpKfSessionWaitCaseList fromJson(String json) {
        return WxJsonBuilder.fromJson(json,
                WxMpKfSessionWaitCaseList.class);
    }

    public List<WxMpKfSession> getKfSessionWaitCaseList() {
        return this.kfSessionWaitCaseList;
    }

    public void setKfSessionWaitCaseList(List<WxMpKfSession> kfSessionWaitCaseList) {
        this.kfSessionWaitCaseList = kfSessionWaitCaseList;
    }

}
