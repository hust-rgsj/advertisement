package com.example.be.service;

import com.example.be.dto.Accountdto;
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

    Integer submit(Integer adId,Integer customerId);

    Ad check(Order order);

    Accountdto pay (Integer orderId);
}
