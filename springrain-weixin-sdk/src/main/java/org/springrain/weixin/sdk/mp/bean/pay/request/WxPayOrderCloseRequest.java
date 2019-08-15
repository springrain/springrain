package org.springrain.weixin.sdk.mp.bean.pay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *  关闭订单请求对象类
 * Created by springrain on 2016-10-27.
 * @author <a href="http://git.oschina.net/chunanyong/springrain">springrain(springrain)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayOrderCloseRequest extends WxPayBaseRequest{

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 二选一
   * String(32)
   * 20150806125346
   * 商户系统内部的订单号，当没提供transaction_id时需要传这个。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  public String getOutTradeNo() {
    return this.outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

}
