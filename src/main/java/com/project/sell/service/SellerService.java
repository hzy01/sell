package com.project.sell.service;

import com.project.sell.entity.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);

    SellerInfo save(SellerInfo sellerInfo);
}
