package com.example.be;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.entity.Application;
import com.example.be.entity.Customer;
import com.example.be.entity.ShoppingCart;
import com.example.be.mapper.CustomerMapper;
import com.example.be.mapper.ShoppingCartMapper;
import com.example.be.service.IAdService;
import com.example.be.service.IApplicationService;
import com.example.be.service.impl.ShoppingCartServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private CustomerMapper userMapper;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IAdService adService;

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

    @Test
    //查找app
    public void pageApp(){
        Integer pageNum = 1;
        String msg = "taobao";

        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg),Application::getName,msg);
        queryWrapper.orderByDesc(Application::getUpdateTime);
        List<Application> list = applicationService.list(queryWrapper);
        PageInfo<Application> pageInfo = new PageInfo<>(list);

        System.out.println(pageInfo);
    }

    @Test
    //投放广告设置
    public void set(){
        Integer applicationId = 111;

        Ad ad = applicationService.getAdByApplicationId(applicationId);
        System.out.println(ad);
    }

    @Test
    //终止广告
    public void stop(){
        Integer applicationId = 111;

        Application application = applicationService.getById(applicationId);
        application.setAdId(null);
        application.setAdTitle(null);
        application.setAdUrl(null);
        application.setUpdateTime(LocalDateTime.now());
        applicationService.updateById(application);
        System.out.println("广告服务已在"+application.getName() +"app中被终止");
    }

    @Test
    //查询广告
    public void pageAd(){
        Integer pageNum = 11;
        String msg = "cheese";

        adService.updateStatus();

        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Ad::getTitle,msg);
        queryWrapper.eq(Ad::getStatus, Status.ON);
        queryWrapper.orderByDesc(Ad::getUpdateTime);
        List<Ad> listAll = adService.list(queryWrapper);
        PageInfo<Ad> pageInfo = new PageInfo<>(listAll);
        List<Addto> adList = adService.ToDto(listAll);
        PageInfo<Addto> pageInfodto = new PageInfo<>();

        System.out.println(adService.copyPageInfo(adList,pageInfodto,pageInfo));
    }

    @Test
    //为app添加广告
    public void addAd(){
        Integer applicationId = 23;
        Integer adId = 44;

        Application application = applicationService.getById(applicationId);
        Ad ad = adService.getById(adId);
        application.setUpdateTime(LocalDateTime.now());
        application.setAdId(adId);
        application.setAdTitle(ad.getTitle());
        application.setAdUrl(ad.getUrl());
        applicationService.updateById(application);
        System.out.println(ad);
    }
}


