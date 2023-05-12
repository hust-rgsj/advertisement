package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.entity.Account;
import com.example.be.entity.AccountLog;
import com.example.be.mapper.AccountLogMapper;
import com.example.be.service.IAccountLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@Service
public class AccountLogServiceImpl extends ServiceImpl<AccountLogMapper, AccountLog> implements IAccountLogService {

    @Override
    public List<String> getByAccountId(Integer accountId) {
        LambdaQueryWrapper<AccountLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountLog::getAccountId, accountId);
        queryWrapper.orderByDesc(AccountLog::getUpdateTime);
        List<AccountLog> list = list(queryWrapper);
        List<String> logList = new ArrayList<>();
        for(AccountLog l : list){
            logList.add(l.getLog());
        }
        return logList;
    }
}
