package org.springrain.system.weixin.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.common.ShiroUser;
import org.springrain.system.core.service.IUserService;
import org.springrain.weixin.sdk.common.api.IWxXcxConfig;
import org.springrain.weixin.sdk.common.api.IWxXcxConfigService;
import org.springrain.weixin.sdk.xcx.api.IWxXcxService;
import org.springrain.weixin.sdk.xcx.bean.result.WxMpOAuth2SessionKey;

@Controller
@RequestMapping(value = "/f/xcx/autologin/{siteId}")
public class WxXcxAutoLoginController extends BaseController {

    @Resource
    private IUserService userService;

    @Resource
    IWxXcxConfigService wxXcxConfigService;

    @Resource
    IWxXcxService wxXcxService;

    /**
     * 通过code获取access_token
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/login")
    public ReturnDatas login(@PathVariable String siteId, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            returnDatas.setMessage("登录成功");
            String code = request.getParameter("code");
            String headImgUrl = request.getParameter("avatarUrl");
            String nickname = request.getParameter("nickname");
            Bind(request, response, returnDatas, code, siteId, headImgUrl, nickname);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ReturnDatas.getErrorReturnDatas();
        }
        return returnDatas;

    }

    private ReturnDatas Bind(HttpServletRequest request, HttpServletResponse response, ReturnDatas returnDatas,
            String code, String siteId, String headImgUrl, String nickname) throws Exception {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(code)) {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("获取code失败");
            return returnDatas;
        }

        Object openIdObj = request.getSession().getAttribute(GlobalStatic.tokenKey);

        if (openIdObj != null) {// 没有登录
            Map<String, String> maps = new HashMap<String, String>();
            maps.put("springraintoken", request.getSession().getAttribute(GlobalStatic.tokenKey).toString());
            maps.put("sessionId", request.getSession().getId());
            returnDatas.setData(maps);
            return returnDatas;
        }

        IWxXcxConfig xcxconfig = wxXcxConfigService.findWxXcxConfigById(siteId);
        if (xcxconfig == null) {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("读取该站点小程序配置失败");
            return returnDatas;
        }

        // 请求微信服务器时间
        WxMpOAuth2SessionKey wSessionKey = wxXcxService.oauth2getSessionKey(xcxconfig, code);
        if (wSessionKey == null) {
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("读取该站点小程序wSessionKey失败");
            return returnDatas;
        }
        String openId = wSessionKey.getOpenId();
        String session_key = wSessionKey.getSessionKey();

        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(openId);
        shiroUser.setName(nickname);
        shiroUser.setAccount(openId);
        SimplePrincipalCollection principals = new SimplePrincipalCollection(shiroUser,
                GlobalStatic.authorizingRealmName);
        WebSubject.Builder builder = new WebSubject.Builder(request, response);
        builder.principals(principals);
        builder.authenticated(true);
        WebSubject subject = builder.buildWebSubject();
        ThreadContext.bind(subject);

        request.getSession().setAttribute(GlobalStatic.tokenKey, "uc_" + SecUtils.getUUID());

        request.getSession().setAttribute("openId", openId);
        request.getSession().setAttribute("session_key", session_key);

        Map<String, String> maps = new HashMap<String, String>();
        maps.put("springraintoken", request.getSession().getAttribute(GlobalStatic.tokenKey).toString());
        maps.put("sessionId", request.getSession().getId());
        returnDatas.setData(maps);

        // EncryptedData enData =
        // wxXcxService.getEncryptedDataInfo(request.getParameter("encryptedData"),session_key,request.getParameter("iv"));

        return returnDatas;
    }

}
