package com.project.sell.Controller;

import com.lly835.bestpay.model.PayResponse;
import com.project.sell.Exception.SellException;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.service.OrderService;
import com.project.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId, @RequestParam("returnUrl") String returnUrl, Map<String,Object> map){
        //1:查询订单是否存在
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO==null){
            throw  new SellException(ExceptionResultEnum.ORDER_NOT_EXIST);
        }
        //2：发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }

}
