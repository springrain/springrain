package org.springrain.weixin.sdk.mp.util.json;

import com.google.gson.*;
import org.springrain.weixin.sdk.common.util.json.GsonHelper;
import org.springrain.weixin.sdk.mp.bean.WxMpCard;

import java.lang.reflect.Type;

/**
 * Created by springrain on 2017/1/8.
 *
 * @author springrain
 * @version 2017/1/8
 */
public class WxMpCardGsonAdapter implements JsonDeserializer<WxMpCard> {

    @Override
    public WxMpCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {
        WxMpCard card = new WxMpCard();
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        card.setCardId(GsonHelper.getString(jsonObject, "card_id"));
        card.setBeginTime(GsonHelper.getLong(jsonObject, "begin_time"));
        card.setEndTime(GsonHelper.getLong(jsonObject, "end_time"));

        return card;
    }

}
