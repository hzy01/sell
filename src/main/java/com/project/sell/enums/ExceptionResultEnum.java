package com.project.sell.enums;

import lombok.Getter;

@Getter
public enum ExceptionResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数错误"),
    CART_NOT_EMPTY(2,"购物车不能为空"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存错误"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAILS_NOT_EXIST(13,"订单详情不存在"),
    ORDER_FINISH(14,"该订单已完成"),
    ORDER_DETAIL_EMPTY(15,"该订单没有商品"),
    ORDER_PAY_STATUS_ERROR(16,"订单支付状态错误"),
    ORDER_OWNER_ERROR(17,"当前登录用户不一致"),
    WEIXIN_GONGZONGHAO_EROR(18,"微信公众号方面错误"),
    WXPAY_NOTIFY_MONEY_VALUE_ERROR(19,"微信支付金额不一致"),
    ORDER_CANCEL_SUCCESS(20,"订单取消成功"),
    ORDER_FINISH_SUCCESS(21,"订单完结成功"),
    PRODUCT_STATUS_ERROR(22,"商品状态不正确"),
    PRODUCT_ONSALE_SUCCESS(23,"商品上架成功"),
    PRODUCT_OFFSALE_SUCCESS(23,"商品下架成功"),
    PRODUCT_CHANGE_SUCCESS(24,"商品修改成功"),
    PRODUCT_CATEGORY_ERROR(25,"商品类目不存在"),
    PRODUCT_TYPE_SUCCESS(26,"商品类目修改成功"),
    PRODUCT_TYPE_EXIST(27,"商品类目已存在")

    ;
    private Integer code;
    private String msg;

    ExceptionResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
