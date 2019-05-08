package com.project.sell.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVo<T> {

    //错误码
    private Integer code;
    //提示消息
    private String msg;
    //数据内容
    private T data;
}
