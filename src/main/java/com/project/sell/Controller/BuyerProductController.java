package com.project.sell.Controller;

import com.project.sell.VO.ProductInfoVo;
import com.project.sell.VO.ProductVo;
import com.project.sell.VO.ResultVo;
import com.project.sell.entity.ProductCategory;
import com.project.sell.entity.ProductInfo;
import com.project.sell.service.impl.CategoryServiceImpl;
import com.project.sell.service.impl.ProductServiceImpl;
import com.project.sell.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有上架商品
        List<ProductInfo> infoList = productService.findUpAll();
        //2.查询上架商品类目
        List<Integer> categoryTypeList = infoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.拼接数据
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo:infoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVos(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVoUtils.success(productVoList);
    }
}
