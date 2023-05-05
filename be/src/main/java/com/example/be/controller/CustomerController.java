package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.common.Status;

import com.example.be.entity.Customer;
import com.example.be.service.IAdService;
import com.example.be.service.ICustomerService;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;



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

    @GetMapping("/id/{id}")
    public R<Customer> getById(@PathVariable Integer id){
        Customer user = userService.getById(id);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @GetMapping("/username/{username}")
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
        if(status == Status.BANNED){
            return R.success("已成功禁用该账号");
        }
        else{
            return R.success("已解封该账号");
        }
    }



}
