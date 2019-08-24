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
import org.apache.commons.lang3.StringUtils;
import org.springrain.weixin.sdk.common.bean.result.WxMediaUploadResult;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author springrain
 */
public class WxMediaUploadResultAdapter extends JsonDeserializer<WxMediaUploadResult> {
    @Override
    public WxMediaUploadResult deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {


        WxMediaUploadResult uploadResult = new WxMediaUploadResult();
        HashMap map = jsonParser.readValueAs(HashMap.class);

        String type = (String) map.get("type");
        String media_id = (String) map.get("media_id");
        String thumb_media_id = (String) map.get("thumb_media_id");
        Long created_at = (Long) map.get("created_at");

        if (StringUtils.isNotBlank(type)) {
            uploadResult.setType(type);
        }
        if (StringUtils.isNotBlank(media_id)) {
            uploadResult.setMediaId(media_id);
        }
        if (StringUtils.isNotBlank(thumb_media_id)) {
            uploadResult.setThumbMediaId(thumb_media_id);
        }
        if (created_at != null) {
            uploadResult.setCreatedAt(created_at);
        }


        return uploadResult;
    }

/*
  public WxMediaUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMediaUploadResult uploadResult = new WxMediaUploadResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("type") != null && !uploadResultJsonObject.get("type").isJsonNull()) {
      uploadResult.setType(GsonHelper.getAsString(uploadResultJsonObject.get("type")));
    }
    if (uploadResultJsonObject.get("media_id") != null && !uploadResultJsonObject.get("media_id").isJsonNull()) {
      uploadResult.setMediaId(GsonHelper.getAsString(uploadResultJsonObject.get("media_id")));
    }
    if (uploadResultJsonObject.get("thumb_media_id") != null && !uploadResultJsonObject.get("thumb_media_id").isJsonNull()) {
      uploadResult.setThumbMediaId(GsonHelper.getAsString(uploadResultJsonObject.get("thumb_media_id")));
    }
    if (uploadResultJsonObject.get("created_at") != null && !uploadResultJsonObject.get("created_at").isJsonNull()) {
      uploadResult.setCreatedAt(GsonHelper.getAsPrimitiveLong(uploadResultJsonObject.get("created_at")));
    }
    return uploadResult;
  }

 */

}
