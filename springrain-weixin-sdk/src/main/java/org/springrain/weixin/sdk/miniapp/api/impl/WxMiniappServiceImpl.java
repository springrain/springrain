package org.springrain.weixin.sdk.miniapp.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.SecUtils;
import org.springrain.weixin.sdk.common.bean.WxAccessToken;
import org.springrain.weixin.sdk.common.bean.result.WxError;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfig;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfigService;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.util.http.RequestExecutor;
import org.springrain.weixin.sdk.common.util.http.SimpleGetRequestExecutor;
import org.springrain.weixin.sdk.common.util.http.SimplePostRequestExecutor;
import org.springrain.weixin.sdk.common.util.json.WxJsonBuilder;
import org.springrain.weixin.sdk.miniapp.api.IWxMiniappService;
import org.springrain.weixin.sdk.miniapp.bean.result.CodeInfo;
import org.springrain.weixin.sdk.miniapp.bean.result.EncryptedData;
import org.springrain.weixin.sdk.miniapp.bean.result.PhoneEncryptedData;
import org.springrain.weixin.sdk.miniapp.bean.result.WxMpOAuth2SessionKey;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caomei
 */
public class WxMiniappServiceImpl implements IWxMiniappService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 生产环境应该是spring注入
    private IWxMiniappConfigService wxMiniappConfigService;

    public WxMiniappServiceImpl() {

    }

    public WxMiniappServiceImpl(IWxMiniappConfigService wxMiniappConfigService) {
        this.wxMiniappConfigService = wxMiniappConfigService;
    }

    @Override
    public WxMpOAuth2SessionKey oauth2getSessionKey(IWxMiniappConfig wxminiappconfig, String code) throws WxErrorException {

        StringBuilder url = new StringBuilder();
        url.append(WxConsts.mpapiurl + "/sns/jscode2session?");
        url.append("appid=").append(wxminiappconfig.getAppId());
        url.append("&secret=").append(wxminiappconfig.getSecret());
        url.append("&js_code=").append(code);
        url.append("&grant_type=authorization_code");

        return getOAuth2SessionKey(wxminiappconfig, url);

    }

    private WxMpOAuth2SessionKey getOAuth2SessionKey(IWxMiniappConfig wxminiappconfig, StringBuilder url)
            throws WxErrorException {
        try {
            RequestExecutor<String, String> executor = new SimpleGetRequestExecutor();
            String responseText = executor.execute(wxminiappconfig, url.toString(), null);
            return WxMpOAuth2SessionKey.fromJson(responseText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAccessToken(IWxMiniappConfig wxminiappconfig) throws WxErrorException {
        return getAccessToken(wxminiappconfig, false);
    }

    @Override
    public String getAccessToken(IWxMiniappConfig wxminiappconfig, boolean forceRefresh) throws WxErrorException {

        if (forceRefresh) {
            wxMiniappConfigService.expireAccessToken(wxminiappconfig);
        }

        if (!wxminiappconfig.isAccessTokenExpired()) {
            return wxminiappconfig.getAccessToken();
        }

        // WxAccessToken accessToken =
        // wxMiniappConfigService.getCustomAPIAccessToken(wxminiappconfig);
        WxAccessToken accessToken = null;
        if (accessToken == null) {
            String url = WxConsts.mpapiurl + "/cgi-bin/token?grant_type=client_credential" + "&appid="
                    + wxminiappconfig.getAppId() + "&secret=" + wxminiappconfig.getSecret();
            HttpGet httpGet = new HttpGet(url);
            if (wxminiappconfig.getHttpProxyHost() != null) {
                RequestConfig config = RequestConfig.custom()
                        .setProxy(new HttpHost(wxminiappconfig.getHttpProxyHost(), wxminiappconfig.getHttpProxyPort())).build();
                httpGet.setConfig(config);
            }

            String resultContent = HttpClientUtils.sendHttpGet(httpGet);
            WxError error = WxError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            }
            // accessToken = WxAccessToken.fromJson(resultContent);
            accessToken = WxJsonBuilder.fromJson(resultContent, WxAccessToken.class);
        }

        if (accessToken == null) {
            return null;
        }

        wxminiappconfig.setAccessToken(accessToken.getAccessToken());
        wxminiappconfig.setAccessTokenExpiresTime(Long.valueOf(accessToken.getExpiresIn()));
        // wxMiniappConfigService.updateAccessToken(wxminiappconfig);

        return wxminiappconfig.getAccessToken();
    }

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
     */
    @Override
    public String get(IWxMiniappConfig wxminiappconfig, String url, String queryParam) throws WxErrorException {
        return execute(wxminiappconfig, new SimpleGetRequestExecutor(), url, queryParam);
    }

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
     */
    @Override
    public String post(IWxMiniappConfig wxminiappconfig, String url, String postData) throws WxErrorException {
        return execute(wxminiappconfig, new SimplePostRequestExecutor(), url, postData);
    }

    /**
     * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
     */
    /**
     * <pre>
     * Service没有实现某个API的时候，可以用这个，
     * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
     * 可以参考，{@link org.springrain.weixin.sdk.common.util.http.MediaUploadRequestExecutor}的实现方法
     * </pre>
     */
    @Override
    public <T, E> T execute(IWxMiniappConfig wxminiappconfig, RequestExecutor<T, E> executor, String uri, E data)
            throws WxErrorException {
        int retrySleepMillis = 1000;
        int maxRetryTimes = 5;
        int retryTimes = 0;

        do {
            try {
                T result = executeInternal(wxminiappconfig, executor, uri, data);
                logger.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", uri, data, result);
                return result;
            } catch (WxErrorException e) {
                logger.error(e.getMessage(), e);
                WxError error = e.getError();
                // -1 系统繁忙, 1000ms后重试
                if (error.getErrorCode() == -1) {
                    int sleepMillis = retrySleepMillis * (1 << retryTimes);
                    try {
                        logger.debug("微信系统繁忙，{}ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        throw new RuntimeException(e1);
                    }
                } else {
                    throw e;
                }
            }
        } while (++retryTimes < maxRetryTimes);

        throw new RuntimeException("微信服务端异常，超出重试次数");
    }

    protected synchronized <T, E> T executeInternal(IWxMiniappConfig wxminiappconfig, RequestExecutor<T, E> executor,
                                                    String uri, E data) throws WxErrorException {
        if (uri.indexOf("access_token=") != -1) {
            throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
        }
        String accessToken = getAccessToken(wxminiappconfig, false);

        String uriWithAccessToken = uri;
        uriWithAccessToken += uri.indexOf('?') == -1 ? "?access_token=" + accessToken : "&access_token=" + accessToken;

        try {
            return executor.execute(wxminiappconfig, uriWithAccessToken, data);
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
            WxError error = e.getError();
            /*
             * 发生以下情况时尝试刷新access_token 40001 获取access_token时AppSecret错误，或者access_token无效
             * 42001 access_token超时
             */
            if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001) {
                // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
                // wxminiappconfig.expireAccessToken();
                wxMiniappConfigService.expireAccessToken(wxminiappconfig);
                if (wxminiappconfig.autoRefreshToken()) {
                    return execute(wxminiappconfig, executor, uri, data);
                }
            }

            if (error.getErrorCode() != 0) {
                logger.error("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", uri, data, error);
                throw new WxErrorException(error);
            }
            return null;
        } catch (IOException e) {
            logger.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", uri, data, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getMiniappCode(String siteId, IWxMiniappConfig wxminiappconfig, CodeInfo codeInfo, String fileSrc, String fileName,
                               String fileType) throws Exception {
        /**
         * 1、先获取tocken 2、调用获取二维码 3、生成二维码图片 4、将图片保存到本地 5、返回图片的url
         */
        String accessToken = getAccessToken(wxminiappconfig);

        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String imgUrl = null;
        try {
            String url = WxConsts.mpapiurl + "/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", codeInfo.getScene());
            param.put("page", codeInfo.getPage());
            param.put("width", codeInfo.getWidth());
            param.put("auto_color", codeInfo.getAuto_color());
            if (codeInfo.getLine_color() == null) {
                Map<String, Object> line_color = new HashMap<>();
                line_color.put("r", 0);
                line_color.put("g", 0);
                line_color.put("b", 0);
                param.put("line_color", line_color);
            } else {
                param.put("line_color", codeInfo.getLine_color());
            }

            // 判断文件目录是否存在，不存在创建
            File folder = new File(fileSrc);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            imgUrl = fileSrc + "/" + fileName + fileType;
            File file = new File(imgUrl);
            // 判断文件是否存在，存在的话直接返回
            if (file.exists()) {
                System.out.println("存在，直接返回");
                return;
            }
            // 不存在，创建
            if (!file.exists()) {
                file.createNewFile();
            }

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class,
                    new Object[0]);

            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            imgUrl = null;
            logger.error("小程序二维码生成失败！", e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public EncryptedData getEncryptedDataInfo(String encryptedData, String sessionkey, String iv) throws Exception {
        String jsonEncryptedDataInfo = getJsonEncryptedDataInfo(encryptedData, sessionkey, iv);
        if (StringUtils.isBlank(jsonEncryptedDataInfo)) {
            return null;
        }
        EncryptedData dEncryptedData = JsonUtils.readValue(jsonEncryptedDataInfo, EncryptedData.class);
        return dEncryptedData;
    }

    @Override
    public PhoneEncryptedData getPhoneEncryptedDataInfo(String encryptedData, String sessionkey, String iv)
            throws Exception {
        String jsonEncryptedDataInfo = getJsonEncryptedDataInfo(encryptedData, sessionkey, iv);
        if (StringUtils.isBlank(jsonEncryptedDataInfo)) {
            return null;
        }
        PhoneEncryptedData dEncryptedData = JsonUtils.readValue(jsonEncryptedDataInfo, PhoneEncryptedData.class);
        return dEncryptedData;
    }

    public String getJsonEncryptedDataInfo(String encryptedData, String sessionkey, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = SecUtils.decoderByteByBase64(encryptedData);
        // 加密秘钥
        byte[] keyByte = SecUtils.decoderByteByBase64(sessionkey);
        // 偏移量
        byte[] ivByte = SecUtils.decoderByteByBase64(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
