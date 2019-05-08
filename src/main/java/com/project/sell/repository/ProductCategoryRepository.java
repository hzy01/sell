package com.project.sell.repository;

import com.project.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//对于实体类的接口的设计
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    ProductCategory findByCategoryTypeIs(Integer categoryType);

    @Modifying
    @Query(value = "update product_info set category_type = :newType where category_type = :oldType",nativeQuery = true)
    Integer update(@Param(value = "newType") Integer newType,@Param(value = "oldType") Integer oldType);

    @Modifying
    @Query(value = "update product_category set category_name = '海鲜类' where category_type= :#{#category.categoryType}",nativeQuery = true)
    Integer updateByObject(@Param(value = "category") ProductCategory category);
}
