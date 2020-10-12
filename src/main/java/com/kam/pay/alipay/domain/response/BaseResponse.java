package com.kam.pay.alipay.domain.response;

public class BaseResponse {

    /**
     * 商户网站唯一订单号
     */
    private String out_trade_no;

    /**
     * 该交易在支付宝系统中的交易流水号
     */
    private String trade_no;

    /**
     * 商户网站唯一订单号
     */
    private String total_amount;

    /**
     * 收款支付宝账号对应的支付宝唯一用户号
     */
    private String seller_id;

    /**
     * 商户原始订单号
     */
    private String merchant_order_no;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getMerchant_order_no() {
        return merchant_order_no;
    }

    public void setMerchant_order_no(String merchant_order_no) {
        this.merchant_order_no = merchant_order_no;
    }
}
