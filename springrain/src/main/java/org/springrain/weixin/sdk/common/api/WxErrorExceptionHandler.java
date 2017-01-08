package org.springrain.weixin.sdk.common.api;

import org.springrain.weixin.sdk.common.exception.WxErrorException;


/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
