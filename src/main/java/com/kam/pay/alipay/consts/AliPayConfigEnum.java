package com.kam.pay.alipay.consts;/** * @author <a href="mailto:xinput.xx@gmail.com">xinput</a> * @date 2020-10-14 14:53 */public enum AliPayConfigEnum {    /**     * 通信协议，默认 https     */    PROTOCOL("alipay.protocol"),    /**     * 网关域名     * 线上为：openapi.alipay.com     * 沙箱为：openapi.alipaydev.com     */    GATEWAY_HOST("alipay.gateway.host"),    /**     * AppId     */    APPID("alipay.appId"),    /**     * 应用私钥     */    MERCHANT_PRIVATE_KEY("alipay.merchant.private.key"),    /**     * 应用公钥证书文件路径     */    MERCHANT_CERT_PATH("alipay.merchant.cert.path"),    /**     * 支付宝公钥证书文件路径     */    ALIPAY_CERT_PATH("alipay.cert.path"),    /**     * 支付宝根证书文件路径     */    ALIPAY_ROOT_CERT_PATH("alipay.root.cert.path"),    /**     * 异步通知回调地址（可选）     */    NOTIFY_URL("alipay.notifyUrl");    private String key;    AliPayConfigEnum(String key) {        this.key = key;    }    public String getKey() {        return key;    }}