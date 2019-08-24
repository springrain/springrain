package org.springrain.weixin.sdk.mp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;
import org.springrain.weixin.sdk.common.service.WxConsts;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.api.IWxMpUserTagService;
import org.springrain.weixin.sdk.mp.bean.tag.WxTagListUser;
import org.springrain.weixin.sdk.mp.bean.tag.WxUserTag;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="http://git.oschina.net/chunanyong/springrain">springrain(springrain)</a>
 * Created by springrain on 2017/1/2.
 */

@Service("wxMpUserTagService")
public class WxMpUserTagServiceImpl implements IWxMpUserTagService {
    private static final String API_URL_PREFIX = WxConsts.mpapiurl + "/cgi-bin/tags";

    @Resource
    private IWxMpService wxMpService;

    public WxMpUserTagServiceImpl() {
    }

    public WxMpUserTagServiceImpl(IWxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @Override
    public WxUserTag tagCreate(IWxMpConfig wxmpconfig, String name) throws WxErrorException {
        String url = API_URL_PREFIX + "/create";
        JsonObject json = new JsonObject();
        JsonObject tagJson = new JsonObject();
        tagJson.addProperty("name", name);
        json.add("tag", tagJson);

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        return WxUserTag.fromJson(responseContent);
    }

    @Override
    public List<WxUserTag> tagGet(IWxMpConfig wxmpconfig) throws WxErrorException {
        String url = API_URL_PREFIX + "/get";

        String responseContent = wxMpService.get(wxmpconfig, url, null);
        return WxUserTag.listFromJson(responseContent);
    }

    @Override
    public Boolean tagUpdate(IWxMpConfig wxmpconfig, Long id, String name) throws WxErrorException {
        String url = API_URL_PREFIX + "/update";

        JsonObject json = new JsonObject();
        JsonObject tagJson = new JsonObject();
        tagJson.addProperty("id", id);
        tagJson.addProperty("name", name);
        json.add("tag", tagJson);

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        WxError wxError = WxError.fromJson(responseContent);
        if (wxError.getErrorCode() == 0) {
            return true;
        }

        throw new WxErrorException(wxError);
    }

    @Override
    public Boolean tagDelete(IWxMpConfig wxmpconfig, Long id) throws WxErrorException {
        String url = API_URL_PREFIX + "/delete";

        JsonObject json = new JsonObject();
        JsonObject tagJson = new JsonObject();
        tagJson.addProperty("id", id);
        json.add("tag", tagJson);

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        WxError wxError = WxError.fromJson(responseContent);
        if (wxError.getErrorCode() == 0) {
            return true;
        }

        throw new WxErrorException(wxError);
    }

    @Override
    public WxTagListUser tagListUser(IWxMpConfig wxmpconfig, Long tagId, String nextOpenid)
            throws WxErrorException {
        String url = WxConsts.mpapiurl + "/cgi-bin/user/tag/get";

        JsonObject json = new JsonObject();
        json.addProperty("tagid", tagId);
        json.addProperty("next_openid", StringUtils.trimToEmpty(nextOpenid));

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        return WxTagListUser.fromJson(responseContent);
    }

    @Override
    public boolean batchTagging(IWxMpConfig wxmpconfig, Long tagId, String[] openids)
            throws WxErrorException {
        String url = API_URL_PREFIX + "/members/batchtagging";

        JsonObject json = new JsonObject();
        json.addProperty("tagid", tagId);
        JsonArray openidArrayJson = new JsonArray();
        for (String openid : openids) {
            openidArrayJson.add(openid);
        }
        json.add("openid_list", openidArrayJson);

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        WxError wxError = WxError.fromJson(responseContent);
        if (wxError.getErrorCode() == 0) {
            return true;
        }

        throw new WxErrorException(wxError);
    }

    @Override
    public boolean batchUntagging(IWxMpConfig wxmpconfig, Long tagId, String[] openids)
            throws WxErrorException {
        String url = API_URL_PREFIX + "/members/batchuntagging";

        JsonObject json = new JsonObject();
        json.addProperty("tagid", tagId);
        JsonArray openidArrayJson = new JsonArray();
        for (String openid : openids) {
            openidArrayJson.add(openid);
        }
        json.add("openid_list", openidArrayJson);

        String responseContent = wxMpService.post(wxmpconfig, url, json.toString());
        WxError wxError = WxError.fromJson(responseContent);
        if (wxError.getErrorCode() == 0) {
            return true;
        }

        throw new WxErrorException(wxError);
    }

    @Override
    public List<Long> userTagList(IWxMpConfig wxmpconfig, String openid) throws WxErrorException {
        String url = API_URL_PREFIX + "/getidlist";

        //JsonObject json = new JsonObject();
        //json.addProperty("openid", openid);

        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("openid", openid);


        String responseContent = wxMpService.post(wxmpconfig, url, WxJsonBuilder.toJson(jsonMap));

        Map map = WxJsonBuilder.fromJson(responseContent, HashMap.class);
        List<Long> list = (List) map.get("tagid_list");


        return list;


/*
    return WxJsonBuilder.fromJson(
        new JsonParser().parse(responseContent).getAsJsonObject().get("tagid_list"),
        new TypeToken<List<Long>>() {
    }.getType());

 */


    }
}
