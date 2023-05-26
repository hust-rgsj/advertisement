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
@RequestMapping("/advertisement/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;




    @PostMapping("/submit")
    public R<Integer> submit(@RequestBody Order order){
        Integer customerId = Math.toIntExact(BaseContext.getCurrentId());
        orderService.submit(order,customerId);

        return R.success(order.getAdId());
    }

    @GetMapping("/check")
    public Ad check(Integer orderId){
        Order order = orderService.getById(orderId);
        return orderService.check(order);
    }

    @GetMapping("/pay")
    public R<Accountdto> pay(Integer orderId){
        Accountdto accountdto = orderService.pay(orderId);
        if (accountdto == null){
            return R.error("余额不足，请充值");
        }
        return R.success(accountdto);
    }

}
