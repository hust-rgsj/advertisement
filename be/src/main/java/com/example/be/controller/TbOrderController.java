package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.entity.TbAccount;
import com.example.be.entity.TbAd;
import com.example.be.entity.TbOrder;
import com.example.be.entity.TbCustomer;
import com.example.be.service.ITbAccountService;
import com.example.be.service.ITbAdService;
import com.example.be.service.ITbOrderService;
import com.example.be.service.ITbCustomerService;
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
public class TbOrderController {

    @Autowired
    private ITbOrderService orderService;

    @Autowired
    private ITbCustomerService userService;

    @Autowired
    private ITbAccountService accountService;

    @Autowired
    private ITbAdService adService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody TbOrder order){
        orderService.submit(order);
        Integer userId = order.getCustomerId();
        TbCustomer user = userService.getById(userId);
        Integer adCount = user.getAdCount() + 1;
        user.setAdCount(adCount);
        userService.updateById(user);
        return R.success("订单提交成功,请支付");
    }

    @GetMapping("/check")
    public TbAd check(Integer orderId){
        TbOrder order = orderService.getById(orderId);
        return orderService.check(order);
    }

    @GetMapping("/pay")
    public R<String> pay(Integer orderId){
        TbOrder order = orderService.getById(orderId);
        Integer adId = order.getAdId();
        TbAd ad = adService.getById(adId);
        Integer userId = order.getCustomerId();
        TbAccount account = accountService.getByUserId(userId);
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

}
