package com.project.sell.Exception;

import com.project.sell.enums.ExceptionResultEnum;

public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ExceptionResultEnum exceptionResultEnum){
        super(exceptionResultEnum.getMsg());
        this.code=exceptionResultEnum.getCode();
    }
    public SellException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

}
