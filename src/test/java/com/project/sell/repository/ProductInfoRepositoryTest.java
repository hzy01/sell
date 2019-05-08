package com.project.sell.repository;

import com.project.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("156548");
        productInfo.setProductName("金桔柠檬");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("酸酸甜甜好味道");
        productInfo.setProductIcon("https://xxxxx.jpe");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(5);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProcutStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }


}