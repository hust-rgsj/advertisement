package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.entity.Account;
import com.example.be.entity.Ad;
import com.example.be.entity.Order;
import com.example.be.entity.Customer;
import com.example.be.service.IAccountService;
import com.example.be.service.IAdService;
import com.example.be.service.IOrderService;
import com.example.be.service.ICustomerService;
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

    @Autowired
    private IAdService adService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Order order){
        orderService.submit(order);
        Integer userId = order.getCustomerId();
        Customer user = customerService.getById(userId);
        Integer adCount = user.getAdCount() + 1;
        user.setAdCount(adCount);
        customerService.updateById(user);
        return R.success("订单提交成功,请支付");
    }

    @GetMapping("/check")
    public Ad check(Integer orderId){
        Order order = orderService.getById(orderId);
        return orderService.check(order);
    }

    @GetMapping("/pay")
    public R<String> pay(Integer orderId){
        Order order = orderService.getById(orderId);
        Integer adId = order.getAdId();
        Ad ad = adService.getById(adId);
        Integer userId = order.getCustomerId();
        Account account = accountService.getByUserId(userId);
        BigDecimal balance = account.getBalance().subtract(order.getAmount());
        if (balance.compareTo(new BigDecimal(0)) == -1){
            return R.error("账户余额不足，请充值");
        }
        account.setBalance(balance);
        String log = account.getLog() + "\n-" + order.getAmount() + "元";
        account.setLog(log);
        accountService.updateById(account);
        ad.setStatus(Status.PAID);
        adService.updateById(ad);
        return R.success("支付成功");
    }

    @GetMapping("/recharge")
    public  R<BigDecimal> recharger(Integer accountId, BigDecimal amount){
        Account account = accountService.getByUserId(accountId);
        BigDecimal balance = account.getBalance().add(amount);
        account.setBalance(balance);
        return R.success(balance);
    }

}
