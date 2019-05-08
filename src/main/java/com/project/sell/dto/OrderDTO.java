package com.project.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.sell.entity.OrderDetail;
import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import com.project.sell.utils.EnumUtil;
import com.project.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@DynamicUpdate
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)过时了的方法
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDTO {

    private String orderId;
    //买家姓名
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //商品总金额
    private BigDecimal orderAmount;
    //订单状态，默认为0，新订单
    private Integer orderStatus ;
    //支付状态，默认为0，未支付
    private Integer payStatus;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //修改时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore//忽略该字段转换对象
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
