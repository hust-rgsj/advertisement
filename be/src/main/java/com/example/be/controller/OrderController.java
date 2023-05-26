package com.example.be.controller;


import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.dto.Accountdto;
import com.example.be.entity.*;
import com.example.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/customer/advertisement/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(Integer adId){
        Integer customerId = Math.toIntExact(BaseContext.getCurrentId());
        Integer orderId = orderService.submit(adId,customerId);

        return R.success("订单id为：" + orderId);
    }

    @GetMapping("/check")
    public Ad check(Integer orderId){
        Order order = orderService.getById(orderId);
        return orderService.check(order);
    }

    @GetMapping("/pay")
    public R<Accountdto> pay(Integer orderId){
        Accountdto accountdto = orderService.pay(orderId);

        return R.success(accountdto);
    }

}
