package com.project.sell.service.impl;

import com.project.sell.entity.ProductInfo;
import com.project.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("174d8ae067d211e990271831bf4f0a14");
        Assert.assertEquals("174d8ae067d211e990271831bf4f0a14",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> infoList = productService.findUpAll();
        Assert.assertNotEquals(0,infoList);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> result = productService.findAll(request);
        System.out.println(result.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = productService.findOne("123456");
        productInfo.setProductStatus(1);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotEquals(0,result);
    }
    @Test
    public void onSale(){
        ProductInfo productInfo = productService.onSale("174d8ae067d211e990271831bf4f0a14");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());
    }
    @Test
    public void offSale(){
        ProductInfo productInfo = productService.offSale("174d8ae067d211e990271831bf4f0a14");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}