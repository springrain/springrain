package org.springrain.weixin.sdk.common.util.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
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
import java.util.ArrayList;
import java.util.List;

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





/*
         INSTANCE.registerTypeAdapter(WxCpMessage.class, new WxCpMessageGsonAdapter());
         INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
         INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
         INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
         INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());


    INSTANCE.registerTypeAdapter(WxMpOAuth2SessionKey.class, new WxMpOAuth2SessionKeyAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());


        INSTANCE.registerTypeAdapter(WxMpKefuMessage.class, new WxMpKefuMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassNews.class, new WxMpMassNewsGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassTagMessage.class, new WxMpMassTagMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassOpenIdsMessage.class, new WxMpMassOpenIdsMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUser.class, new WxMpUserGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUserList.class, new WxUserListGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassVideo.class, new WxMpMassVideoAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassSendResult.class, new WxMpMassSendResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassUploadResult.class, new WxMpMassUploadResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpQrCodeTicket.class, new WxQrCodeTicketAdapter());
        INSTANCE.registerTypeAdapter(WxMpTemplateMessage.class, new WxMpTemplateMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpSemanticQueryResult.class, new WxMpSemanticQueryResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpOAuth2AccessToken.class, new WxMpOAuth2AccessTokenAdapter());
        INSTANCE.registerTypeAdapter(WxDataCubeUserSummary.class, new WxMpUserSummaryGsonAdapter());
        INSTANCE.registerTypeAdapter(WxDataCubeUserCumulate.class, new WxMpUserCumulateGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialUploadResult.class, new WxMpMaterialUploadResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialVideoInfoResult.class, new WxMpMaterialVideoInfoResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassNews.WxMpMassNewsArticle.class, new WxMpMassNewsArticleGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialArticleUpdate.class, new WxMpMaterialArticleUpdateGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialCountResult.class, new WxMpMaterialCountResultAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNews.class, new WxMpMaterialNewsGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNews.WxMpMaterialNewsArticle.class, new WxMpMaterialNewsArticleGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.class, new WxMpMaterialNewsBatchGetGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class, new WxMpMaterialNewsBatchGetGsonItemAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.class, new WxMpMaterialFileBatchGetGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class, new WxMpMaterialFileBatchGetGsonItemAdapter());
        INSTANCE.registerTypeAdapter(WxMpCardResult.class, new WxMpCardResultGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpCard.class, new WxMpCardGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpMassPreviewMessage.class, new WxMpMassPreviewMessageGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMediaImgUploadResult.class, new WxMediaImgUploadResultGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpTemplateIndustry.class, new WxMpIndustryGsonAdapter());
        INSTANCE.registerTypeAdapter(WxMpUserBlacklistGetResult.class, new WxUserBlacklistGetResultGsonAdapter());

 */


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

    /**
     * 将List对象字符串,转化成List对象.
     *
     * @param content         字符串内容
     * @param collectionClass 集合类型,例如 ArrayList.class
     * @param clazz           对象类型 例如 User.class
     * @return
     */
    private static Object readValues(String content, Class collectionClass, Class clazz) {
        Object o = null;

        try {
            o = mapper.readValue(content, getCollectionType(collectionClass, clazz));
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return o;
    }

    /**
     * 将List对象字符串,转化成List对象.
     *
     * @param content 字符串内容
     * @param clazz   对象类型 例如 User.class
     * @return
     */
    public static <T> List<T> readValues(String content, Class<T> clazz) {
        return (List<T>) readValues(content, ArrayList.class, clazz);
    }


    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


}
