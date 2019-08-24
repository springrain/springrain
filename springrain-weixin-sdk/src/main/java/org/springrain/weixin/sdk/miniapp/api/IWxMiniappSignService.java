package org.springrain.weixin.sdk.miniapp.api;

import java.util.Map;

import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxMiniappConfig;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.request.WxSignRequest;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.request.WxSurrenderRequest;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.result.WxSignResult;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.result.WxSurrenderResult;

/**
 * 用户管理相关操作接口
 *
 * @author springrain
 */
public interface IWxMiniappSignService {

	  /**
	   * 微信签约接口
	   * @param wxminiappconfig
	   * @param request
	   * @return
	   * @throws WxErrorException
	   */
	   WxSignResult getWxSignInfo(IWxMiniappConfig wxminiappconfig, WxSignRequest request) throws WxErrorException;
	   
	   
	   /**
	   * 微信解约接口
	   * @param wxminiappconfig
	   * @param request
	   * @return
	   * @throws WxErrorException
	   */
	   WxSurrenderResult getWxSurrenderInfo(IWxMiniappConfig wxminiappconfig, WxSurrenderRequest request) throws WxErrorException;
	   
	   
	   /**
	    * 微信签约url
	    */
	   String getWxSignUrl(IWxMiniappConfig wxminiappconfig, WxSignRequest request) throws WxErrorException;
	   
	   
	   /**
	    * 获取微信签约参数
	    * @param wxminiappconfig
	    * @param request
	    * @return
	    * @throws WxErrorException
	    */
	   Map<String, String> getSignParam(IWxMiniappConfig wxminiappconfig, WxSignRequest request) throws WxErrorException;

}
