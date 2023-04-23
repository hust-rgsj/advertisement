package com.example.be.service;

import com.example.be.entity.TbCustomer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
public interface ITbCustomerService extends IService<TbCustomer> {
    TbCustomer getByUsername(String username);


}
