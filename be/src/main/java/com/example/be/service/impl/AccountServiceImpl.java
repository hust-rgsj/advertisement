package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.entity.Account;
import com.example.be.mapper.AccountMapper;
import com.example.be.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public Account getByUserId(Integer userId) {
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Account::getCustomerId, userId);
        Account account = getOne(queryWrapper);
        return account;
    }
}
