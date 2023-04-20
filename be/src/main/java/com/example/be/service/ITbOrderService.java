package com.example.be.service;

import com.example.be.entity.TbAd;
import com.example.be.entity.TbOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
public interface ITbOrderService extends IService<TbOrder> {

    void submit(TbOrder order);

    TbAd check(TbOrder order);
}
