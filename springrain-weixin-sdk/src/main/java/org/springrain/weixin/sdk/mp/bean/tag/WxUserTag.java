package org.springrain.weixin.sdk.mp.bean.tag;

import org.springrain.weixin.sdk.common.util.ToStringUtils;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户标签对象
 *
 * @author <a href="http://git.oschina.net/chunanyong/springrain">springrain(springrain)</a>
 * Created by springrain on 2017/1/2.
 */
public class WxUserTag {
    /**
     * id	标签id，由微信分配
     */
    private Long id;

    /**
     * name	标签名，UTF8编码
     */
    private String name;

    /**
     * count 此标签下粉丝数
     */
    private Integer count;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static WxUserTag fromJson(String json) {

        Map map = WxJsonBuilder.fromJson(json, HashMap.class);

        Object tag = map.get("tag");

        String str = WxJsonBuilder.toJson(tag);
        return WxJsonBuilder.fromJson(str, WxUserTag.class);
/*
    return WxJsonBuilder.fromJson(
        new JsonParser().parse(json).getAsJsonObject().get("tag"),
        WxUserTag.class);

 */
    }

    public static List<WxUserTag> listFromJson(String json) {

        Map map = WxJsonBuilder.fromJson(json, HashMap.class);
        Object tag = map.get("tags");
        String str = WxJsonBuilder.toJson(tag);
        return WxJsonBuilder.readValues(str, WxUserTag.class);

/*
    return WxJsonBuilder.fromJson(
        new JsonParser().parse(json).getAsJsonObject().get("tags"),
        new TypeToken<List<WxUserTag>>(){}.getType());

 */
    }

    public String toJson() {
        return WxJsonBuilder.toJson(this);
    }

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }
}
