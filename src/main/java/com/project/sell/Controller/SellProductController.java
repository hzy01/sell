package com.project.sell.Controller;

import com.project.sell.Exception.SellException;
import com.project.sell.entity.ProductCategory;
import com.project.sell.entity.ProductInfo;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.enums.ProductStatusEnum;
import com.project.sell.from.ProductForm;
import com.project.sell.service.CategoryService;
import com.project.sell.service.ProductService;
import com.project.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "5") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            log.info("【买家端上架商品】,错误信息={}",e.getMessage());
            map.put("url","/sell/seller/product/list");
            map.put("msg",e.getMessage());
            return new ModelAndView("comment/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ExceptionResultEnum.PRODUCT_ONSALE_SUCCESS.getMsg());
        return new ModelAndView("comment/success",map);
    }
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map){
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            log.info("【买家端下架商品】,错误信息={}",e.getMessage());
            map.put("url","/sell/seller/product/list");
            map.put("msg",e.getMessage());
            return new ModelAndView("comment/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ExceptionResultEnum.PRODUCT_OFFSALE_SUCCESS.getMsg());
        return new ModelAndView("comment/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            try {
                ProductInfo productInfo = productService.findOne(productId);
                map.put("productInfo",productInfo);
            } catch (SellException e) {
                map.put("url","/sell/seller/product/list");
                map.put("msg",e.getMessage());
                return new ModelAndView("comment/error",map);
            }
        }
        //查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("url","/sell/seller/product/index");
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("comment/error",map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            if(!StringUtils.isEmpty(form.getProductId())){//修改
                productInfo = productService.findOne(form.getProductId());
            }else //新增
                form.setProductId(KeyUtils.getUniqueKey());
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("url","/sell/seller/product/index");
            map.put("msg",e.getMessage());
            return new ModelAndView("comment/error",map);
        }
        map.put("url","/sell/seller/product/list");
        map.put("msg",ExceptionResultEnum.PRODUCT_CHANGE_SUCCESS.getMsg());
        return new ModelAndView("comment/success",map);
    }
}
