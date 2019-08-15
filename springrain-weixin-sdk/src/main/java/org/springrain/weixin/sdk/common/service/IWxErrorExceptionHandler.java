package org.springrain.weixin.sdk.common.service;

import org.springrain.weixin.sdk.common.exception.WxErrorException;


/**
 * WxErrorException处理器
 */
public interface IWxErrorExceptionHandler {

  void handle(WxErrorException e);

}
