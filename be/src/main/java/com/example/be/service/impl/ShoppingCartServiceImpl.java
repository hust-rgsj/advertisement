package com.example.be.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.be.common.BaseContext;
import com.example.be.entity.ShoppingCart;
import com.example.be.mapper.ShoppingCartMapper;
import com.example.be.service.IShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

    @Override
    public List<ShoppingCart> selectAll() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCustomerId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        return list(queryWrapper);
    }

    @Override
    public ShoppingCart getCart(Integer adId) {
        //设置用户id，指定当前是哪个用户的购物车数据
        Integer currentId = Math.toIntExact(BaseContext.getCurrentId());
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        ShoppingCart cartOne = getById(adId);
        if(cartOne !=null){
            //如果已经存在则数量加1
            Integer number = cartOne.getAmount();
            cartOne.setAmount(number +1);
            cartOne.setCreateTime(LocalDateTime.now());
        }
        else{
            //如果不存在，则添加到购物车，数量默认就是一
            ShoppingCart shoppingCart =new ShoppingCart();
            shoppingCart.setCustomerId(Math.toIntExact(currentId));
            shoppingCart.setAdId(adId);
            shoppingCart.setAmount(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            save(shoppingCart);
            cartOne =shoppingCart;
        }
        updateById(cartOne);

        return  cartOne;
    }
}