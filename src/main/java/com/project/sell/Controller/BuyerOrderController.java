package com.project.sell.Controller;

import com.project.sell.Exception.SellException;
import com.project.sell.VO.ResultVo;
import com.project.sell.converter.OrderFrom2OrderDTOConverter;
import com.project.sell.dto.OrderDTO;
import com.project.sell.enums.ExceptionResultEnum;
import com.project.sell.from.OrderFrom;
import com.project.sell.service.BuyerService;
import com.project.sell.service.OrderService;
import com.project.sell.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderFrom orderFrom,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderFrom={}",orderFrom);
            throw new SellException(ExceptionResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFrom2OrderDTOConverter.conver(orderFrom);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ExceptionResultEnum.CART_NOT_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtils.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                            @RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【订单列表】参数,openid不能为空");
            System.out.println("list方法，参数错误");
            throw new SellException(ExceptionResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,pageRequest);
        return ResultVoUtils.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVo<List<OrderDTO>> detail(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            log.error("【订单列表】参数不能为空");
            System.out.println("参数错误");
            throw new SellException(ExceptionResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderId);
        return ResultVoUtils.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        if(StringUtils.isEmpty(openid)||StringUtils.isEmpty(orderId)){
            log.error("【订单列表】参数不能为空");
            throw new SellException(ExceptionResultEnum.PARAM_ERROR);
        }
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtils.success();
    }

}
