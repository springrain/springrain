/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.util;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springrain.frame.shiro.ShiroUser;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.SpringUtils;
import org.springrain.weixin.bean.Articles;
import org.springrain.weixin.bean.Attachment;
import org.springrain.weixin.bean.InMessage;
import org.springrain.weixin.bean.OutMessage;
import org.springrain.weixin.oauth.CustomMessage;
import org.springrain.weixin.oauth.Group;
import org.springrain.weixin.oauth.Menu;
import org.springrain.weixin.oauth.Message;
import org.springrain.weixin.oauth.Oauth;
import org.springrain.weixin.oauth.Qrcod;
import org.springrain.weixin.oauth.User;
import org.springrain.weixin.service.IMessageProcessingService;

import com.thoughtworks.xstream.XStream;

/**
 * 微信常用的API
 *
 * @author L.cm & ____′↘夏悸
 * @date 2013-11-5 下午3:01:20
 */
public class WeiXinUtils {
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
	private static final String GET_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
	private static final String UPLOAD_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	
	//private static final String SEND_CUSTOM_MESSAGE_URL= "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	
	private static IMessageProcessingService messageProcessingService;
	
    

    /**
     * 消息操作接口
     */
    public static final Message message = new Message();
    /**
     * 菜单操作接口
     */
    public static final Menu menu = new Menu();
    /**
     * 用户操作接口
     */
    public static final User user = new User();
    /**
     * 分组操作接口
     */
    public static final Group group = new Group();
    
    /**
     * 分组操作接口
     */
    public static final Qrcod qrcod = new Qrcod();
    
    /**
     * 客服消息接口
     */
    public static final CustomMessage customMessage=new CustomMessage();
    /**
     * 微信认证接口
     */
    public static final Oauth oauth=new Oauth();
    
    

    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String appid = ConfKit.get("AppId");
        String secret = ConfKit.get("AppSecret");
        return getAccessToken(appid, secret);
    }
    
    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appid,String secret) throws Exception {
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JsonUtils.readValue(jsonStr, Map.class);
        return map.get("access_token").toString();
    }

   /**
    * 支付反馈
    * @param openid
    * @param feedbackid
    * @return
    * @throws Exception
    */
    public static boolean payfeedback(String openid, String feedbackid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("feedbackid", feedbackid);
        String jsonStr = HttpKit.get(PAYFEEDBACK_URL, map);
        Map<String, Object> jsonMap = JsonUtils.readValue(jsonStr, Map.class);
        return "0".equals(jsonMap.get("errcode").toString());
    }

    /**
     * 签名检查
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        return Tools.checkSignature(token, signature, timestamp, nonce);
    }

    /**
     * 根据接收到用户消息进行处理
     * @param responseInputString   微信发送过来的xml消息体
     * @return
     * @throws Exception 
     */
    public static String processing(String responseInputString,String weixinId) throws Exception {
    	if(messageProcessingService==null){
    		messageProcessingService=(IMessageProcessingService) SpringUtils.getBean("defaultMessageProcessingService");
    	}
    	
        InMessage inMessage = parsingInMessage(responseInputString);
        OutMessage oms = null;
      
        String xml = "";
            //取得消息类型
            String type = inMessage.getMsgType().toLowerCase();
            oms= messageProcessingService.getOutMessageByInMessage(inMessage, type,weixinId);
            
            if(oms==null){
            	return null;
            }
            
            
            //调用事后处理
     
            	messageProcessingService.afterProcess(inMessage, oms,weixinId);
		
            
            if(oms != null){
            	setMsgInfo(oms, inMessage);
            }
  
        if(oms != null){
            // 把发送发送对象转换为xml输出
            XStream xs = XStreamFactory.init(true);
            xs.alias("xml", oms.getClass());
            xs.alias("item", Articles.class);
            xml = xs.toXML(oms);
        }
        return xml;
    }

    /**
     * 设置发送消息体
     * @param oms
     * @param msg
     * @throws Exception
     */
    private static void setMsgInfo(OutMessage oms,InMessage msg) throws Exception {
    	if(oms==null||msg==null){
    		return;
    	}
            oms.setCreateTime(new Date().getTime());
            oms.setFromUserName(msg.getToUserName());
            oms.setToUserName(msg.getFromUserName());
    }

    /**
     *消息体转换
     * @param responseInputString
     * @return
     */
    private static InMessage parsingInMessage(String responseInputString) {
        //转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(responseInputString);
        return msg;
    }
    
    /**
     * 获取媒体资源
     * @param accessToken
     * @param mediaId
     * @return
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     */
    public static Attachment getMedia(String accessToken,String mediaId) throws IOException, ExecutionException, InterruptedException{
    	String url = GET_MEDIA_URL + accessToken + "&media_id=" + mediaId;
        return HttpKit.download(url);
    }
    
    
   
    
    
    /**
     * 上传素材文件
     * @param type
     * @param file
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> uploadMedia(String accessToken,String type,File file) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException, ExecutionException, InterruptedException {
        String url = UPLOAD_MEDIA_URL + accessToken +"&type="+type;
        String jsonStr = HttpKit.upload(url,file);
        return JsonUtils.readValue(jsonStr, Map.class);
    }
    
    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     * @param request
     * @return
     */
 	public static boolean isWeiXin(HttpServletRequest request) {
 		String userAgent = request.getHeader("User-Agent");
 		if (StringUtils.isNotBlank(userAgent)) {
 			Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
 			Matcher m = p.matcher(userAgent);
 			String version = null;
 			if(m.find()){
 				version = m.group(1);
 			}
 			return (null != version && NumberUtils.toInt(version) >= 5); 
 		}
 		return false;
 	}
 	
 	
 	/**
 	 * 根据 微信账号的 openId 创建shiro的登陆账号
 	 * @param openId
 	 * @param request
 	 * @param response
 	 */
 	public static void createShiroUserByOpenId(String openId,ServletRequest request,ServletResponse response){
		ShiroUser shiroUser=new ShiroUser();
		shiroUser.setId(openId);
		shiroUser.setName(openId);
		shiroUser.setAccount(openId);
		 SimplePrincipalCollection principals = new SimplePrincipalCollection(shiroUser, GlobalStatic.authorizingRealmName);
         WebSubject.Builder builder = new WebSubject.Builder(request,response);
         builder.principals(principals);
         builder.authenticated(true);
         WebSubject subject = builder.buildWebSubject();
         ThreadContext.bind(subject);
 	}
 	
 	
}
