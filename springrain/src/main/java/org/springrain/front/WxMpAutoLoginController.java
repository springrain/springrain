package org.springrain.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.controller.BaseController;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.common.api.WxConsts;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping(value = "/wx/mpautologin/{siteId}")
public class WxMpAutoLoginController extends BaseController {
	@Resource
	IWxMpService wxMpService;
	@Resource
	IWxMpConfigService wxMpConfigService;

	/**
	 * 跳转到微信认证页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/oauth2")
	public String oauth2(@PathVariable String siteId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		if(StringUtils.isBlank(url)||StringUtils.isBlank(siteId)){
			return null;
		}
		
		
		WxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
		
		
		String _url=SiteUtils.getBaseURL(request)+"/wx/mpautologin/"+siteId+"/callback?url=" + url;
		
		String oauthUrl = wxMpService.oauth2buildAuthorizationUrl(wxmpconfig,_url, WxConsts.OAUTH2_SCOPE_BASE, null);
		return redirect + oauthUrl;
	}

	/**
	 * 获取微信用户信息（openid）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/callback")
	public String callback(@PathVariable String siteId,HttpServletRequest request, HttpServletResponse response) throws Exception {
		//WxMpUser wxMpUser = new WxMpUser();
		String url = request.getParameter("url");
		String code = request.getParameter("code");
		
		if(StringUtils.isBlank(url)||StringUtils.isBlank(code)||StringUtils.isBlank(siteId)){
			return null;
		}
		
		
		
		
		WxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
		try {
			// 获取OpenId
			WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(wxmpconfig, code);
			// wxMpUser=wxMpService.oauth2getUserInfo(wxmpconfig,accessToken,"zh_CN");
			request.getSession().setAttribute("openId", accessToken.getOpenId());
		} catch (WxErrorException e) {
			logger.error(e.getMessage());
		}
		return redirect + url;
	}

}
