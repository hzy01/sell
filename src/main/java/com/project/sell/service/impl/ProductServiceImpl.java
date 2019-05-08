package com.project.sell.service.impl;

import com.project.sell.Exception.SellException;
import com.project.sell.dto.CartDTO;
import com.project.sell.entity.ProductInfo;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.enums.ProductStatusEnum;
import com.project.sell.repository.ProductInfoRepository;
import com.project.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        try {
            return repository.getOne(productId);
        } catch (Exception e) {
            throw new SellException(ExceptionResultEnum.PRODUCT_NOT_EXIST);
        }
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ExceptionResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ExceptionResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ExceptionResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = new ProductInfo();
        try {
             productInfo = repository.getOne(productId);
        } catch (Exception e) {
            throw new SellException(ExceptionResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw  new SellException(ExceptionResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        repository.save(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = repository.getOne(productId);
        } catch (Exception e) {
            throw new SellException(ExceptionResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){
            throw  new SellException(ExceptionResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        repository.save(productInfo);
        return productInfo;
    }
}
