package com.project.sell.repository;

import com.project.sell.entity.OrderDetail;
import com.project.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> resultList = repository.findByOrderId("111222");
        Assert.assertNotEquals(0,resultList.size());
    }
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123458");
        orderDetail.setOrderId("333333");
        orderDetail.setProductId("4856698");
        orderDetail.setProductName("小龙虾");
        orderDetail.setProductPrice(new BigDecimal(120));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("http://xxxxx.jpg");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}