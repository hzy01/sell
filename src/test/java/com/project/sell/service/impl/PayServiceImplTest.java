package com.project.sell.service.impl;

import com.project.sell.dto.OrderDTO;
import com.project.sell.service.OrderService;
import com.project.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1543816223224962052");
        payService.create(orderDTO);
    }
    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1555575794141931560");
        payService.refund(orderDTO);
    }
}