package org.springrain.weixin.base.cp.api;

import org.springrain.weixin.base.cp.bean.WxCpXmlMessage;

/**
 * 消息匹配器，用在消息路由的时候
 */
public interface WxCpMessageMatcher {

  /**
   * 消息是否匹配某种模式
   */
  boolean match(WxCpXmlMessage message);

}
