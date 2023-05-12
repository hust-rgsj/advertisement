package com.example.be.controller;


import com.example.be.common.R;

import com.example.be.entity.Customer;

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
    private ICustomerService customerService;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
         return R.success("退出成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody Customer customer){
        customerService.removeById(customer);
        return R.success("注销成功");
    }

    @GetMapping("/id/{id}")
    public R<Customer> getById(@PathVariable Integer id){
        Customer customer = customerService.getById(id);
        if(customer != null){
            return R.success(customer);
        }

        return R.error("该用户不存在");
    }

    @GetMapping("/username/{username}")
    public R<Customer> getByUsername(@PathVariable String username){
        Customer customer = customerService.getByUsername(username);
        if(customer != null){
            return R.success(customer);
        }
        return R.error("该用户不存在");
    }

    @PutMapping("/update")
    public R<Customer> update(HttpServletRequest request, @RequestBody Customer customer){
        customer.setUpdateTime(LocalDateTime.now());
        customerService.updateById(customer);

        return R.success(customer);
    }


}
