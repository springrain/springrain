/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package org.springrain.weixin.sdk.common.util.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.seata.common.util.StringUtils;
import org.springrain.weixin.sdk.common.bean.result.WxError;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author springrain
 */
public class WxErrorAdapter extends JsonDeserializer<WxError> {
    @Override
    public WxError deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        WxError wxError = new WxError();
        HashMap map = jsonParser.readValueAs(HashMap.class);


        String errmsg = (String) map.get("errmsg");
        if (StringUtils.isNotBlank(errmsg)) {
            wxError.setErrorMsg(errmsg);
        }

        Integer errcode = (Integer) map.get("errcode");

        if (errcode != null) {
            wxError.setErrorCode(errcode);
        }

        return wxError;
    }

/*
  public WxError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxError wxError = new WxError();
    JsonObject wxErrorJsonObject = json.getAsJsonObject();

    if (wxErrorJsonObject.get("errcode") != null && !wxErrorJsonObject.get("errcode").isJsonNull()) {
      wxError.setErrorCode(GsonHelper.getAsPrimitiveInt(wxErrorJsonObject.get("errcode")));
    }
    if (wxErrorJsonObject.get("errmsg") != null && !wxErrorJsonObject.get("errmsg").isJsonNull()) {
      wxError.setErrorMsg(GsonHelper.getAsString(wxErrorJsonObject.get("errmsg")));
    }
    wxError.setJson(json.toString());
    return wxError;
  }
*/
}
