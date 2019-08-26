package org.springrain.weixin.sdk.pay;

import com.jfinal.weixin.sdk.kit.PaymentKit;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;

import java.util.Map;

/**
 * 微信红包api
 *
 * @author osc余书慧
 */
public class RedPackApi {

    // 文档地址：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5#
    private static String sendRedPackUrl = WxConsts.mppaybaseurl + "/mmpaymkttransfers/sendredpack";
    // 文档地址：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_7&index=6
    private static String getHBInfo = WxConsts.mppaybaseurl + "/mmpaymkttransfers/gethbinfo ";
    // 裂变红包：https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=16_5
    private static String sendGroupRedPackUrl = WxConsts.mppaybaseurl + "/mmpaymkttransfers/sendgroupredpack";

    /**
     * 发送红包
     *
     * @param params   请求参数
     * @param certPath 证书文件目录
     * @param partner  证书密码
     * @return {String}
     */
    public static String sendRedPack(IWxMpConfig wxmpconfig, Map<String, String> params, String certPath, String partner) {
        return HttpClientUtils.sendHttpPostSSL(sendRedPackUrl, PaymentKit.toXml(params), certPath, partner);
    }

    /**
     * 根据商户订单号查询信息
     *
     * @param params   请求参数
     * @param certPath 证书文件目录
     * @param partner  证书密码
     * @return {String}
     */
    public static String getHbInfo(IWxMpConfig wxmpconfig, Map<String, String> params, String certPath, String partner) {
        return HttpClientUtils.sendHttpPostSSL(getHBInfo, PaymentKit.toXml(params), certPath, partner);
    }

    /**
     * 发送裂变红包
     *
     * @param params   请求参数
     * @param certPath 证书文件目录
     * @param partner  证书密码
     * @return {String}
     */
    public static String sendGroupRedPack(IWxMpConfig wxmpconfig, Map<String, String> params, String certPath, String partner) {
        return HttpClientUtils.sendHttpPostSSL(sendGroupRedPackUrl, PaymentKit.toXml(params), certPath, partner);
    }
}
