package com.example.be.service.impl;

import com.example.be.common.Status;
import com.example.be.entity.Ad;
import com.example.be.entity.Order;
import com.example.be.mapper.OrderMapper;
import com.example.be.service.IAdService;
import com.example.be.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
