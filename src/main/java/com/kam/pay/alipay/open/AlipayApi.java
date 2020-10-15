package com.kam.pay.alipay.open;

import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.base.oauth.models.AlipaySystemOauthTokenResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCancelResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCreateResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayApi {

    private static final Logger logger = LoggerFactory.getLogger(AlipayApi.class);

    /**
     * 换取授权访问令牌
     *
     * @author Kam    @date 17:27 2020-09-11
     * @params code 授权码，用户对应用授权后得到
     */
    public static AlipaySystemOauthTokenResponse getToken(String code) {
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = Factory.Base.OAuth().getToken(code);
        } catch (Exception e) {
            logger.error("alipay api request error: Base.OAuth().getToken({}),exception info:{}", code, e);
        }
        return response;
    }

    /**
     * 刷新授权访问令牌
     *
     * @author Kam    @date 17:27 2020-09-11
     * @params code 授权码，用户对应用授权后得到
     */
    public static AlipaySystemOauthTokenResponse refreshToken(String code) {
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = Factory.Base.OAuth().refreshToken(code);
        } catch (Exception e) {
            logger.error("alipay api request error: Base.OAuth().refreshToken({}),exception info:{}", code, e);
        }
        return response;
    }

    /**
     * 创建交易
     *
     * @param outTradeNo  商户订单号，64个字符以内，可包含字母、数字、下划线，需保证在商户端不重复
     * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000],
     * @param buyerId     买家的支付宝唯一用户号（2088开头的16位纯数字）]
     * @param subject     订单标题
     * @author Kam    @date 17:57 2020-09-11
     */
    public static AlipayTradeCreateResponse create(String subject, String outTradeNo, String totalAmount, String buyerId) {
        AlipayTradeCreateResponse response = null;
        try {
            response = Factory.Payment.Common().create(subject, outTradeNo, totalAmount, buyerId);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().create({}, {}, {}, {}),exception info:{}",
                    subject, outTradeNo, totalAmount, buyerId, e);
        }
        return response;
    }

    /**
     * 查询交易
     *
     * @param outTradeNo 交易创建时传入的商户订单号
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradeQueryResponse query(String outTradeNo) {
        AlipayTradeQueryResponse response = null;
        try {
            response = Factory.Payment.Common().query(outTradeNo);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().query({}),exception info:{}",
                    outTradeNo, e);
        }
        return response;
    }

    /**
     * 交易退款
     *
     * @param outTradeNo   交易创建时传入的商户订单号，用户对应用授权后得到
     * @param refundAmount 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数]
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradeRefundResponse refund(String outTradeNo, String refundAmount) {
        AlipayTradeRefundResponse response = null;
        try {
            response = Factory.Payment.Common().refund(outTradeNo, refundAmount);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().refund({}, {}),exception info:{}",
                    outTradeNo, refundAmount, e);
        }
        return response;
    }

    /**
     * 关闭交易
     *
     * @param outTradeNo 交易创建时传入的商户订单号
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradeCloseResponse close(String outTradeNo) {
        AlipayTradeCloseResponse response = null;
        try {
            response = Factory.Payment.Common().close(outTradeNo);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().close({}),exception info:{}",
                    outTradeNo, e);
        }
        return response;
    }

    /**
     * 撤销交易
     *
     * @param outTradeNo 交易创建时传入的商户订单号
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradeCancelResponse cancel(String outTradeNo) {
        AlipayTradeCancelResponse response = null;
        try {
            response = Factory.Payment.Common().cancel(outTradeNo);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().cancel({}),exception info:{}",
                    outTradeNo, e);
        }
        return response;
    }

    /**
     * 交易退款查询
     *
     * @param outTradeNo   交易创建时传入的商户订单号
     * @param outRequestNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradeFastpayRefundQueryResponse queryRefund(String outTradeNo, String outRequestNo) {
        AlipayTradeFastpayRefundQueryResponse response = null;
        try {
            response = Factory.Payment.Common().queryRefund(outTradeNo, outRequestNo);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().queryRefund({},{}),exception info:{}",
                    outTradeNo, outRequestNo, e);
        }
        return response;
    }

    /**
     * 查询对账单下载地址
     *
     * @param billType 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     *                 trade 指商户基于支付宝交易收单的业务账单；
     *                 signcustomer 是指基于商户支付宝余额收入及支出等资金变动的帐务账单,
     * @param billDate 账单时间
     *                 日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；
     *                 月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单]
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayDataDataserviceBillDownloadurlQueryResponse downloadBill(String billType, String billDate) {
        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = Factory.Payment.Common().downloadBill(billType, billDate);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().downloadBill({},{}),exception info:{}",
                    billType, billDate, e);
        }
        return response;
    }

    /**
     * 异步通知验签
     *
     * @param parameters 异步通知中收到的待验签的所有参数
     * @author Kam    @date 17:27 2020-09-11
     */
    public static Boolean verifyNotify(java.util.Map<String, String> parameters) {
        Boolean response = null;
        try {
            response = Factory.Payment.Common().verifyNotify(parameters);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Common().verifyNotify(parameters),exception info:{}",
                    JSONObject.toJSONString(parameters, true), e);
        }
        return response;
    }

    /**
     * 电脑网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     *
     * @param subject     订单标题
     * @param outTradeNo  交易创建时传入的商户订单号
     * @param totalAmount 订单总金额
     * @param returnUrl   支付成功后同步跳转的页面
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradePagePayResponse pagePay(String subject, String outTradeNo, String totalAmount, String returnUrl) {
        AlipayTradePagePayResponse response = null;
        try {
            response = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, returnUrl);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Page().pay({}, {}, {}, {}),exception info:{}",
                    subject, outTradeNo, totalAmount, returnUrl, e);
        }
        return response;
    }

    /**
     * 手机网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     *
     * @param subject     订单标题
     * @param outTradeNo  交易创建时传入的商户订单号
     * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000],
     * @param quitUrl     添加该参数后在h5支付收银台会出现返回按钮，可用于用户付款中途退出并返回到该参数指定的商户
     *                    网站地址。注：该参数对支付宝钱包标准收银台下的跳转不生效。
     * @param returnUrl
     * @author Kam    @date 18:25 2020-09-11
     */
    public static AlipayTradeWapPayResponse WapPay(String subject, String outTradeNo, String totalAmount, String quitUrl, String returnUrl) {
        AlipayTradeWapPayResponse response = null;
        try {
            response = Factory.Payment.Wap().pay(subject, outTradeNo, totalAmount, quitUrl, returnUrl);
        } catch (Exception e) {
            logger.error("alipay api request error: Payment.Wap().pay({}, {}, {}, {},{}),exception info:{}",
                    subject, outTradeNo, totalAmount, quitUrl, returnUrl, e);
        }
        return response;
    }

}
