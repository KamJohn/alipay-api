package com.kam.pay.alipay.open;

import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.base.oauth.models.AlipaySystemOauthTokenResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.common.models.*;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.kam.pay.alipay.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayApi {

    private static final Logger logger = LoggerFactory.getLogger(AlipayApi.class);

    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {

        PropertiesUtils propertiesUtil = PropertiesUtils.propertiesUtil;

        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = propertiesUtil.getStringProperty("alipay.app.id");

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "<-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        // config.alipayPublicKey = "<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->";

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }

    /**
     * @description 换取授权访问令牌
     * @author Kam    @date 17:27 2020-09-11
     * @params [code 授权码，用户对应用授权后得到]
     */
    public AlipaySystemOauthTokenResponse getToken(String code) {
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = Factory.Base.OAuth().getToken(code);
        } catch (Exception e) {
            logger.error("alipay api request error: Base.OAuth().getToken({}),exception info:{}", code, e);
        }
        return response;
    }

    /**
     * @description 刷新授权访问令牌
     * @author Kam    @date 17:27 2020-09-11
     * @params [code 授权码，用户对应用授权后得到]
     */
    public AlipaySystemOauthTokenResponse refreshToken(String code) {
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = Factory.Base.OAuth().refreshToken(code);
        } catch (Exception e) {
            logger.error("alipay api request error: Base.OAuth().refreshToken({}),exception info:{}", code, e);
        }
        return response;
    }

    /**
     * @description 创建交易
     * @author Kam    @date 17:57 2020-09-11
     * @params [subject 订单标题, outTradeNo 商户订单号，64个字符以内，可包含字母、数字、下划线，需保证在商户端不重复,
     *                   totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000], buyerId 买家的支付宝唯一用户号（2088开头的16位纯数字）]
     */
    public AlipayTradeCreateResponse create(String subject, String outTradeNo, String totalAmount, String buyerId) {
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
     * @description 创建交易
     * @author Kam    @date 17:27 2020-09-11
     * @params [outTradeNo 交易创建时传入的商户订单号]
     */
    public AlipayTradeQueryResponse query(String outTradeNo) {
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
     * @description 交易退款
     * @author Kam    @date 17:27 2020-09-11
     * @params [outTradeNo 交易创建时传入的商户订单号，用户对应用授权后得到,refundAmount 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数]
     */
    public AlipayTradeRefundResponse refund(String outTradeNo, String refundAmount) {
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
     * @description 关闭交易
     * @author Kam    @date 17:27 2020-09-11
     * @params [outTradeNo 交易创建时传入的商户订单号]
     */
    public AlipayTradeCloseResponse close(String outTradeNo) {
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
     * @description 撤销交易
     * @author Kam    @date 17:27 2020-09-11
     * @params [outTradeNo 交易创建时传入的商户订单号]
     */
    public AlipayTradeCancelResponse cancel(String outTradeNo) {
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
     * @description 交易退款查询
     * @author Kam    @date 17:27 2020-09-11
     * @params [outTradeNo 交易创建时传入的商户订单号,outRequestNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号]
     */
    public AlipayTradeFastpayRefundQueryResponse queryRefund(String outTradeNo, String outRequestNo) {
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
     * @description 查询对账单下载地址
     * @author Kam    @date 17:27 2020-09-11
     * @params [billType 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
     *          trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单,
     *          billDate 账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单]
     */
    public AlipayDataDataserviceBillDownloadurlQueryResponse downloadBill(String billType, String billDate) {
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
     * @description 异步通知验签
     * @author Kam    @date 17:27 2020-09-11
     * @params [parameters 异步通知中收到的待验签的所有参数]
     */
    public Boolean downloadBill(java.util.Map<String, String> parameters) {
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
     * @description 电脑网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     * @author Kam    @date 17:27 2020-09-11
     * @params [subject 订单标题, outTradeNo 交易创建时传入的商户订单号, totalAmount 订单总金额, returnUrl 支付成功后同步跳转的页面]
     */
    public AlipayTradePagePayResponse page(String subject, String outTradeNo, String totalAmount, String returnUrl) {
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
     * @description 手机网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     * @author Kam    @date 18:25 2020-09-11
     * @params [subject 订单标题, outTradeNo 交易创建时传入的商户订单号, totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000],
     *                      quitUrl 用户付款中途退出返回商户网站的地址, 支付成功后同步跳转的页面]
     */
    public AlipayTradeWapPayResponse page(String subject, String outTradeNo, String totalAmount, String quitUrl, String returnUrl) {
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
