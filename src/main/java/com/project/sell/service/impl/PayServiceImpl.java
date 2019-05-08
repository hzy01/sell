package com.project.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.project.sell.Exception.SellException;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.CodeEnum;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.service.OrderService;
import com.project.sell.service.PayService;
import com.project.sell.utils.JsonUtil;
import com.project.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付,payRequest={}",JsonUtil.toJson(payRequest));
         PayResponse payResponse =  bestPayService.pay(payRequest);
        log.info("【微信支付二】发起支付,payResponse={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1:签名校验
        //2:支付的状态
        //3:支付金额
        //4:支付人
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}",payResponse);
        //判断订单是否存在
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO==null){
            log.error("【微信支付】发起支付,订单不存在,orderId={}",payResponse.getOrderId());
            throw new SellException(ExceptionResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信支付】发起支付,订单金额不一致,orderId={},微信支付金额={},系统支付金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ExceptionResultEnum.WXPAY_NOTIFY_MONEY_VALUE_ERROR);
        }
        //修改支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDTO 订单对象
     * @return 退款成功与否
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】,request={}",JsonUtil.toJson(refundRequest));
        RefundResponse response = bestPayService.refund(refundRequest);
        log.info("【微信退款】,response={}",JsonUtil.toJson(response));
        return response;
    }
}
