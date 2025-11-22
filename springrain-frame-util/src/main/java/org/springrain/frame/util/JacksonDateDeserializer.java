package org.springrain.frame.util;


import org.apache.commons.lang3.StringUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.jdk.JavaUtilDateDeserializer;

import java.util.Date;

/**
 * 全局处理jackson解析日期字符串,不需要在字段上加上日期格式的注解了.
 * 自动分析字符串格式,转化成Date对象
 */
public class JacksonDateDeserializer extends JavaUtilDateDeserializer {

    protected JacksonDateDeserializer() {
        super();
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext context) throws JacksonException {
        if (p == null) {
            return super.deserialize(p, context);
        }
        String text = p.getString().trim();
        if (StringUtils.isBlank(text)) {
            return super.deserialize(p, context);
        }
        Date date = DateUtils.convertString2Date(text);
        if (date != null) {
            return date;
        }

        return super.deserialize(p, context);
    }
}
