package org.springrain.weixin.sdk.mp.util.json;

import com.google.gson.*;
import org.springrain.weixin.sdk.common.util.json.GsonHelper;
import org.springrain.weixin.sdk.mp.bean.template.WxMpTemplateIndustry;

import java.lang.reflect.Type;

/**
 * @author springrain
 */
public class WxMpIndustryGsonAdapter
        implements JsonSerializer<WxMpTemplateIndustry>, JsonDeserializer<WxMpTemplateIndustry> {
    @Override
    public JsonElement serialize(WxMpTemplateIndustry wxMpIndustry, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject json = new JsonObject();
        json.addProperty("industry_id1", wxMpIndustry.getPrimaryIndustry().getId());
        json.addProperty("industry_id2", wxMpIndustry.getSecondIndustry().getId());
        return json;
    }

    @Override
    public WxMpTemplateIndustry deserialize(JsonElement jsonElement, Type type,
                                            JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        WxMpTemplateIndustry wxMpIndustry = new WxMpTemplateIndustry();
        JsonObject primaryIndustry = jsonElement.getAsJsonObject()
                .get("primary_industry").getAsJsonObject();
        wxMpIndustry.setPrimaryIndustry(convertFromJson(primaryIndustry));
        JsonObject secondaryIndustry = jsonElement.getAsJsonObject()
                .get("secondary_industry").getAsJsonObject();
        wxMpIndustry.setSecondIndustry(convertFromJson(secondaryIndustry));
        return wxMpIndustry;
    }

    private static WxMpTemplateIndustry.Industry convertFromJson(JsonObject json) {
        WxMpTemplateIndustry.Industry industry = new WxMpTemplateIndustry.Industry();
        industry.setFirstClass(GsonHelper.getString(json, "first_class"));
        industry.setSecondClass(GsonHelper.getString(json, "second_class"));
        return industry;
    }
}
