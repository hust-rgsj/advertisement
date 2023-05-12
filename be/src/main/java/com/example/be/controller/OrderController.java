package com.example.be.controller;


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

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAccountService accountService;


    @PostMapping("/submit")
    public R<String> submit(@RequestBody Order order){
        orderService.submit(order);
        Integer customerId = order.getCustomerId();
        Customer customer = customerService.getById(customerId);
        Integer adCount = customer.getAdCount() + 1;
        customer.setAdCount(adCount);
        customerService.updateById(customer);
        return R.success("订单提交成功,请支付");
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

    @GetMapping("/recharge")
    public  R<BigDecimal> recharge(Integer accountId, BigDecimal amount){
        Account account = accountService.getByUserId(accountId);
        BigDecimal balance = account.getBalance().add(amount);
        account.setBalance(balance);
        return R.success(balance);
    }

}
