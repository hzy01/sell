package com.project.sell.Controller;

import com.project.sell.Exception.SellException;
import com.project.sell.entity.ProductCategory;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.from.CategoryForm;
import com.project.sell.from.ProductForm;
import com.project.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }
    @GetMapping("/index")
    public ModelAndView index(@RequestParam( value = "categoryId",required = false) Integer categoryId, Map<String, Object> map){
        if(!StringUtils.isEmpty(categoryId)){
            //查询数据
            try {
                ProductCategory category = categoryService.findOne(categoryId);
                map.put("category",category);
            } catch (SellException e) {
                map.put("url","/sell/seller/category/list");
                map.put("msg",e.getMessage());
                return new ModelAndView("comment/error",map);
            }
        }
        return new ModelAndView("category/index",map);
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult, Map<String, Object> map){
        if(bindingResult.hasErrors()){
            map.put("url","/sell/seller/category/index");
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("comment/error",map);
        }
        ProductCategory category = new ProductCategory();
        if(!StringUtils.isEmpty(form.getCategoryId())){//修改
            category = categoryService.findOne(form.getCategoryId());
            //修改商品信息表中的类别
            categoryService.update(form.getCategoryType(),category.getCategoryType());
        }
        BeanUtils.copyProperties(form,category);
        try {
            categoryService.save(category);
        } catch (Exception e) {
            map.put("url","/sell/seller/category/index");
            map.put("msg",ExceptionResultEnum.PRODUCT_TYPE_EXIST.getMsg());
            return new ModelAndView("comment/error",map);
        }
        map.put("url","/sell/seller/category/index");
        map.put("msg","成功");
        return new ModelAndView("comment/success",map);
    }
}
