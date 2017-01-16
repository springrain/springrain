package org.springrain.weixin.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springrain.cms.utils.SiteUtils;
import org.springrain.frame.controller.BaseController;
import org.springrain.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.common.api.WxConsts;
import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.mp.api.IWxMpService;
import org.springrain.weixin.sdk.mp.bean.result.WxMpOAuth2AccessToken;
import org.springrain.weixin.sdk.mp.bean.result.WxMpUser;

@Controller
@RequestMapping(value = "/wx/author")
public class WxAuthorController extends BaseController {
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
	public String oauth2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		
		String siteId=SiteUtils.getSiteId();
		if(StringUtils.isBlank(siteId)){
			siteId=request.getParameter("_siteId");
		}
		
		if(StringUtils.isBlank(url)||StringUtils.isBlank(siteId)){
			return null;
		}
		
		
		WxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
		
		
		String _url=SiteUtils.getSiteDomain(request)+"wx/author/callback?_siteId="+siteId+"&url=" + url;
		
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
	public String callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WxMpUser wxMpUser = new WxMpUser();
		String url = request.getParameter("url");
		String code = request.getParameter("code");
		String siteId=request.getParameter("_siteId");
		
		WxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
		try {
			// 获取OpenId
			WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(wxmpconfig, code);
			// wxMpUser=wxMpService.oauth2getUserInfo(wxmpconfig,accessToken,"zh_CN");
			request.getSession().setAttribute("openId", accessToken.getOpenId());
			System.out.println("重新获取用户信息了！");
		} catch (WxErrorException e) {
			logger.error(e.getMessage());
		}
		return redirect + url;
	}

}
