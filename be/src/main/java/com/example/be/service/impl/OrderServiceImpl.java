package com.example.be.service.impl;

import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Accountdto;
import com.example.be.entity.Account;
import com.example.be.entity.AccountLog;
import com.example.be.entity.Ad;
import com.example.be.entity.Order;
import com.example.be.mapper.OrderMapper;
import com.example.be.service.IAccountLogService;
import com.example.be.service.IAccountService;
import com.example.be.service.IAdService;
import com.example.be.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IAdService adService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IAccountLogService accountLogService;

    @Override
    public void submit(Order order) {
        Integer adId = order.getAdId();
        Ad ad = adService.getById(adId);
        ad.setStatus(Status.NOT_PAID);
        adService.updateById(ad);
        order.setAmount(ad.getPrice());
        order.setPayTime(LocalDateTime.now());
        this.save(order);
    }

    @Override
    public Ad check(Order order) {
        Integer adId = order.getAdId();
        Ad ad = adService.getById(adId);
        return ad;
    }

    @Override
    public Accountdto pay(Integer orderId) {
        Order order = getById(orderId);
        Integer adId = order.getAdId();
        Ad ad = adService.getById(adId);
        Integer customerId = order.getCustomerId();
        Account account = accountService.getByUserId(customerId);
        BigDecimal amount = order.getAmount();
        BigDecimal balance = account.getBalance().subtract(amount);
        if (balance.compareTo(new BigDecimal(0)) < 0){
            return null;
        }
        LocalDateTime time = LocalDateTime.now();
        account.setUpdateTime(time);
        account.setBalance(balance);
        accountService.updateById(account);

        AccountLog accountLog = new AccountLog();
        accountLog.setId(account.getId());
        accountLog.setUpdateTime(time);
        String log = time + "支付了" +amount + "元," + "当前余额为:" + balance + "元";
        accountLog.setLog(log);
        accountLogService.updateById(accountLog);

        Accountdto accountdto = new Accountdto();
        accountdto.setBalance(balance);
        List<String> list = accountLogService.getByAccountId(account.getId());
        accountdto.setLog(list);

        ad.setStatus(Status.PAID);
        adService.updateById(ad);

        return accountdto;
    }


}
