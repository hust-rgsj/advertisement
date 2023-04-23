package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.entity.TbCustomer;
import com.example.be.mapper.TbCustomerMapper;
import com.example.be.service.ITbCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
@Service
public class TbCustomerServiceImpl extends ServiceImpl<TbCustomerMapper, TbCustomer> implements ITbCustomerService {

    @Autowired
    private TbCustomerMapper userMapper;

    @Override
    @Transactional
    public TbCustomer getByUsername(String username){
        LambdaQueryWrapper<TbCustomer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbCustomer::getUsername,username);
        TbCustomer user = getOne(queryWrapper);

        return user;
    }

}
