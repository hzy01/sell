package com.project.sell.service.impl;

import com.project.sell.dto.OrderDTO;
import com.project.sell.entity.OrderDetail;
import com.project.sell.enums.OrderStatusEnum;
import com.project.sell.enums.PayStatusEnum;
import com.project.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("猪刚烈");
        orderDTO.setBuyerPhone("13856494568");
        orderDTO.setBuyerAddress("高老庄");
        orderDTO.setBuyerOpenid("123111");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("156548");
        orderDetail.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("12");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        Pageable pageable = PageRequest.of(0,1);
        Page<OrderDTO> result = orderService.findList("123111",pageable);
        Assert.assertNotEquals(0,result.getContent().size());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1543299327392772901");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1543298537455395796");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("154329853745539579");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertNotEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus())  ;
    }
    @Test
    public void list(){
        PageRequest request = PageRequest.of(0,1);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}