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
import org.springrain.weixin.sdk.common.bean.WxAccessToken;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author springrain
 */
public class WxAccessTokenAdapter extends JsonDeserializer<WxAccessToken> {


    @Override
    public WxAccessToken deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        WxAccessToken token = new WxAccessToken();

        HashMap map = jsonParser.readValueAs(HashMap.class);


        String accessToken = (String) map.get("access_token");
        if (StringUtils.isNotBlank(accessToken)) {
            token.setAccessToken(accessToken);
        }

        Integer expiresIn = (Integer) map.get("expires_in");

        if (expiresIn != null) {
            token.setExpiresIn(expiresIn);
        }


        return token;
    }



  /*

  public WxAccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxAccessToken accessToken = new WxAccessToken();
    JsonObject accessTokenJsonObject = json.getAsJsonObject();

    if (accessTokenJsonObject.get("access_token") != null && !accessTokenJsonObject.get("access_token").isJsonNull()) {
      accessToken.setAccessToken(GsonHelper.getAsString(accessTokenJsonObject.get("access_token")));
    }
    if (accessTokenJsonObject.get("expires_in") != null && !accessTokenJsonObject.get("expires_in").isJsonNull()) {
      accessToken.setExpiresIn(GsonHelper.getAsPrimitiveInt(accessTokenJsonObject.get("expires_in")));
    }
    return accessToken;
  }

   */


}
