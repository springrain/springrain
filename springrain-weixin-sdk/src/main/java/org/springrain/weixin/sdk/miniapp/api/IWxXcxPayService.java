package org.springrain.weixin.sdk.miniapp.api;

import java.io.File;
import java.util.Map;

import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxXcxConfig;
import org.springrain.weixin.sdk.mp.bean.pay.WxPayJsSDKCallback;
import org.springrain.weixin.sdk.mp.bean.pay.request.WxEntPayRequest;
import org.springrain.weixin.sdk.mp.bean.pay.request.WxPayRefundRequest;
import org.springrain.weixin.sdk.mp.bean.pay.request.WxPaySendRedpackRequest;
import org.springrain.weixin.sdk.mp.bean.pay.request.WxPayUnifiedOrderRequest;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxEntPayQueryResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxEntPayResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayOrderCloseResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayOrderQueryResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayRedpackQueryResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayRefundQueryResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayRefundResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPaySendRedpackResult;
import org.springrain.weixin.sdk.mp.bean.pay.result.WxPayUnifiedOrderResult;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.request.WxAutoDebitRequest;
import org.springrain.weixin.sdk.miniapp.bean.result.sign.result.WxAutoDebitResult;

public interface IWxXcxPayService {

	  /**
	   * <pre>
	   * 查询订单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_2)
	   * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
	   * 需要调用查询接口的情况：
	   * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
	   * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
	   * ◆ 调用被扫支付API，返回USERPAYING的状态；
	   * ◆ 调用关单或撤销接口API之前，需确认支付状态；
	   * 接口地址：https://api.mch.weixin.qq.com/pay/orderquery
	   * </pre>
	   *
	   * @param transactionId 微信支付分配的商户号
	   * @param outTradeNo    商户系统内部的订单号，当没提供transaction_id时需要传这个。
	   * @throws WxErrorException
	   */
	  WxPayOrderQueryResult queryOrder(IWxXcxConfig wxxcxconfig,String transactionId, String outTradeNo) throws WxErrorException;

	  /**
	   * <pre>
	   * 关闭订单
	   * 应用场景
	   * 以下情况需要调用关单接口：
	   * 1. 商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
	   * 2. 系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	   * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
	   * 接口地址：https://api.mch.weixin.qq.com/pay/closeorder
	   * 是否需要证书：   不需要。
	   * </pre>
	   *
	   * @param outTradeNo 商户系统内部的订单号，当没提供transaction_id时需要传这个。
	   * @throws WxErrorException
	   */
	  WxPayOrderCloseResult closeOrder(IWxXcxConfig wxxcxconfig,String outTradeNo) throws WxErrorException;

	  /**
	   * 统一下单(详见http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
	   * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
	   * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
	   *
	   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
	   * @throws WxErrorException
	   */
	  WxPayUnifiedOrderResult unifiedOrder(IWxXcxConfig wxxcxconfig,WxPayUnifiedOrderRequest request) throws WxErrorException;

	  /**
	   * 该接口调用“统一下单”接口，并拼装发起支付请求需要的参数
	   * 详见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115&token=&lang=zh_CN
	   *
	   * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
	   * @throws WxErrorException
	   */
	  Map<String, String> getPayInfo(IWxXcxConfig wxxcxconfig,WxPayUnifiedOrderRequest request) throws WxErrorException;

	  /**
	   * <pre>
	   * 微信支付-申请退款
	   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	   * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
	   * </pre>
	   *
	   * @param request 请求对象
	   * @param keyFile 证书文件对象
	   * @return 退款操作结果
	   */
	  WxPayRefundResult refund(IWxXcxConfig wxxcxconfig,WxPayRefundRequest request, File keyFile) throws WxErrorException;

	  /**
	   * <pre>
	   * 微信支付-查询退款
	   * 应用场景：
	   *  提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
	   * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_5
	   * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
	   * </pre>
	   *  以下四个参数四选一
	   * @param transactionId 微信订单号
	   * @param outTradeNo    商户订单号
	   * @param outRefundNo   商户退款单号
	   * @param refundId      微信退款单号
	   * @return 退款信息
	   */
	  WxPayRefundQueryResult refundQuery(IWxXcxConfig wxxcxconfig,String transactionId, String outTradeNo, String outRefundNo, String refundId) throws WxErrorException;

	  /**
	   * 读取支付结果通知
	   * 详见http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
	   */
	  WxPayJsSDKCallback getJSSDKCallbackData(IWxXcxConfig wxxcxconfig,String xmlData) throws WxErrorException;

	  /**
	   * <pre>
	   * 计算Map键值对是否和签名相符,
	   * 按照字段名的 ASCII 码从小到大排序(字典序)后,使用 URL 键值对的 格式(即 key1=value1&key2=value2...)拼接成字符串
	   * </pre>
	   */
	  boolean checkJSSDKCallbackDataSignature(IWxXcxConfig wxxcxconfig,Map<String, String> kvm, String signature);

	  /**
	   * 发送微信红包给个人用户
	   * <pre>
	   * 文档详见:
	   * 发送普通红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3
	   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack
	   * 发送裂变红包 https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_5&index=4
	   *  接口地址：https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack
	   * </pre>
	   *
	   * @param request 请求对象
	   * @param keyFile 证书文件对象
	   */
	  WxPaySendRedpackResult sendRedpack(IWxXcxConfig wxxcxconfig,WxPaySendRedpackRequest request, File keyFile) throws WxErrorException;

	  /**
	   * <pre>
	   *   查询红包记录
	   *   用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变包。
	   *   请求Url	https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo
	   *   是否需要证书	是（证书及使用说明详见商户证书）
	   *   请求方式	POST
	   * </pre>
	   * @param mchBillNo 商户发放红包的商户订单号，比如10000098201411111234567890
	   * @param keyFile 证书文件对象
	   */
	  WxPayRedpackQueryResult queryRedpack(IWxXcxConfig wxxcxconfig,String mchBillNo, File keyFile) throws WxErrorException;

	  /**
	   * <pre>
	   * 企业付款业务是基于微信支付商户平台的资金管理能力，为了协助商户方便地实现企业向个人付款，针对部分有开发能力的商户，提供通过API完成企业付款的功能。
	   * 比如目前的保险行业向客户退保、给付、理赔。
	   * 企业付款将使用商户的可用余额，需确保可用余额充足。查看可用余额、充值、提现请登录商户平台“资金管理”https://pay.weixin.qq.com/进行操作。
	   * 注意：与商户微信支付收款资金并非同一账户，需要单独充值。
	   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
	   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers
	   * </pre>
	   *
	   * @param request 请求对象
	   * @param keyFile 证书文件对象
	   */
	  WxEntPayResult entPay(IWxXcxConfig wxxcxconfig,WxEntPayRequest request, File keyFile) throws WxErrorException;

	  /**
	   * <pre>
	   * 查询企业付款API
	   * 用于商户的企业付款操作进行结果查询，返回付款操作详细结果。
	   * 文档详见:https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_3
	   * 接口链接：https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo
	   * </pre>
	   *
	   * @param partnerTradeNo 商户订单号
	   * @param keyFile        证书文件对象
	   */
	  WxEntPayQueryResult queryEntPay(IWxXcxConfig wxxcxconfig,String partnerTradeNo, File keyFile) throws WxErrorException;
	  
	  
	  /**
	   * 微信委托自动扣款接口
	   * @param wxxcxconfig
	   * @param request
	   * @return
	   * @throws WxErrorException
	   */
	  WxAutoDebitResult getAutoDebitInfo(IWxXcxConfig wxxcxconfig,WxAutoDebitRequest request) throws WxErrorException;

}
