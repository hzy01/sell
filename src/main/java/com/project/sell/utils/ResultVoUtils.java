package com.project.sell.utils;

import com.project.sell.VO.ResultVo;

public class ResultVoUtils {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        resultVo.setData(object);
        return resultVo;
    }
    public static ResultVo success(){
        return success(null);
    }
    public static ResultVo error(String msg,Integer code){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
