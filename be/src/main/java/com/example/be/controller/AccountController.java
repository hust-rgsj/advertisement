package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.entity.Account;
import com.example.be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/customer/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/recharge")
    public R<Account> recharge(BigDecimal charge, Integer userId){
        Account account = accountService.getByUserId(userId);
        BigDecimal balance = account.getBalance().add(charge);
        account.setBalance(balance);
        account.setUpdateTime(LocalDateTime.now());
        String log = account.getLog() + "\n+" + charge +"元";
        account.setLog(log);
        accountService.updateById(account);
        return R.success(account);
    }

}
