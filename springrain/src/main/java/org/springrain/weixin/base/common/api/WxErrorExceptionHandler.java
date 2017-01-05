package org.springrain.weixin.base.common.api;

import org.springrain.weixin.base.common.exception.WxErrorException;


/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
