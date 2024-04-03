package org.springrain.weixin.util;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.SecUtils;
import org.springrain.weixin.constant.WXCommonConst;
import org.springrain.weixin.dto.PayRequestParamDTO;
import org.springrain.weixin.exception.WXException;
import org.springrain.weixin.sdk.common.wxconfig.IWxPayConfig;
import org.springrain.weixin.service.IWxPayConfigService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.*;
import java.util.Map;
import java.util.UUID;

/**
 * @descriptions: 支付工具类
 * @author: xiaohua
 * @date: 2024/3/29 14:05
 * @version: 1.0
 */
@Component("payUtil")
public class PayUtil {
    public Logger logger = LoggerFactory.getLogger(getClass());

    public static final String WX_JSAPI_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    public static final String WX_APP_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/app";
    public static final String WX_NATIVE_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/native";
    public static final String WX_FIND_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}?mchid={mchid}";
    public static final String WX_CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/{out_trade_no}/close";

    private static final String CHARSET = "utf-8";
    private static final String ALGORITHMS = "RSA";
    private static final String SIGNALGORITHMS = "SHA256withRSA";
    private static final String X509 = "X509";
    @Resource
    private IWxPayConfigService wxPayConfigService;

    private static PrivateKey H5PrivateKey;
    private static PrivateKey AppPrivateKey;

    private static PrivateKey getPrivateKey(String site){
        if(WXCommonConst.APP_SITE.equals(site)){
            return AppPrivateKey;
        }else if(WXCommonConst.H5_SITE.equals(site)){
            return H5PrivateKey;
        }
        return H5PrivateKey;
    }

    private PayUtilInItRes init(String site) throws GeneralSecurityException, IOException, HttpCodeException, NotFoundException {
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(WXCommonConst.siteVerity(site));
        if(wxPayConfig==null){
            throw new WXException("未找到微信支付配置! wxPayConfig is null!");
        }
        String certPath = wxPayConfig.getCertificateFile()+ File.separator+"apiclient_cert.pem";

        //证书序列号
        X509Certificate certificate = this.getCertificate(certPath);
        String mchSerialNo = certificate.getSerialNumber().toString(16);
        String appId = wxPayConfig.getAppId();
        String mchId = wxPayConfig.getMchId();
        String apiV3Key = wxPayConfig.getApiV3Key();
        String notifyUrl = wxPayConfig.getNotifyUrl();

        PrivateKey privateKey = getPrivateKey(site);
        /*verifier = new ScheduledUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),
                apiV3Key.getBytes(StandardCharsets.UTF_8));*/
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
                certificatesManager.putMerchant(mchId, new WechatPay2Credentials(mchId,
                        new PrivateKeySigner(mchSerialNo, privateKey)), apiV3Key.getBytes(StandardCharsets.UTF_8));
        Verifier verifier = certificatesManager.getVerifier(mchId);
        CloseableHttpClient wxChatHttpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, privateKey)
                .withValidator(new WechatPay2Validator(verifier))
                .build();

        PayUtilInItRes r = new PayUtilInItRes();
        r.setAppId(appId);
        r.setMchId(mchId);
        r.setNotifyUrl(notifyUrl);
        r.setWxChatHttpClient(wxChatHttpClient);
        return r;
    }
    // 如果你想启动的时候就初始化微信支付配置,可以解开注释
    // @PostConstruct
    public void initPrivateKey(){
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(WXCommonConst.H5_SITE);
        if(wxPayConfig==null){
            throw new WXException("未找到微信支付配置! wxPayConfig is null!");
        }
        if(H5PrivateKey==null){
            String privateKeyPath = wxPayConfig.getCertificateFile() + File.separator + "apiclient_key.pem";
            String privateKeyStr = PathUtil.readPath(privateKeyPath);
            H5PrivateKey= PemUtil.loadPrivateKey(privateKeyStr);
        }
        try {
            IWxPayConfig appWxPayConfig = wxPayConfigService.findWxPayConfigById(WXCommonConst.H5_SITE);
            if(AppPrivateKey==null){
                String privateKeyPath = appWxPayConfig.getCertificateFile() + File.separator + "apiclient_key.pem";
                String privateKeyStr = PathUtil.readPath(privateKeyPath);
                AppPrivateKey= PemUtil.loadPrivateKey(privateKeyStr);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
    /**
     * 微信支付下单 jsapi
     * @param amountTotal 金额 (分)
     * @param description 商品描述
     * @param openid 用戶openid
     * @param orderId 订单id
     * @return 预支付id
     * @throws Exception 异常，保证每次请求的商品描述，金额一致，并且该订单未关闭，即可同一个为订单号重复生成预支付id，反之会提示订单号重复
     */
    public String placeAnOrderByJsApi(String amountTotal,String description,String openid,String orderId,String site,String attach) throws Exception {
        PayUtilInItRes init = init(site);

        //请求URL
        HttpPost httpPost = new HttpPost(WX_JSAPI_URL);
        // 请求body参数
        String reqdata = "{"
                + "\"amount\": {"
                    + "\"total\": "+amountTotal+","
                    + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \""+init.getMchId()+"\","
                + "\"description\": \""+description+"\","
                + "\"notify_url\": \""+init.getNotifyUrl()+"\","
                + "\"payer\": {"
                    + "\"openid\": \""+openid+"\"" + "},"
                + "\"out_trade_no\": \""+orderId+"\","
                + "\"attach\": \""+attach+"\","
                + "\"appid\": \""+init.getAppId()+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        CloseableHttpClient wxChatHttpClient = init.getWxChatHttpClient();
        try (CloseableHttpResponse response = wxChatHttpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                return map.get("prepay_id").toString();
            } else {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                logger.error(JsonUtils.writeValueAsString(map));
                throw WXException.ORDER_ERR;
            }
        }finally {
            wxChatHttpClient.close();
        }
    }

    /**
     * 微信支付下单 App
     * @param amountTotal 金额 (分)
     * @param description 商品描述
     * @param orderId 订单id
     * @return 预支付id
     */
    public String placeAnOrderByApp(String amountTotal,String description,String orderId,String site,String attach) throws Exception {
        PayUtilInItRes init = init(site);

        //请求URL
        HttpPost httpPost = new HttpPost(WX_APP_URL);
        // 请求body参数
        String reqdata = "{"
                + "\"amount\": {"
                    + "\"total\": "+amountTotal+","
                    + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \""+init.getMchId()+"\","
                + "\"description\": \""+description+"\","
                + "\"notify_url\": \""+init.getNotifyUrl()+"\","
                + "\"out_trade_no\": \""+orderId+"\","
                +"\"attach\":\""+attach+"\","
                + "\"appid\": \""+init.getAppId()+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        CloseableHttpClient wxChatHttpClient = init.getWxChatHttpClient();
        try (CloseableHttpResponse response = wxChatHttpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                return map.get("prepay_id").toString();
            } else {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                logger.error(JsonUtils.writeValueAsString(map));
                throw WXException.ORDER_ERR;
            }
        }finally {
            wxChatHttpClient.close();
        }
    }

    /**
     * 获取微信支付二维码
     * @param productName 商品名称
     * @param sysOrderId 商户订单号
     * @param totalFee 支付金额
     * @param attach 附加数据
     * @return 二维码链接
     * @throws Exception 异常
     */
    public String getWeChatPayQRCode(String productName,String sysOrderId,String totalFee,String attach) throws Exception {
        int attachLength = attach.getBytes(StandardCharsets.UTF_8).length;
        if(attachLength>128){
            logger.error("附加数据大小超过128个字节！");
            throw WXException.OPERATION_FAIL;
        }
        PayUtilInItRes init = init(WXCommonConst.H5_SITE);
        //生成微信支付订单
        //请求URL
        HttpPost httpPost = new HttpPost(WX_NATIVE_URL);
        // 请求body参数
        String reqdata = "{"
                + "\"amount\": {"
                + "\"total\": "+totalFee+","
                + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \""+init.getMchId()+"\","
                + "\"description\": \""+productName+"\","
                + "\"notify_url\": \""+init.getNotifyUrl()+"\","
                + "\"attach\": \""+attach+"\","
                + "\"out_trade_no\": \""+sysOrderId+"\","
                + "\"appid\": \""+init.getAppId()+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        CloseableHttpClient wxChatHttpClient = init.getWxChatHttpClient();
        CloseableHttpResponse response = wxChatHttpClient.execute(httpPost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                return map.get("code_url").toString();
            } else {
                Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
                logger.error(JsonUtils.writeValueAsString(map));
                throw WXException.ORDER_ERR;
            }
        } finally {
            response.close();
            wxChatHttpClient.close();
        }
    }

    /**
     * 请求微信,关闭订单
     * @param orderId 订单id
     * @return 是否关闭成功
     * 官方文档描述
     * 状态码	错误码	描述	解决方案
     * 204||400 订单关闭成功
     * 202	USERPAYING	用户支付中，需要输入密码	等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作
     * 403	TRADE_ERROR	交易错误	因业务原因交易失败，请查看接口返回的详细信息
     * 500	SYSTEMERROR	系统错误	系统异常，请用相同参数重新调用
     * 401	SIGN_ERROR	签名错误	请检查签名参数和方法是否都符合签名算法要求
     * 403	RULELIMIT	业务规则限制	因业务规则限制请求频率，请查看接口返回的详细信息
     * 400	PARAM_ERROR	参数错误	请根据接口返回的详细信息检查请求参数
     * 403	OUT_TRADE_NO_USED	商户订单号重复	请核实商户订单号是否重复提交
     * 404	ORDERNOTEXIST	订单不存在	请检查订单是否发起过交易
     * 400	ORDER_CLOSED	订单已关闭	当前订单已关闭，请重新下单
     * 500	OPENID_MISMATCH	openid和appid不匹配	请确认openid和appid是否匹配
     * 403	NOTENOUGH	余额不足	用户账号余额不足，请用户充值或更换支付卡后再支付
     * 403	NOAUTH	商户无权限	请商户前往申请此接口相关权限
     * 400	MCH_NOT_EXISTS	商户号不存在	请检查商户号是否正确
     * 500	INVALID_TRANSACTIONID	订单号非法	请检查微信支付订单号是否正确
     * 400	INVALID_REQUEST	无效请求	请根据接口返回的详细信息检查
     * 429	FREQUENCY_LIMITED	频率超限	请降低请求接口频率
     * 500	BANKERROR	银行系统异常	银行系统异常，请用相同参数重新调用
     * 400	APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	请确认appid和mch_id是否匹配
     * 403	ACCOUNTERROR	账号异常	用户账号异常，无需更多操作
     */
    public Boolean closeOrder(String orderId) throws Exception{
        PayUtilInItRes init = init(WXCommonConst.H5_SITE);
        //请求URL
        HttpPost httpPost = new HttpPost(WX_CLOSE_ORDER_URL.replace("{out_trade_no}",orderId));
        // 请求body参数
        String reqdata = "{\"mchid\": \""+init.getMchId()+"\"}";
        StringEntity entity = new StringEntity(reqdata,"utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        CloseableHttpClient wxChatHttpClient = init.getWxChatHttpClient();
        try (CloseableHttpResponse response = wxChatHttpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==204){
                return true;
            }
            Map map = JsonUtils.readValue(response.getEntity().getContent(), Map.class);
            String code = map.get("code").toString();
            if("ORDER_CLOSED".equals(code) || "ORDERNOTEXIST".equals(code)){
                return true;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            wxChatHttpClient.close();
        }
        return false;
    }
    /**
     * 调起微信收银台的js请求参数
     * @param prepay_id 预支付id
     * @return 源数据和签名后的数据
     * @throws Exception 异常
     */
    public PayRequestParamDTO getPayRequestParam(String prepay_id, String site) throws Exception {
        IWxPayConfig wxPayConfig = wxPayConfigService.findWxPayConfigById(WXCommonConst.siteVerity(site));
        if(wxPayConfig==null){
            logger.error("未找到微信支付配置! wxPayConfig is null!");
            return null;
        }
        PayRequestParamDTO dto = new PayRequestParamDTO();
        dto.setAppId(wxPayConfig.getAppId());
        dto.setPartenrid(wxPayConfig.getMchId());
        dto.setPrepayid(prepay_id);
        dto.setTimeStamp(System.currentTimeMillis()+"");
        dto.setNonceStr(UUID.randomUUID().toString().replace("-",""));
        dto.setSignType(ALGORITHMS);
        if (WXCommonConst.APP_SITE.equals(site)){
            dto.setPackageStr(prepay_id);
        }else {
            dto.setPackageStr("prepay_id="+prepay_id);
        }

        String builder = dto.getAppId() + "\n" +
                dto.getTimeStamp() + "\n" +
                dto.getNonceStr() + "\n" +
                dto.getPackageStr() + "\n";

        String signStr = this.sign(builder);
        dto.setPaySign(signStr);
        return dto;
    }

    /**
     * 查询订单
     * @param orderId 订单id
     * @return
     */
    public Map<String,String> finOrder(String orderId) throws Exception {
        PayUtilInItRes init = init(WXCommonConst.H5_SITE);
        String url = WX_FIND_ORDER_URL.replace("{out_trade_no}",orderId).replace("{mchid}",init.getMchId());
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept", "application/json");
        CloseableHttpClient wxChatHttpClient = init.getWxChatHttpClient();
        try (CloseableHttpResponse response = wxChatHttpClient.execute(httpGet)){
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return JsonUtils.readValue(response.getEntity().getContent(), Map.class);
            } else {
                throw WXException.ORDER_ERR;
            }
        } finally {
            wxChatHttpClient.close();
        }
    }

    /**
     * 签名
     * 发送方私钥签名
     */
    private String sign(String encryptText) throws Exception {
        Signature signature = Signature.getInstance(SIGNALGORITHMS);
        if (H5PrivateKey != null) {
            signature.initSign(H5PrivateKey);
            signature.update(encryptText.getBytes(CHARSET));
            byte[] bytes = signature.sign();
            //return new String(Base64.encode(bytes));
            return SecUtils.encoderByBase64(bytes);
        }
        return null;
    }

    private X509Certificate getCertificate(String certPath){
        try {
            InputStream is = new FileInputStream(certPath);
            CertificateFactory cf = CertificateFactory.getInstance(X509);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(is);
            cert.checkValidity();
            return cert;
        } catch (CertificateNotYetValidException e) {
            throw new RuntimeException("证书尚未生效!");
        } catch (CertificateExpiredException e) {
            throw new RuntimeException("证书已过期!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件不存在!");
        } catch (CertificateException e) {
            throw new RuntimeException("无效的证书!");
        }
    }
}

//规避单例bean公用成员变量
class PayUtilInItRes{
    private CloseableHttpClient wxChatHttpClient;
    private String appId;
    private String mchId;
    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public CloseableHttpClient getWxChatHttpClient() {
        return wxChatHttpClient;
    }

    public void setWxChatHttpClient(CloseableHttpClient wxChatHttpClient) {
        this.wxChatHttpClient = wxChatHttpClient;
    }
}
