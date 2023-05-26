package com.example.be.controller;


import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.dto.Accountdto;
import com.example.be.entity.Account;
import com.example.be.entity.AccountLog;
import com.example.be.service.IAccountLogService;
import com.example.be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private IAccountLogService accountLogService;

    @PostMapping("/recharge")
    public R<Accountdto> recharge(@RequestParam  BigDecimal charge){
        Integer customerId = Math.toIntExact(BaseContext.getCurrentId());
        Account account = accountService.getByUserId(customerId);
        BigDecimal balance = account.getBalance().add(charge);
        account.setBalance(balance);
        LocalDateTime time = LocalDateTime.now().withNano(0);
        account.setUpdateTime(time);
        accountService.updateById(account);
        AccountLog accountLog = new AccountLog();
        accountLog.setAccountId(account.getId());
        accountLog.setUpdateTime(time);
        String log = time + "充值了" + charge + "元," + "当前余额为:" + balance + "元";
        accountLog.setLog(log);
        accountLogService.save(accountLog);

        Accountdto accountdto = new Accountdto();
        accountdto.setBalance(balance);
        accountdto.setLog(log);
        return R.success(accountdto);
    }

}
