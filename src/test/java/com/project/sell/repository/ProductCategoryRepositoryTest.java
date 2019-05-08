package com.project.sell.repository;

import com.project.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findoneTest(){
        ProductCategory productCategory = repository.getOne(2);
        System.out.println(productCategory.toString());
        Assert.assertNotNull(productCategory);
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory = repository.getOne(2);
        productCategory.setCategoryType(3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByCategoryType(){
        List<Integer> list = Arrays.asList(1,2,3);
        System.out.println(repository.findByCategoryTypeIn(list));
    }

}