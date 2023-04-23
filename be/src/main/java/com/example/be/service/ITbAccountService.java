package com.example.be.service;

import com.example.be.entity.TbAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-21
 */
public interface ITbAccountService extends IService<TbAccount> {

    TbAccount getByUserId(Integer userId);
}
