package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.entity.TbAccount;
import com.example.be.mapper.TbAccountMapper;
import com.example.be.service.ITbAccountService;
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
public class TbAccountServiceImpl extends ServiceImpl<TbAccountMapper, TbAccount> implements ITbAccountService {

    @Override
    public TbAccount getByUserId(Integer userId) {
        LambdaQueryWrapper<TbAccount> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(TbAccount::getUserId, userId);
        TbAccount account = getOne(queryWrapper);
        return account;
    }
}
