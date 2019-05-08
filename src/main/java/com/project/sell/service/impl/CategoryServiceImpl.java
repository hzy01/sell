package com.project.sell.service.impl;

import com.project.sell.Exception.SellException;
import com.project.sell.entity.ProductCategory;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.repository.ProductCategoryRepository;
import com.project.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        return repository.findByCategoryTypeIs(categoryType);
    }

    @Override
    public ProductCategory findOne(Integer categoryId) {
        try {
            return repository.getOne(categoryId);
        } catch (Exception e) {
            throw new SellException(ExceptionResultEnum.PRODUCT_CATEGORY_ERROR);
        }
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    @Transactional
    public void update(Integer newType,Integer oldType) {
        repository.update(newType,oldType);
    }

    @Override
    public Integer updateByObject(ProductCategory category) {
        return repository.updateByObject(category);
    }
}
