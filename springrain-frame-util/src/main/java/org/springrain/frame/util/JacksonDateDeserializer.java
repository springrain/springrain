package org.springrain.frame.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

public class JacksonDateDeserializer extends DateDeserializers.DateDeserializer  {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p == null) {
            return super.deserialize(p, ctxt);
        }
        String text = p.getText();
        text = text.trim();
        if (StringUtils.isBlank(text)) {
            return super.deserialize(p, ctxt);
        }
        Date date=DateUtils.convertString2DateAutoFormat(text);
        if (date!=null){
            return date;
        }

        return super.deserialize(p, ctxt);

    }
}
