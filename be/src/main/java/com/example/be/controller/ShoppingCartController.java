package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.entity.Ad;
import com.example.be.entity.ShoppingCart;
import com.example.be.service.IShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@Slf4j
@RestController
@RequestMapping("/customer/shoppingCart")
public class ShoppingCartController {


    @Autowired
    IShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestParam Integer adId){
        log.info("添加到购物车");
        //设置用户id，指定当前是哪个用户的购物车数据
        ShoppingCart cartOne = shoppingCartService.getCart(Math.toIntExact(adId));
        return R.success(cartOne);

    }

    @GetMapping()
    public R<List<ShoppingCart>> list(){
        log.info("查看购物车...");
        List<ShoppingCart> list = shoppingCartService.selectAll();
        return R.success(list);
    }

    @DeleteMapping("/clean")
    public R<String> clean(){
        log.info("清空购物车");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCustomerId,BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);
        return R.success("清空购物车成功");
    }
    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam Integer adId){
        LambdaQueryWrapper<ShoppingCart>queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getAdId,adId);
        shoppingCartService.remove(queryWrapper);
        return R.success("删除成功");
    }
}

