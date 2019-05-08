package com.project.sell.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderFrom {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "微信openid不能为空")
    private String openid;

    @NotEmpty(message = "商品不能为空")
    private String items;
}
