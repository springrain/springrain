/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package org.springrain.weixin.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.util.HttpKit;

/**
 * 菜单,可以将accessToken
 * 存储在session或者memcache中
 * @author L.cm
 * @date 2013-11-5 下午3:17:33
 */
@SuppressWarnings("unchecked")
public class Menu {

    /**
     * 创建菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
	public boolean createMenu(String accessToken,String params) throws InterruptedException, ExecutionException, IOException {
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, params);
        Map<String, Object> map = JsonUtils.readValue(jsonStr,Map.class);
        return "0".equals(map.get("errcode").toString());
    }
    
    /**
     * 查询菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public Map<String, Object> getMenuInfo(String accessToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
        Map<String, Object> map = JsonUtils.readValue(jsonStr, Map.class);
        return map;
    }
    
    /**
     * 删除自定义菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public boolean deleteMenu(String accessToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
        Map<String, Object> map = JsonUtils.readValue(jsonStr, Map.class);
        return "0".equals(map.get("errcode").toString());
    }
}
