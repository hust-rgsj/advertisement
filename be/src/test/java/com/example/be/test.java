package com.example.be;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.entity.Customer;
import com.example.be.entity.ShoppingCart;
import com.example.be.mapper.CustomerMapper;
import com.example.be.mapper.ShoppingCartMapper;
import com.example.be.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private CustomerMapper userMapper;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;
    @Test
    public void testlist(){
        List<Customer> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void add(){
       // "添加到购物车"
        Integer adId = 1;
        //设置用户id，指定当前是哪个用户的购物车数据
        ShoppingCart cartOne = shoppingCartService.getCart(Math.toIntExact(adId));
        System.out.println("添加成功");
    }

    @Test
    public void list(){
        //"查看购物车..."
        Integer currentId = 1;
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getCustomerId,currentId);
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        System.out.println(list);
    }

    @Test
    //清空购物车
    public  void clear(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        Integer currentId = 1;
        queryWrapper.eq(ShoppingCart::getCustomerId, currentId);
        shoppingCartService.remove(queryWrapper);
    }
}


