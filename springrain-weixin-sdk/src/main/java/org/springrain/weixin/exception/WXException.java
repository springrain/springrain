package org.springrain.weixin.exception;

/**
 * @descriptions: 微信支付异常类
 * @author: xiaohua
 * @date: 2024/3/29 14:05
 * @version: 1.0
 */
public class WXException extends RuntimeException {
    public static final Exception OPERATION_FAIL = new WXException("操作失败!");
    public static final Exception ORDER_NOT_EXIST = new WXException("订单不存在!");
    public static final Exception ORDER_ERR = new WXException("订单异常");
    public WXException(String message) {
        super(message);
    }
}