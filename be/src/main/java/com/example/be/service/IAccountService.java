package com.example.be.service;

import com.example.be.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
public interface IAccountService extends IService<Account> {

    Account getByUserId(Integer userId);
}
