package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.entity.Ad;
import com.example.be.entity.Customer;
import com.example.be.service.IAdService;
import com.example.be.service.ICustomerService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
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
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService userService;

    @Autowired
    private IAdService adService;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
         return R.success("退出成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody Customer customer){
        userService.removeById(customer);
        return R.success("注销成功");
    }

    @GetMapping("/{id}")
    public R<Customer> getById(@PathVariable Integer id){
        Customer user = userService.getById(id);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @GetMapping("/{username}")
    public R<Customer> getByUsername(@PathVariable String username){
        Customer user = userService.getByUsername(username);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @PutMapping("/update")
    public R<Customer> update(HttpServletRequest request, @RequestBody Customer customer){
        customer.setUpdateTime(LocalDateTime.now());
        userService.updateById(customer);

        return R.success(customer);
    }

    @PostMapping("/status")
    public R<String> status(Integer status, Integer customerId){
        Customer customer = userService.getById(customerId);
        customer.setStatus(status);
        userService.updateById(customer);
        return R.success("修改成功");
    }

    @PostMapping("/page")
    public PageInfo<Ad> page(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 6);
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Ad::getDescription,msg);
        queryWrapper.orderByDesc(Ad::getUpdateTime);
        List<Ad> list = adService.list(queryWrapper);
        PageInfo<Ad> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

}
