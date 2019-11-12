package org.springrain.alipay.sdk.pay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AliPayApi {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * APP支付
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeAppPayModel}
     * @param notifyUrl    异步通知 URL
     * @return {@link AlipayTradeAppPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeAppPayResponse appPayToResponse(IAliPayConfig aliPayConfig, AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).sdkExecute(request);
    }

    /**
     * WAP支付
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeWapPayModel}
     * @param returnUrl    异步通知URL
     * @param notifyUrl    同步通知URL
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String wapPay(IAliPayConfig aliPayConfig, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
        String form = wapPayStr(aliPayConfig, model, returnUrl, notifyUrl);
        return form;
    }

    /**
     * <p>WAP支付</p>
     *
     * <p>为了解决 Filter 中使用 OutputStream getOutputStream() 和 PrintWriter getWriter() 冲突异常问题</p>
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeWapPayModel}
     * @param returnUrl    异步通知URL
     * @param notifyUrl    同步通知URL
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String wapPayByOutputStream(IAliPayConfig aliPayConfig, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
        String form = wapPayStr(aliPayConfig, model, returnUrl, notifyUrl);
        return form;

    }


    /**
     * WAP支付
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeWapPayModel}
     * @param returnUrl    异步通知URL
     * @param notifyUrl    同步通知URL
     * @return {String}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String wapPayStr(IAliPayConfig aliPayConfig, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
        AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
        aliPayRequest.setReturnUrl(returnUrl);
        aliPayRequest.setNotifyUrl(notifyUrl);
        aliPayRequest.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).pageExecute(aliPayRequest).getBody();
    }

    /**
     * 统一收单交易支付接口接口 <br>
     * 适用于:条形码支付、声波支付等 <br>
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePayModel}
     * @param notifyUrl    异步通知URL
     * @return {@link AlipayTradePayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePayResponse tradePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePayModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        // 填充业务参数
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易支付接口接口 <br>
     * 适用于:条形码支付、声波支付等 <br>
     *
     * @param aliPayConfig
     * @param model        {AlipayTradePayModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {AlipayTradePayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePayResponse tradePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单线下交易预创建 <br>
     * 适用于：扫码支付等 <br>
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePrecreateModel}
     * @param notifyUrl    异步通知URL
     * @return {@link AlipayTradePrecreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePrecreateModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单线下交易预创建 <br>
     * 适用于：扫码支付等 <br>
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePrecreateModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradePrecreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePrecreateModel model,
                                                                           String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 单笔转账到支付宝账户
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransToaccountTransferModel}
     * @return 转账是否成功
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static boolean transfer(IAliPayConfig aliPayConfig, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
        AlipayFundTransToaccountTransferResponse response = transferToResponse(aliPayConfig, model);
        String result = response.getBody();
        if (response.isSuccess()) {
            return true;
        } else {
            // 调用查询接口查询数据
            Map mapResult = JsonUtils.readValue(result, HashMap.class);
            Map map = (Map) mapResult.get("alipay_fund_trans_toaccount_transfer_response");
            String outBizNo = (String) map.get("out_biz_no");
            AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
            model.setOutBizNo(outBizNo);
            return transferQuery(aliPayConfig, queryModel);
        }
    }

    /**
     * 单笔转账到支付宝账户
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransToaccountTransferModel}
     * @return {@link AlipayFundTransToaccountTransferResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundTransToaccountTransferResponse transferToResponse(IAliPayConfig aliPayConfig, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 转账查询接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransOrderQueryModel}
     * @return 是否存在此
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static boolean transferQuery(IAliPayConfig aliPayConfig, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
        AlipayFundTransOrderQueryResponse response = transferQueryToResponse(aliPayConfig, model);
        return response.isSuccess();
    }

    /**
     * 转账查询接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransOrderQueryModel}
     * @return {@link AlipayFundTransOrderQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundTransOrderQueryResponse transferQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单线下交易查询接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeQueryModel}
     * @return {@link AlipayTradeQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeQueryResponse tradeQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeQueryModel model) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单线下交易查询接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeQueryModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeQueryResponse tradeQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeQueryModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易撤销接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCancelModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCancelResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCancelResponse tradeCancelToResponse(IAliPayConfig aliPayConfig, AlipayTradeCancelModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易撤销接口
     *
     * @param model {@link AlipayTradeCancelModel}
     * @return {@link AlipayTradeCancelResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCancelResponse tradeCancelToResponse(IAliPayConfig aliPayConfig, AlipayTradeCancelModel model)
            throws AlipayApiException {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易关闭接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCloseModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCloseResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCloseResponse tradeCloseToResponse(IAliPayConfig aliPayConfig, AlipayTradeCloseModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);

    }

    /**
     * 统一收单交易关闭接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCloseModel}
     * @return {@link AlipayTradeCloseResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCloseResponse tradeCloseToResponse(IAliPayConfig aliPayConfig, AlipayTradeCloseModel model) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易创建接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCreateModel}
     * @param notifyUrl    异步通知URL
     * @return {@link AlipayTradeCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCreateResponse tradeCreateToResponse(IAliPayConfig aliPayConfig, AlipayTradeCreateModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易创建接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCreateModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCreateResponse tradeCreateToResponse(IAliPayConfig aliPayConfig, AlipayTradeCreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易退款接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeRefundModel}
     * @return {@link AlipayTradeRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeRefundResponse tradeRefundToResponse(IAliPayConfig aliPayConfig, AlipayTradeRefundModel model) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易退款接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeRefundModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeRefundResponse tradeRefundToResponse(IAliPayConfig aliPayConfig, AlipayTradeRefundModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易退款查询
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeFastpayRefundQueryModel}
     * @return {@link AlipayTradeFastpayRefundQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeFastpayRefundQueryModel model) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易退款查询
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeFastpayRefundQueryModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeFastpayRefundQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeFastpayRefundQueryModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 查询对账单下载地址
     *
     * @param aliPayConfig
     * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
     * @return 对账单下载地址
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String billDownloadurlQuery(IAliPayConfig aliPayConfig, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryResponse response = billDownloadUrlQueryToResponse(aliPayConfig, model);
        return response.getBillDownloadUrl();
    }

    /**
     * 查询对账单下载地址
     *
     * @param aliPayConfig
     * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
     * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(IAliPayConfig aliPayConfig, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 统一收单交易结算接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeOrderSettleModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeOrderSettleResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(IAliPayConfig aliPayConfig, AlipayTradeOrderSettleModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易结算接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeOrderSettleModel}
     * @return {@link AlipayTradeOrderSettleResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(IAliPayConfig aliPayConfig, AlipayTradeOrderSettleModel model) throws AlipayApiException {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 电脑网站支付(PC支付)
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePagePayModel}
     * @param notifyUrl    异步通知URL
     * @param returnUrl    同步通知URL
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String tradePage(IAliPayConfig aliPayConfig, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String form = AliPayUtils.getAliPayClient(aliPayConfig).pageExecute(request).getBody();// 调用SDK生成表单
        return form;
    }

    /**
     * 电脑网站支付(PC支付)
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePagePayModel}
     * @param notifyUrl    异步通知URL
     * @param returnUrl    同步通知URL
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String tradePageByOutputStream(IAliPayConfig aliPayConfig, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String form = AliPayUtils.getAliPayClient(aliPayConfig).pageExecute(request).getBody();// 调用SDK生成表单
        return form;
    }

    /**
     * 资金预授权冻结接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderFreezeModel}
     * @return {@link AlipayFundAuthOrderFreezeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderFreezeResponse authOrderFreezeToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderFreezeModel model) throws AlipayApiException {
        AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 资金授权解冻接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderUnfreezeModel}
     * @return {@link AlipayFundAuthOrderUnfreezeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderUnfreezeResponse authOrderUnfreezeToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderUnfreezeModel model) throws AlipayApiException {
        AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 资金预授权冻结接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderVoucherCreateModel}
     * @return {@link AlipayFundAuthOrderVoucherCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderVoucherCreateResponse authOrderVoucherCreateToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderVoucherCreateModel model) throws AlipayApiException {
        AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 资金授权撤销接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOperationCancelModel}
     * @return {@link AlipayFundAuthOperationCancelResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOperationCancelResponse authOperationCancelToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOperationCancelModel model) throws AlipayApiException {
        AlipayFundAuthOperationCancelRequest request = new AlipayFundAuthOperationCancelRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 资金授权操作查询接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOperationDetailQueryModel}
     * @return {@link AlipayFundAuthOperationDetailQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOperationDetailQueryResponse authOperationDetailQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOperationDetailQueryModel model) throws AlipayApiException {
        AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包无线支付接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderAppPayModel}
     * @return {@link AlipayFundCouponOrderAppPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderAppPayResponse fundCouponOrderAppPayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderAppPayModel model) throws AlipayApiException {
        AlipayFundCouponOrderAppPayRequest request = new AlipayFundCouponOrderAppPayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包页面支付接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderPagePayModel}
     * @return {@link AlipayFundCouponOrderPagePayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderPagePayResponse fundCouponOrderPagePayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderPagePayModel model) throws AlipayApiException {
        AlipayFundCouponOrderPagePayRequest request = new AlipayFundCouponOrderPagePayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包协议支付接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderAgreementPayModel}
     * @return {@link AlipayFundCouponOrderAgreementPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderAgreementPayResponse fundCouponOrderAgreementPayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderAgreementPayModel model) throws AlipayApiException {
        AlipayFundCouponOrderAgreementPayRequest request = new AlipayFundCouponOrderAgreementPayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包打款接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderDisburseModel}
     * @return {@link AlipayFundCouponOrderDisburseResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderDisburseResponse fundCouponOrderDisburseToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderDisburseModel model) throws AlipayApiException {
        AlipayFundCouponOrderDisburseRequest request = new AlipayFundCouponOrderDisburseRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包退回接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderRefundModel}
     * @return {@link AlipayFundCouponOrderRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderRefundResponse fundCouponOrderRefundToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderRefundModel model) throws AlipayApiException {
        AlipayFundCouponOrderRefundRequest request = new AlipayFundCouponOrderRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 红包退回接口
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOperationQueryModel}
     * @return {@link AlipayFundCouponOperationQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOperationQueryResponse fundCouponOperationQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOperationQueryModel model) throws AlipayApiException {
        AlipayFundCouponOperationQueryRequest request = new AlipayFundCouponOperationQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 应用授权 URL 拼装
     *
     * @param aliPayConfig
     * @param appId        应用编号
     * @param redirectUri  回调 URI
     * @return 应用授权 URL
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String getOauth2Url(IAliPayConfig aliPayConfig, String appId, String redirectUri) throws UnsupportedEncodingException {
        return new StringBuffer().append("https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=").append(appId).append("&redirect_uri=").append(URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)).toString();
    }

    /**
     * 使用 app_auth_code 换取 app_auth_token
     *
     * @param aliPayConfig
     * @param model        {@link AlipayOpenAuthTokenAppModel}
     * @return {@link AlipayOpenAuthTokenAppResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayOpenAuthTokenAppResponse openAuthTokenAppToResponse(IAliPayConfig aliPayConfig, AlipayOpenAuthTokenAppModel model) throws AlipayApiException {
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 查询授权信息
     *
     * @param aliPayConfig
     * @param model        {@link AlipayOpenAuthTokenAppQueryModel}
     * @return {@link AlipayOpenAuthTokenAppQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayOpenAuthTokenAppQueryResponse openAuthTokenAppQueryToResponse(IAliPayConfig aliPayConfig, AlipayOpenAuthTokenAppQueryModel model) throws AlipayApiException {
        AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 地铁购票发码
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherGenerateModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherGenerateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherGenerateResponse voucherGenerateToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherGenerateModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherGenerateRequest request = new AlipayCommerceCityfacilitatorVoucherGenerateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 地铁购票发码退款
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherRefundModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherRefundResponse metroRefundToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherRefundModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherRefundRequest request = new AlipayCommerceCityfacilitatorVoucherRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 地铁车站数据查询
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorStationQueryModel}
     * @return {@link AlipayCommerceCityfacilitatorStationQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorStationQueryResponse stationQueryToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorStationQueryModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorStationQueryRequest request = new AlipayCommerceCityfacilitatorStationQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 核销码批量查询
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherBatchqueryModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherBatchqueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherBatchqueryResponse voucherBatchqueryToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherBatchqueryModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherBatchqueryRequest request = new AlipayCommerceCityfacilitatorVoucherBatchqueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * @param aliPayConfig
     * @param params
     * @param privateKey
     * @param signType
     * @return
     * @throws ParseException
     */
    public static String batchTrans(IAliPayConfig aliPayConfig, Map<String, String> params, String privateKey, String signType) throws ParseException {
        params.put("service", "batch_trans_notify");
        params.put("_input_charset", "UTF-8");
        params.put("pay_date", DateUtils.convertDate2String("YYYYMMDD", new Date()));
        Map<String, String> param = AliPayUtils.buildRequestPara(params, privateKey, signType);
        return GATEWAY_NEW.concat(AliPayUtils.createLinkString(param));
    }

    /**
     * 将异步通知的参数转化为Map
     *
     * @param aliPayConfig
     * @param aliPayConfig
     * @param requestParams
     * @return
     */
    public static Map<String, String> toMap(IAliPayConfig aliPayConfig, Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 生活缴费查询账单
     *
     * @param aliPayConfig
     * @param orderType       支付宝订单类型
     * @param merchantOrderNo 业务流水号
     * @return {@link AlipayEbppBillGetResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayEbppBillGetResponse ebppBillGet(IAliPayConfig aliPayConfig, String orderType, String merchantOrderNo) throws AlipayApiException {
        AlipayEbppBillGetRequest request = new AlipayEbppBillGetRequest();
        request.setOrderType(orderType);
        request.setMerchantOrderNo(merchantOrderNo);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * H5刷脸认证初始化
     *
     * @param aliPayConfig
     * @param model        {@link ZolozIdentificationUserWebInitializeModel}
     * @return {@link ZolozIdentificationUserWebInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozIdentificationUserWebInitializeResponse identificationUserWebInitialize(IAliPayConfig aliPayConfig, ZolozIdentificationUserWebInitializeModel model) throws AlipayApiException {
        ZolozIdentificationUserWebInitializeRequest request = new ZolozIdentificationUserWebInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * H5刷脸认证查询
     *
     * @param aliPayConfig
     * @param model        {@link ZolozIdentificationUserWebQueryModel}
     * @return {@link ZolozIdentificationUserWebQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozIdentificationUserWebQueryResponse identificationUserWebInitialize(IAliPayConfig aliPayConfig, ZolozIdentificationUserWebQueryModel model) throws AlipayApiException {
        ZolozIdentificationUserWebQueryRequest request = new ZolozIdentificationUserWebQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 热脸入库
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFacemanageCreateModel}
     * @return {@link ZolozAuthenticationCustomerFacemanageCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFacemanageCreateResponse authenticationCustomerFaceManageCreate(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFacemanageCreateModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFacemanageCreateRequest request = new ZolozAuthenticationCustomerFacemanageCreateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 热脸出库
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFacemanageDeleteModel}
     * @return {@link ZolozAuthenticationCustomerFacemanageDeleteResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFacemanageDeleteResponse authenticationCustomerFaceManageDelete(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFacemanageDeleteModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFacemanageDeleteRequest request = new ZolozAuthenticationCustomerFacemanageDeleteRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 人脸 ftoken 查询消费接口
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFtokenQueryModel}
     * @return {@link ZolozAuthenticationCustomerFtokenQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFtokenQueryResponse authenticationCustomerFTokenQuery(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFtokenQueryModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFtokenQueryRequest request = new ZolozAuthenticationCustomerFtokenQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 人脸初始化刷脸付
     *
     * @param model {@link ZolozAuthenticationSmilepayInitializeModel}
     * @return {@link ZolozAuthenticationSmilepayInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationSmilepayInitializeResponse authenticationSmilePayInitialize(IAliPayConfig aliPayConfig, ZolozAuthenticationSmilepayInitializeModel model) throws AlipayApiException {
        ZolozAuthenticationSmilepayInitializeRequest request = new ZolozAuthenticationSmilepayInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

    /**
     * 人脸初始化唤起zim
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerSmilepayInitializeModel}
     * @return {@link ZolozAuthenticationCustomerSmilepayInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerSmilepayInitializeResponse authenticationCustomerSmilePayInitialize(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerSmilepayInitializeModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerSmilepayInitializeRequest request = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayClient(aliPayConfig).execute(request);
    }

}