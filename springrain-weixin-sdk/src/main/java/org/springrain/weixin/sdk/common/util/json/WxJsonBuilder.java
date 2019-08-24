package org.springrain.weixin.sdk.common.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.bean.result.WxMediaUploadResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class WxJsonBuilder {

    // public static final GsonBuilder INSTANCE = new GsonBuilder();

    private WxJsonBuilder() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    private static final Logger logger = LoggerFactory.getLogger(WxJsonBuilder.class);

    private final static ObjectMapper mapper = new ObjectMapper();


    static {

        SimpleModule module = new SimpleModule();
        module.addDeserializer(WxAccessToken.class, new WxAccessTokenAdapter());
        module.addDeserializer(WxError.class, new WxErrorAdapter());
        //module.addDeserializer(WxMenu.class, new WxMenuGsonAdapter());
        module.addDeserializer(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());


        mapper.registerModule(module);

        //INSTANCE.disableHtmlEscaping();
        //INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
        // INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
        // INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
        // INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
    }

    //public static Gson create() {
    // return INSTANCE.create();
    //}

    public static <T> T fromJson(String content, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(content, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }

    public static <T> T fromJson(Reader reader, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(reader, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }


    public static <T> T fromJson(InputStream stream, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(stream, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return t;
    }

    public static String toJson(Object object) {
        String str = null;
        try {
            str = mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return str;
    }


}
