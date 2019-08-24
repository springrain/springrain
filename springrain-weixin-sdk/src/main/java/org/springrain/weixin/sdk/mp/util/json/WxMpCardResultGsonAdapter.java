package org.springrain.weixin.sdk.mp.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springrain.weixin.sdk.common.util.json.GsonHelper;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;
import org.springrain.weixin.sdk.mp.bean.WxMpCard;
import org.springrain.weixin.sdk.mp.bean.result.WxMpCardResult;

import java.lang.reflect.Type;

/**
 * Created by springrain on 2017/1/8.
 *
 * @author springrain
 * @version 2017/1/8
 */
public class WxMpCardResultGsonAdapter implements JsonDeserializer<WxMpCardResult> {
  @Override
  public WxMpCardResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpCardResult cardResult = new WxMpCardResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    cardResult.setOpenId(GsonHelper.getString(jsonObject, "openid"));
    cardResult.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
    cardResult.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
    cardResult.setCanConsume(GsonHelper.getBoolean(jsonObject, "can_consume"));
    cardResult.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));


    WxMpCard card = WxJsonBuilder.fromJson(jsonObject.get("card"),
        new TypeToken<WxMpCard>() {
        }.getType());

    cardResult.setCard(card);

    return cardResult;
  }
}
