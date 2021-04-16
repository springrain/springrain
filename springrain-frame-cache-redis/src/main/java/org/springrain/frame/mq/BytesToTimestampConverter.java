package org.springrain.frame.mq;


import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;

/**
 * 增加自定义的 BytesToTimestampConverter 类型转换器.
 * spring jdbc 把 datetime 类型解析成了 java.sql.timestamp,spring-data-redis并没用提供BytesToTimestampConverter,造成无法转换类型
 * 使用 ObjectHashMapper 构造函数 注册自定义的转换器
 */
//@ReadingConverter
class BytesToTimestampConverter implements Converter<byte[], Timestamp> {
    @Override
    public Timestamp convert(byte[] bytes) {
        String value = new String(bytes);
        return new Timestamp(Long.parseLong(value));
    }

}
