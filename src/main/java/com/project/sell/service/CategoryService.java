package com.project.sell.service;

import com.project.sell.entity.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    ProductCategory findByCategoryType(Integer categoryType);

    void update(Integer newType,Integer oldType);

    Integer updateByObject(ProductCategory category);
}
