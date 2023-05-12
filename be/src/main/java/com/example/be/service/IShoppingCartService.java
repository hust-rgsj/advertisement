package com.example.be.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.be.entity.ShoppingCart;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
public interface IShoppingCartService extends IService<ShoppingCart> {

    List<ShoppingCart> selectAll();

    ShoppingCart getCart(Integer adId);
}
