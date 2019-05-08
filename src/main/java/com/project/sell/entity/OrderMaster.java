package com.project.sell.entity;

import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@DynamicUpdate
@Entity
@Proxy(lazy = false)
public class OrderMaster {
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态，默认为0，未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

}
