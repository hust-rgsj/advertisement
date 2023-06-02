package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.dto.Accountdto;
import com.example.be.entity.*;
import com.example.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public R<Integer> submit(@RequestBody Order order){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getId,order.getId());
        List<Order> list = orderService.list(queryWrapper);
        if (list != null){
            orderService.removeById(order);
            orderService.updateById(order);
        }
        Integer adId = order.getAdId();
        Integer customerId = Math.toIntExact(BaseContext.getCurrentId());
        Integer orderId = orderService.submit(adId,customerId);
        return R.success(orderId);
    }

    @GetMapping("/check")
    public Ad check(Integer orderId){
        Order order = orderService.getById(orderId);
        return orderService.check(order);
    }

    @PostMapping("/pay")
    public R<Accountdto> pay(@RequestBody Order order){
        Integer orderId = order.getId();
        Accountdto accountdto = orderService.pay(orderId);
        orderService.removeById(order);
        return R.success(accountdto);
    }

}
