package org.springrain.frame.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParseException;
import tools.jackson.core.JacksonException;
import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.io.InputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Json工具类
 *
 * @author caomei
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    private final static JsonFactory jsonFactory = JsonFactory.builder().characterEscapes(new HTMLCharacterEscapes()).build();

    private final static JsonMapper jsonMapper = JsonMapper.builder(jsonFactory)
            // 1. 序列化包含策略 (修正了方法名和参数)
            //.changeDefaultPropertyInclusion((UnaryOperator<JsonInclude.Value>) JsonInclude.Value.construct(JsonInclude.Include.NON_NULL, JsonInclude.Include.NON_NULL))

            // 2. 反序列化配置 (确保 DeserializationFeature 导包正确)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

            // 3. 序列化配置 (确保 SerializationFeature 导包正确)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)


            .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)

            // 4. JSON 解析特性 (原 ALLOW_UNQUOTED_FIELD_NAMES 移到了 JsonReadFeature)
            .enable(JsonReadFeature.ALLOW_UNQUOTED_PROPERTY_NAMES)
            .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES)

            // 5. 日期格式
            .defaultDateFormat(new SimpleDateFormat(DateUtils.DATETIME_FORMAT))


            // 6. 工厂配置 (字符转义)
            // .jsonFactory(factoryBuilder -> {factoryBuilder.characterEscapes(new HTMLCharacterEscapes());})

            // 7. 注册模块
            .addModule(new SimpleModule().addDeserializer(Date.class, new JacksonDateDeserializer()))

            .build();


    public static JsonMapper getJsonMapper(){
        return jsonMapper;
    }

    /**
     * 将对象转转化成Json字符串
     *
     * @param o
     * @return
     */
    public static String writeValueAsString(Object o) {
        String str = null;
        try {
            str = jsonMapper.writeValueAsString(o);
        } catch (JacksonException e) {
            logger.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * 将对象字符串(不是List格式),转化成对象.
     *
     * @param content
     * @param clazz
     * @return
     */

    public static <T> T readValue(String content, Class<T> clazz) {
        T t = null;
        try {
            t = jsonMapper.readValue(content, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }


    /**
     * 将对象reader(不是List格式),转化成对象.
     *
     * @param reader
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readValue(Reader reader, Class<T> clazz) {
        T t = null;
        try {
            t = jsonMapper.readValue(reader, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * 将对象InputStream(不是List格式),转化成对象.
     *
     * @param stream
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readValue(InputStream stream, Class<T> clazz) {
        T t = null;
        try {
            t = jsonMapper.readValue(stream, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * 将List对象字符串,转化成List对象.
     *
     * @param content 字符串内容
     * @param clazz   对象类型 例如 User.class
     * @return
     */
    public static <T> List<T> readValueList(String content, Class<T> clazz) {
        return (List<T>) readValueList(content, ArrayList.class, clazz);
    }

    /**
     * 将List对象字符串,转化成List对象.
     *
     * @param content         字符串内容
     * @param collectionClass 集合类型,例如 ArrayList.class
     * @param clazz           对象类型 例如 User.class
     * @return
     */
    private static Object readValueList(String content, Class collectionClass, Class clazz) {
        Object o = null;

        try {
            o = jsonMapper.readValue(content, getCollectionType(collectionClass, clazz));
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        }

        return o;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return jsonMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
