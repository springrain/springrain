package org.springrain.weixin.sdk.mp.api;

import com.jfinal.weixin.sdk.utils.HttpClientUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 会员卡接口
 *
 * @author L.cm
 */
public class MemberCardApi {
    private static String activateUrl = WxConsts.mpapiurl + "/card/membercard/activate?access_token=";
    private static String setActivateUserFormUrl = WxConsts.mpapiurl + "/card/membercard/activateuserform/set?access_token=";
    private static String getUserInfoUrl = WxConsts.mpapiurl + "/card/membercard/userinfo/get?access_token=";
    private static String getActivateTempInfoUrl = WxConsts.mpapiurl + "/card/membercard/activatetempinfo/get?access_token=";
    private static String updateUserUrl = WxConsts.mpapiurl + "/card/membercard/updateuser?access_token=";

    /**
     * 接口激活
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult activate(String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(activateUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 普通一键激活-设置开卡字段接口
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult setActivateUserForm(String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(setActivateUserFormUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }

    /**
     * 拉取会员信息接口
     *
     * @param cardId 卡券ID代表一类卡券。
     * @param code   卡券code。
     * @return {ApiResult}
     */
    public static ApiResult getUserInfo(String cardId, String code) {
        Map<String, Object> data = new HashMap<>();
        data.put("card_id", cardId).set("code", code);
        String jsonResult = HttpClientUtils.sendHttpPost(getUserInfoUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 跳转型一键激活-设置开卡字段接口-获取用户提交资料
     *
     * @param activaTeicket 用户填写信息的参数
     * @return {ApiResult}
     */
    public static ApiResult getActivateTempInfo(String activaTeicket) {
        Map<String, Object> data = new HashMap<>();
        data.put("activate_ticket", activaTeicket);
        String jsonResult = HttpClientUtils.sendHttpPost(getActivateTempInfoUrl + wxmpconfig.getAccessToken(), JsonUtils.writeValueAsString(data));
        return new ApiResult(jsonResult);
    }

    /**
     * 更新会员信息
     *
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult updateUser(String jsonStr) {
        String jsonResult = HttpClientUtils.sendHttpPost(updateUserUrl + wxmpconfig.getAccessToken(), jsonStr);
        return new ApiResult(jsonResult);
    }
}
