package com.kam.pay.alipay.open;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import com.alipay.easysdk.payment.common.models.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCancelResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCloseResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeCreateResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePayResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.alipay.easysdk.payment.huabei.models.HuabeiConfig;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.alipay.easysdk.payment.wap.models.AlipayTradeWapPayResponse;
import com.kam.pay.alipay.consts.AliPayConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于easysdk的二次封装
 * 文档见 https://github.com/alipay/alipay-easysdk/blob/master/APIDoc.md
 */
public class PayApi {

    private static final Logger logger = LoggerFactory.getLogger(PayApi.class);

    /**
     * 创建交易
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 商户订单号，64个字符以内，可包含字母、数字、下划线，需保证在商户端不重复
     * @param totalAmount [必填] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param buyerId     [必填] 买家的支付宝唯一用户号（2088开头的16位纯数字）
     * @return
     * @throws Exception
     */
    public static AlipayTradeCreateResponse create(String subject, String outTradeNo, String totalAmount, String buyerId) throws Exception {
        return Factory.Payment.Common().create(subject, outTradeNo, totalAmount, buyerId);

    }

    /**
     * 查询交易
     *
     * @param outTradeNo [必填] 交易创建时传入的商户订单号
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayTradeQueryResponse query(String outTradeNo) throws Exception {
        AlipayTradeQueryResponse response = Factory.Payment.Common().query(outTradeNo);
        return response;
    }

    /**
     * 交易退款
     *
     * @param outTradeNo   [必填] 交易创建时传入的商户订单号
     * @param refundAmount [必填] 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayTradeRefundResponse refund(String outTradeNo, String refundAmount) throws Exception {
        AlipayTradeRefundResponse response = Factory.Payment.Common().refund(outTradeNo, refundAmount);
        return response;
    }

    /**
     * 关闭交易
     *
     * @param outTradeNo [必填] 交易创建时传入的商户订单号
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayTradeCloseResponse close(String outTradeNo) throws Exception {
        AlipayTradeCloseResponse response = Factory.Payment.Common().close(outTradeNo);
        return response;
    }

    /**
     * 撤销交易
     *
     * @param outTradeNo [必填] 交易创建时传入的商户订单号
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayTradeCancelResponse cancel(String outTradeNo) throws Exception {
        AlipayTradeCancelResponse response = Factory.Payment.Common().cancel(outTradeNo);
        return response;
    }

    /**
     * 交易退款查询
     *
     * @param outTradeNo   [必填] 交易创建时传入的商户订单号
     * @param outRequestNo [必填] 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayTradeFastpayRefundQueryResponse queryRefund(String outTradeNo, String outRequestNo) throws Exception {
        AlipayTradeFastpayRefundQueryResponse response = Factory.Payment.Common().queryRefund(outTradeNo, outRequestNo);
        return response;
    }

    /**
     * 查询对账单下载地址
     *
     * @param billTypeEnum {@link AliPayConsts.BillTypeEnum}
     * @param billDate     [必填] 账单时间
     *                     日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；
     *                     月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单]
     * @author Kam
     * @date 17:27 2020-09-11
     */
    public static AlipayDataDataserviceBillDownloadurlQueryResponse downloadBill(AliPayConsts.BillTypeEnum billTypeEnum, String billDate) throws Exception {
        AlipayDataDataserviceBillDownloadurlQueryResponse response =
                Factory.Payment.Common().downloadBill(billTypeEnum.getBillType(), billDate);
        return response;
    }

    /**
     * 异步通知验签
     *
     * @param parameters [必填] 异步通知中收到的待验签的所有参数
     * @author Kam    @date 17:27 2020-09-11
     */
    public static Boolean verifyNotify(java.util.Map<String, String> parameters) throws Exception {
        Boolean response = Factory.Payment.Common().verifyNotify(parameters);
        return response;
    }

    /**
     * 创建花呗分期交易
     *
     * @param subject      [必填] 订单标题
     * @param outTradeNo   [必填] 商户订单号，64个字符以内，可包含字母、数字、下划线，需保证在商户端不重复
     * @param totalAmount  [必填] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param buyerId      [必填] 买家的支付宝唯一用户号（2088开头的16位纯数字）
     * @param huabeiConfig [必填] 花呗交易扩展参数
     * @return
     * @throws Exception
     */
    public static com.alipay.easysdk.payment.huabei.models.AlipayTradeCreateResponse huabeiCreate(String subject, String outTradeNo, String totalAmount, String buyerId, HuabeiConfig huabeiConfig) throws Exception {
        return Factory.Payment.Huabei().create(subject, outTradeNo, totalAmount, buyerId, huabeiConfig);
    }

    /**
     * 当面付交易付款
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 商户订单号，64个字符以内，可包含字母、数字、下划线，需保证在商户端不重复
     * @param totalAmount [必填] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param authCode    [必填] 支付授权码，即买家的付款码数字
     * @return
     * @throws Exception
     */
    public static AlipayTradePayResponse faceToFacePay(String subject, String outTradeNo, String totalAmount, String authCode) throws Exception {
        return Factory.Payment.FaceToFace().pay(subject, outTradeNo, totalAmount, authCode);
    }

    /**
     * 交易预创建，生成正扫二维码
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 交易创建时传入的商户订单号
     * @param totalAmount [必填] 订单总金额
     * @return
     * @throws Exception
     */
    public static AlipayTradePrecreateResponse precreate(String subject, String outTradeNo, String totalAmount) throws Exception {
        return Factory.Payment.FaceToFace().preCreate(subject, outTradeNo, totalAmount);
    }

    /**
     * 电脑网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 交易创建时传入的商户订单号
     * @param totalAmount [必填] 订单总金额
     * @param returnUrl   支付成功后同步跳转的页面
     * @author Kam    @date 17:27 2020-09-11
     */
    public static AlipayTradePagePayResponse pagePay(String subject, String outTradeNo, String totalAmount, String returnUrl) throws Exception {
        AlipayTradePagePayResponse response = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, returnUrl);
        return response;
    }

    /**
     * 手机网站 生成交易表单，渲染后自动跳转支付宝网站引导用户完成支付
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 交易创建时传入的商户订单号
     * @param totalAmount [必填] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param quitUrl     [必填] 添加该参数后在h5支付收银台会出现返回按钮，可用于用户付款中途退出并返回到该参数指定的商户
     *                    网站地址。注：该参数对支付宝钱包标准收银台下的跳转不生效。
     * @param returnUrl
     * @author Kam    @date 18:25 2020-09-11
     */
    public static AlipayTradeWapPayResponse wapPay(String subject, String outTradeNo, String totalAmount, String quitUrl, String returnUrl) throws Exception {
        AlipayTradeWapPayResponse response =
                Factory.Payment.Wap().pay(subject, outTradeNo, totalAmount, quitUrl, returnUrl);
        return response;
    }

    /**
     * 手机APP支付
     *
     * @param subject     [必填] 订单标题
     * @param outTradeNo  [必填] 交易创建时传入的商户订单号
     * @param totalAmount [必填] 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @return
     * @throws Exception
     */
    public static AlipayTradeAppPayResponse appPay(String subject, String outTradeNo, String totalAmount) throws Exception {
        AlipayTradeAppPayResponse response =
                Factory.Payment.App().pay(subject, outTradeNo, totalAmount);
        return response;
    }
}
