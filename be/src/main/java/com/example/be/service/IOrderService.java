package com.example.be.service;

import com.example.be.entity.Ad;
import com.example.be.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
public interface IOrderService extends IService<Order> {

    void submit(Order order);

    Ad check(Order order);
}