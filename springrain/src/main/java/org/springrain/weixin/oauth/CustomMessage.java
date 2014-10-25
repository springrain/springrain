package org.springrain.weixin.oauth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springrain.weixin.util.HttpKit;
import org.springrain.weixin.util.WeiXinUtils;

/**
 * 客服消息
 * @author caomei
 *
 */
public class CustomMessage {
	private static final String SEND_CUSTOM_MESSAGE_URL= "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
	
	
	 /**
     * 发送客服消息
     * @param accessToken
     * @param mediaId
     * @return
     * @throws Exception 
     */
    public static void sendCustomMessage(Map map) throws Exception{
    	String url = SEND_CUSTOM_MESSAGE_URL + WeiXinUtils.getAccessToken() ;
    	HttpKit.post(url, map);
    }
    
    
    /**
     * 发送客服消息
     * @param accessToken
     * @param mediaId
     * @return
     * @throws Exception 
     */
    public static void sendCustomMessage(String json) throws Exception{
    	String url = SEND_CUSTOM_MESSAGE_URL +  WeiXinUtils.getAccessToken();
    	HttpKit.post(url, json);
    }
    /**
     * 发送文本信息
     * @param json
     * @throws Exception
     */
    public static void sendTextCustomMessage(String weixinId,String touser,String content)throws Exception{
    	Map map=new HashMap();
		map.put("msgtype", "text");
		map.put("touser", touser);
		Map contentMap=new HashMap();
		contentMap.put("content", content);
		map.put("text", contentMap);
		sendCustomMessage(map);
    }
    
    /**
     * 发送图文信息
     * @param json
     * @throws Exception
     */
    public static void sendNewsCustomMessage(String weixinId,String touser,List<Map> maps)throws Exception{
    	Map map=new HashMap();
		map.put("msgtype", "news");
		map.put("touser", touser);
		Map contentMap=new HashMap();
		contentMap.put("articles", maps);
		map.put("news", contentMap);
		sendCustomMessage(map);
    }
    
    
    
    
    
    
	
	
	
}
