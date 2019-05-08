package com.project.sell.service.impl;

import com.project.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(2);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(2,3));
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享",5);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryType(){
        ProductCategory productCategory = categoryService.findByCategoryType(6);
        Assert.assertNotNull(productCategory);
    }

    @Test
    @Transactional
    public void update(){
        ProductCategory productCategory = categoryService.findByCategoryType(10);
        categoryService.updateByObject(productCategory);
    }
}