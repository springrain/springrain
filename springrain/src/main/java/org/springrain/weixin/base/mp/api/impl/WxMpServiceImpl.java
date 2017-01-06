package org.springrain.weixin.base.mp.api.impl;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.weixin.base.common.bean.WxAccessToken;
import org.springrain.weixin.base.common.bean.WxJsapiSignature;
import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.common.util.RandomUtils;
import org.springrain.weixin.base.common.util.crypto.SHA1;
import org.springrain.weixin.base.common.util.http.ApacheHttpClientBuilder;
import org.springrain.weixin.base.common.util.http.DefaultApacheHttpClientBuilder;
import org.springrain.weixin.base.common.util.http.RequestExecutor;
import org.springrain.weixin.base.common.util.http.SimpleGetRequestExecutor;
import org.springrain.weixin.base.common.util.http.SimplePostRequestExecutor;
import org.springrain.weixin.base.common.util.http.URIUtil;
import org.springrain.weixin.base.mp.api.WxMpCardService;
import org.springrain.weixin.base.mp.api.WxMpConfigStorage;
import org.springrain.weixin.base.mp.api.WxMpDataCubeService;
import org.springrain.weixin.base.mp.api.WxMpKefuService;
import org.springrain.weixin.base.mp.api.WxMpMaterialService;
import org.springrain.weixin.base.mp.api.WxMpMenuService;
import org.springrain.weixin.base.mp.api.WxMpPayService;
import org.springrain.weixin.base.mp.api.WxMpQrcodeService;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.api.WxMpStoreService;
import org.springrain.weixin.base.mp.api.WxMpTemplateMsgService;
import org.springrain.weixin.base.mp.api.WxMpUserBlacklistService;
import org.springrain.weixin.base.mp.api.WxMpUserService;
import org.springrain.weixin.base.mp.api.WxMpUserTagService;
import org.springrain.weixin.base.mp.bean.WxMpMassNews;
import org.springrain.weixin.base.mp.bean.WxMpMassOpenIdsMessage;
import org.springrain.weixin.base.mp.bean.WxMpMassPreviewMessage;
import org.springrain.weixin.base.mp.bean.WxMpMassTagMessage;
import org.springrain.weixin.base.mp.bean.WxMpMassVideo;
import org.springrain.weixin.base.mp.bean.WxMpSemanticQuery;
import org.springrain.weixin.base.mp.bean.result.WxMpMassSendResult;
import org.springrain.weixin.base.mp.bean.result.WxMpMassUploadResult;
import org.springrain.weixin.base.mp.bean.result.WxMpOAuth2AccessToken;
import org.springrain.weixin.base.mp.bean.result.WxMpSemanticQueryResult;
import org.springrain.weixin.base.mp.bean.result.WxMpUser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WxMpServiceImpl implements WxMpService {

  private static final JsonParser JSON_PARSER = new JsonParser();

  protected final Logger log = LoggerFactory.getLogger(this.getClass());

  private WxMpConfigStorage configStorage;

  private WxMpKefuService kefuService = new WxMpKefuServiceImpl(this);

  private WxMpMaterialService materialService = new WxMpMaterialServiceImpl(this);

  private WxMpMenuService menuService = new WxMpMenuServiceImpl(this);

  private WxMpUserService userService = new WxMpUserServiceImpl(this);

  private WxMpUserTagService tagService = new WxMpUserTagServiceImpl(this);

  private WxMpQrcodeService qrCodeService = new WxMpQrcodeServiceImpl(this);

  private WxMpCardService cardService = new WxMpCardServiceImpl(this);

  private WxMpPayService payService = new WxMpPayServiceImpl(this);

  private WxMpStoreService storeService = new WxMpStoreServiceImpl(this);

  private WxMpDataCubeService dataCubeService = new WxMpDataCubeServiceImpl(this);

  private WxMpUserBlacklistService blackListService = new WxMpUserBlacklistServiceImpl(this);

  private WxMpTemplateMsgService templateMsgService = new WxMpTemplateMsgServiceImpl(this);

  private CloseableHttpClient httpClient;

  private HttpHost httpProxy;

  private int retrySleepMillis = 1000;

  private int maxRetryTimes = 5;

 // protected WxSessionManager sessionManager = new StandardSessionManager();


  /**
   * <pre>
   * 验证消息的确来自微信服务器
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN
   * </pre>
   */
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(configStorage.getToken(), timestamp, nonce)
          .equals(signature);
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * 获取access_token, 不强制刷新access_token
   *
   * @see #getAccessToken(boolean)
   */
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  /**
   * <pre>
   * 获取access_token，本方法线程安全
   * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
   *
   * 另：本service的所有方法都会在access_token过期是调用此方法
   *
   * 程序员在非必要情况下尽量不要主动调用此方法
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    Lock lock = configStorage.getAccessTokenLock();
    try {
      lock.lock();

      if (forceRefresh) {
        configStorage.expireAccessToken();
      }

      if (configStorage.isAccessTokenExpired()) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
            "&appid=" + configStorage.getAppId() + "&secret="
            + configStorage.getSecret();
        try {
          HttpGet httpGet = new HttpGet(url);
          if (this.httpProxy != null) {
            RequestConfig config = RequestConfig.custom().setProxy(this.httpProxy).build();
            httpGet.setConfig(config);
          }
          try (CloseableHttpResponse response = getHttpclient().execute(httpGet)) {
            String resultContent = new BasicResponseHandler().handleResponse(response);
            WxError error = WxError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
              throw new WxErrorException(error);
            }
            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            configStorage.updateAccessToken(accessToken.getAccessToken(),
                accessToken.getExpiresIn());
          }finally {
            httpGet.releaseConnection();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    } finally {
      lock.unlock();
    }
    return configStorage.getAccessToken();
  }

  /**
   * 获得jsapi_ticket,不强制刷新jsapi_ticket
   *
   * @see #getJsapiTicket(boolean)
   */
  public String getJsapiTicket() throws WxErrorException {
    return getJsapiTicket(false);
  }

  /**
   * <pre>
   * 获得jsapi_ticket
   * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   *
   * @param forceRefresh 强制刷新
   */
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    Lock lock = configStorage.getJsapiTicketLock();
    try {
      lock.lock();

      if (forceRefresh) {
        configStorage.expireJsapiTicket();
      }

      if (configStorage.isJsapiTicketExpired()) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
        String responseContent = execute(new SimpleGetRequestExecutor(), url, null);
        JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
        JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
        configStorage.updateJsapiTicket(jsapiTicket, expiresInSeconds);
      }
    } finally {
      lock.unlock();
    }
    return configStorage.getJsapiTicket();
  }

  /**
   * <pre>
   * 创建调用jsapi时所需要的签名
   *
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
   * </pre>
   */
  public WxJsapiSignature createJsapiSignature(String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtils.getRandomStr();
    String jsapiTicket = getJsapiTicket(false);
    String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
        "noncestr=" + noncestr, "timestamp=" + timestamp, "url=" + url);
    WxJsapiSignature jsapiSignature = new WxJsapiSignature();
    jsapiSignature.setAppid(configStorage.getAppId());
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNoncestr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);
    return jsapiSignature;
  }


  /**
   * <pre>
   * 上传群发用的图文消息，上传后才能群发图文消息
   *
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(org.springrain.weixin.base.mp.bean.WxMpMassTagMessage)
   * @see #massOpenIdsMessageSend(org.springrain.weixin.base.mp.bean.WxMpMassOpenIdsMessage)
   */
  public WxMpMassUploadResult massNewsUpload(WxMpMassNews news) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";
    String responseContent = this.post(url, news.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  /**
   * <pre>
   * 上传群发用的视频，上传后才能群发视频消息
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @see #massGroupMessageSend(org.springrain.weixin.base.mp.bean.WxMpMassTagMessage)
   * @see #massOpenIdsMessageSend(org.springrain.weixin.base.mp.bean.WxMpMassOpenIdsMessage)
   */
  public WxMpMassUploadResult massVideoUpload(WxMpMassVideo video) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo";
    String responseContent = this.post(url, video.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  /**
   * <pre>
   * 分组群发消息
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(org.springrain.weixin.base.mp.bean.WxMpMassNews)} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(org.springrain.weixin.base.mp.bean.WxMpMassVideo)} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  public WxMpMassSendResult massGroupMessageSend(WxMpMassTagMessage message) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall";
    String responseContent = this.post(url, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }


  /**
   * <pre>
   * 按openId列表群发消息
   * 如果发送图文消息，必须先使用 {@link #massNewsUpload(org.springrain.weixin.base.mp.bean.WxMpMassNews)} 获得media_id，然后再发送
   * 如果发送视频消息，必须先使用 {@link #massVideoUpload(org.springrain.weixin.base.mp.bean.WxMpMassVideo)} 获得media_id，然后再发送
   * 详情请见: http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   */
  public WxMpMassSendResult massOpenIdsMessageSend(WxMpMassOpenIdsMessage message) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/send";
    String responseContent = this.post(url, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  /**
   * <pre>
   * 群发消息预览接口
   * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。为了满足第三方平台开发者的需求，在保留对openID预览能力的同时，增加了对指定微信号发送预览的能力，但该能力每日调用次数有限制（100次），请勿滥用。
   * 接口调用请求说明
   *  http请求方式: POST
   *  https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
   * 详情请见：http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN
   * </pre>
   *
   * @return wxMpMassSendResult
   */
  public WxMpMassSendResult massMessagePreview(WxMpMassPreviewMessage wxMpMassPreviewMessage) throws Exception {
    String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
    String responseContent = this.post(url, wxMpMassPreviewMessage.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  /**
   * <pre>
   * 长链接转短链接接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=长链接转短链接接口
   * </pre>
   *
   */
  public String shortUrl(String long_url) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/shorturl";
    JsonObject o = new JsonObject();
    o.addProperty("action", "long2short");
    o.addProperty("long_url", long_url);
    String responseContent = this.post(url, o.toString());
    JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("short_url").getAsString();
  }

  /**
   * <pre>
   * 语义查询接口
   * 详情请见：http://mp.weixin.qq.com/wiki/index.php?title=语义理解
   * </pre>
   */
  public WxMpSemanticQueryResult semanticQuery(WxMpSemanticQuery semanticQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/semantic/semproxy/search";
    String responseContent = this.post(url, semanticQuery.toJson());
    return WxMpSemanticQueryResult.fromJson(responseContent);
  }
  /**
   * <pre>
   * 构造第三方使用网站应用授权登录的url
   * 详情请见: <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN">网站应用微信登录开发指南</a>
   * URL格式为：https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
   * </pre>
   *
   * @param redirectURI 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @param scope 应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
   * @param state 非必填，用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
   * @return url
   */
  public String buildQrConnectUrl(String redirectURI, String scope,
      String state) {
    StringBuilder url = new StringBuilder();
    url.append("https://open.weixin.qq.com/connect/qrconnect?");
    url.append("appid=").append(configStorage.getAppId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectURI));
    url.append("&response_type=code");
    url.append("&scope=").append(scope);
    if (state != null) {
      url.append("&state=").append(state);
    }

    url.append("#wechat_redirect");
    return url.toString();
  }


  /**
   * <pre>
   * 构造oauth2授权的url连接
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
   * </pre>
   *
   * @param redirectURI 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @return url
   */
  public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
    StringBuilder url = new StringBuilder();
    url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
    url.append("appid=").append(configStorage.getAppId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectURI));
    url.append("&response_type=code");
    url.append("&scope=").append(scope);
    if (state != null) {
      url.append("&state=").append(state);
    }
    url.append("#wechat_redirect");
    return url.toString();
  }

  
  private WxMpOAuth2AccessToken getOAuth2AccessToken(StringBuilder url) throws WxErrorException {
    try {
      RequestExecutor<String, String> executor = new SimpleGetRequestExecutor();
      String responseText = executor.execute(this.getHttpclient(), this.httpProxy, url.toString(), null);
      return WxMpOAuth2AccessToken.fromJson(responseText);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * <pre>
   * 用code换取oauth2的access token
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=网页授权获取用户基本信息
   * </pre>
   */
  public WxMpOAuth2AccessToken oauth2getAccessToken(String code) throws WxErrorException {
    StringBuilder url = new StringBuilder();
    url.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
    url.append("appid=").append(configStorage.getAppId());
    url.append("&secret=").append(configStorage.getSecret());
    url.append("&code=").append(code);
    url.append("&grant_type=authorization_code");

    return this.getOAuth2AccessToken(url);
  }

  /**
   * <pre>
   * 刷新oauth2的access token
   * </pre>
   */
  public WxMpOAuth2AccessToken oauth2refreshAccessToken(String refreshToken) throws WxErrorException {
    StringBuilder url = new StringBuilder();
    url.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?");
    url.append("appid=").append(configStorage.getAppId());
    url.append("&grant_type=refresh_token");
    url.append("&refresh_token=").append(refreshToken);

    return this.getOAuth2AccessToken(url);
  }

  /**
   * <pre>
   * 用oauth2获取用户信息, 当前面引导授权时的scope是snsapi_userinfo的时候才可以
   * </pre>
   *
   * @param lang              zh_CN, zh_TW, en
   */
  public WxMpUser oauth2getUserInfo(WxMpOAuth2AccessToken oAuth2AccessToken, String lang) throws WxErrorException {
    StringBuilder url = new StringBuilder();
    url.append("https://api.weixin.qq.com/sns/userinfo?");
    url.append("access_token=").append(oAuth2AccessToken.getAccessToken());
    url.append("&openid=").append(oAuth2AccessToken.getOpenId());
    if (lang == null) {
      url.append("&lang=zh_CN");
    } else {
      url.append("&lang=").append(lang);
    }

    try {
      RequestExecutor<String, String> executor = new SimpleGetRequestExecutor();
      String responseText = executor.execute(getHttpclient(), this.httpProxy, url.toString(), null);
      return WxMpUser.fromJson(responseText);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * <pre>
   * 验证oauth2的access token是否有效
   * </pre>
   *
   */
  public boolean oauth2validateAccessToken(WxMpOAuth2AccessToken oAuth2AccessToken) {
    StringBuilder url = new StringBuilder();
    url.append("https://api.weixin.qq.com/sns/auth?");
    url.append("access_token=").append(oAuth2AccessToken.getAccessToken());
    url.append("&openid=").append(oAuth2AccessToken.getOpenId());

    try {
      RequestExecutor<String, String> executor = new SimpleGetRequestExecutor();
      executor.execute(getHttpclient(), this.httpProxy, url.toString(), null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (WxErrorException e) {
      return false;
    }
    return true;
  }

  /**
   * <pre>
   * 获取微信服务器IP地址
   * http://mp.weixin.qq.com/wiki/0/2ad4b6bfd29f30f71d39616c2a0fcedc.html
   * </pre>
   */
  public String[] getCallbackIP() throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
    String responseContent = get(url, null);
    JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
    JsonArray ipList = tmpJsonElement.getAsJsonObject().get("ip_list").getAsJsonArray();
    String[] ipArray = new String[ipList.size()];
    for (int i = 0; i < ipList.size(); i++) {
      ipArray[i] = ipList.get(i).getAsString();
    }
    return ipArray;
  }


  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   */
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(new SimpleGetRequestExecutor(), url, queryParam);
  }


  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   */
  public String post(String url, String postData) throws WxErrorException {
    return execute(new SimplePostRequestExecutor(), url, postData);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
   */
  /**
   * <pre>
   * Service没有实现某个API的时候，可以用这个，
   * 比{@link #get}和{@link #post}方法更灵活，可以自己构造RequestExecutor用来处理不同的参数和不同的返回类型。
   * 可以参考，{@link org.springrain.weixin.base.common.util.http.MediaUploadRequestExecutor}的实现方法
   * </pre>
   */
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        T result = executeInternal(executor, uri, data);
        this.log.debug("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}",uri, data, result);
        return result;
      } catch (WxErrorException e) {
        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            this.log.debug("微信系统繁忙，{}ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
          }
        } else {
          throw e;
        }
      }
    } while (++retryTimes < this.maxRetryTimes);

    throw new RuntimeException("微信服务端异常，超出重试次数");
  }

  protected synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    if (uri.indexOf("access_token=") != -1) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    String uriWithAccessToken = uri;
    uriWithAccessToken += uri.indexOf('?') == -1 ? "?access_token=" + accessToken : "&access_token=" + accessToken;

    try {
      return executor.execute(getHttpclient(), this.httpProxy, uriWithAccessToken, data);
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       * 40001 获取access_token时AppSecret错误，或者access_token无效
       * 42001 access_token超时
       */
      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        configStorage.expireAccessToken();
        if(configStorage.autoRefreshToken()){
          return this.execute(executor, uri, data);
        }
      }

      if (error.getErrorCode() != 0) {
        this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[RESPONSE]: {}", uri, data,
            error);
        throw new WxErrorException(error);
      }
      return null;
    } catch (IOException e) {
      this.log.error("\n[URL]:  {}\n[PARAMS]: {}\n[EXCEPTION]: {}", uri, data, e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public HttpHost getHttpProxy() {
    return this.httpProxy;
  }

  public CloseableHttpClient getHttpclient() {
    return this.httpClient;
  }

  public void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider) {
    this.configStorage = wxConfigProvider;
    this.initHttpClient();
  }

  private void initHttpClient() {
    ApacheHttpClientBuilder apacheHttpClientBuilder = this.configStorage
        .getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
        .httpProxyPort(configStorage.getHttpProxyPort())
        .httpProxyUsername(configStorage.getHttpProxyUsername())
        .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getSSLContext() != null) {
      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
          configStorage.getSSLContext(), new String[] { "TLSv1" }, null,
          new DefaultHostnameVerifier());
      apacheHttpClientBuilder.sslConnectionSocketFactory(sslsf);
    }

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  public WxMpConfigStorage getWxMpConfigStorage() {
    return this.configStorage;
  }

  /**
   * <pre>
   * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试
   * 默认：1000ms
   * </pre>
   */
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  public WxMpKefuService getKefuService() {
    return this.kefuService;
  }

  public WxMpMaterialService getMaterialService() {
    return this.materialService;
  }

  public WxMpMenuService getMenuService() {
    return this.menuService;
  }

  public WxMpUserService getUserService() {
    return this.userService;
  }

  public WxMpUserTagService getUserTagService() {
    return this.tagService;
  }

  public WxMpQrcodeService getQrcodeService() {
    return this.qrCodeService;
  }

  public WxMpCardService getCardService() {
    return this.cardService;
  }

  public WxMpPayService getPayService() {
    return this.payService;
  }

  public WxMpDataCubeService getDataCubeService() {
    return this.dataCubeService;
  }

  public WxMpUserBlacklistService getBlackListService() {
    return this.blackListService;
  }

  public WxMpStoreService getStoreService() {
    return this.storeService;
  }

  public WxMpTemplateMsgService getTemplateMsgService() {
    return this.templateMsgService;
  }

}
