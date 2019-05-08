package com.project.sell.repository;

import com.project.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    @Test
    public void findByBuyerOpenId() {
        PageRequest pageRequest = PageRequest.of(0,1);
        Page<OrderMaster> result = repository.findByBuyerOpenid("119120",pageRequest);
        System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getSize());
    }
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("张三丰");
        orderMaster.setBuyerPhone("12345678915");
        orderMaster.setBuyerAddress("武当山");
        orderMaster.setBuyerOpenid("119120");
        orderMaster.setOrderAmount(new BigDecimal(10.5));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
}