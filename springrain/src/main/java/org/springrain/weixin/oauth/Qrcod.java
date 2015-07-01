package org.springrain.weixin.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.util.HttpKit;

/**
 * 创建二维码
 *
 * @author ____′↘夏悸
 */
public class Qrcod {

    private static final String QRCOD_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    private static final String QRCOD_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    /**
     * 创建临时二维码
     *
     * @param accessToken
     * @param expireSeconds 最大不超过1800
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Map createScene(String accessToken, int expireSeconds,
                                  int sceneId) throws InterruptedException, ExecutionException, IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        params.put("expire_seconds", expireSeconds);
        params.put("action_name", "QR_SCENE");
        scene.put("scene_id", sceneId);
        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        String post = JsonUtils.writeValueAsString(params);
        String reslut = HttpKit.post(QRCOD_CREATE.concat(accessToken), post);
        if (StringUtils.isNotEmpty(reslut)) {
        	return JsonUtils.readValue(reslut, Map.class);
           
        }
        return null;
    }

    /**
     * 创建永久二维码
     *
     * @param accessToken
     * @param sceneId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Map createLimitScene(String accessToken, int sceneId) throws InterruptedException, ExecutionException, IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> actionInfo = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        params.put("action_name", "QR_LIMIT_SCENE");
        scene.put("scene_id", sceneId);
        actionInfo.put("scene", scene);
        params.put("action_info", actionInfo);
        String post = JsonUtils.writeValueAsString(params);
        String reslut = HttpKit.post(QRCOD_CREATE.concat(accessToken), post);
        if (StringUtils.isNotEmpty(reslut)) {
        	return JsonUtils.readValue(reslut, Map.class);
        }
        return null;
    }

    /**
     * 获取查看二维码链接
     *
     * @param ticket
     * @return
     */
    public static String showqrcodeUrl(String ticket) {
        return QRCOD_SHOW.concat(ticket);
    }
}
