package org.springrain.weixin.sdk.mp.bean.result;

import org.springrain.weixin.sdk.common.util.ToStringUtils;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 微信用户信息
 * @author springrain
 *
 */
public class WxMpUser implements Serializable {

  private static final long serialVersionUID = 5788154322646488738L;
  private Boolean subscribe;
  private String openId;
  private String nickname;
  private String sex;
  private String language;
  private String city;
  private String province;
  private String country;
  private String headImgUrl;
  private Long subscribeTime;
  private String unionId;
  private Integer sexId;
  private String remark;
  private Integer groupId;
  private Integer[] tagIds;

  public Boolean getSubscribe() {
    return this.subscribe;
  }

  public void setSubscribe(Boolean subscribe) {
    this.subscribe = subscribe;
  }

  public String getOpenId() {
    return this.openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getHeadImgUrl() {
    return this.headImgUrl;
  }

  public void setHeadImgUrl(String headImgUrl) {
    this.headImgUrl = headImgUrl;
  }

  public Long getSubscribeTime() {
    return this.subscribeTime;
  }

  public void setSubscribeTime(Long subscribeTime) {
    this.subscribeTime = subscribeTime;
  }

  /**
   *只有在将公众号绑定到微信开放平台帐号后，才会出现该字段。
   */
  public String getUnionId() {
    return this.unionId;
  }

  public void setUnionId(String unionId) {
    this.unionId = unionId;
  }

  public Integer getSexId() {

    return this.sexId;
  }

  public void setSexId(Integer sexId) {
    this.sexId = sexId;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getGroupId() {
    return this.groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public Integer[] getTagIds() {
    return this.tagIds;
  }

  public void setTagIds(Integer[] tagIds) {
    this.tagIds = tagIds;
  }


  public static WxMpUser fromJson(String json) {
    return WxJsonBuilder.fromJson(json, WxMpUser.class);
  }

  public static List<WxMpUser> fromJsonList(String json) {

    HashMap map= WxJsonBuilder.fromJson(json, HashMap.class);

    List list=(List)map.get("list");

    String content=WxJsonBuilder.toJson(list);

    return WxJsonBuilder.readValues(content, WxMpUser.class);

    /*
    Type collectionType = new TypeToken<List<WxMpUser>>() {
    }.getType();
    Gson gson = WxJsonBuilder;
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    return gson.fromJson(jsonObject.get("user_info_list"), collectionType);
     */


  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
