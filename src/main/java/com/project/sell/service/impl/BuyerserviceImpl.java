package com.project.sell.service.impl;

import com.project.sell.Exception.SellException;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.service.BuyerService;
import com.project.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Service
@Slf4j
public class BuyerserviceImpl implements BuyerService {

    @Autowired
    public OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid,orderId);
        if(orderDTO==null){
            log.error("【取消订单】该订单不存在，orderId={}",orderId);
            throw new SellException(ExceptionResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ExceptionResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
