package org.springrain.weixin.sdk.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.weixin.sdk.common.api.WxErrorExceptionHandler;
import org.springrain.weixin.sdk.common.exception.WxErrorException;


public class LogExceptionHandler implements WxErrorExceptionHandler {

  private Logger logger = LoggerFactory.getLogger(WxErrorExceptionHandler.class);

  @Override
  public void handle(WxErrorException e) {

    this.logger.error("Error happens", e);

  }

}
