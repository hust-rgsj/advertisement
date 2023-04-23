package com.example.be.service.impl;

import com.example.be.common.Status;
import com.example.be.entity.TbAd;
import com.example.be.entity.TbOrder;
import com.example.be.mapper.TbOrderMapper;
import com.example.be.service.ITbAdService;
import com.example.be.service.ITbOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements ITbOrderService {

    @Autowired
    private ITbAdService adService;

    @Override
    public void submit(TbOrder order) {
        Integer adId = order.getAdId();
        TbAd ad = adService.getById(adId);
        ad.setStatus(Status.NOT_PAID);
        adService.updateById(ad);
        order.setAmount(ad.getPrice());
        order.setPayTime(LocalDateTime.now());
        this.save(order);
    }

    @Override
    public TbAd check(TbOrder order) {
        Integer adId = order.getAdId();
        TbAd ad = adService.getById(adId);
        return ad;
    }
}
