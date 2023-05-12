package com.example.be.controller;


import com.example.be.common.R;

import com.example.be.entity.Customer;

import com.example.be.service.ICustomerService;


import com.example.be.utils.AliOSSUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
         return R.success("退出成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody Customer customer){
        customerService.removeById(customer);
        return R.success("注销成功");
    }

    @PutMapping("/update")
    public R<Customer> update(@RequestBody Customer customer){
        customer.setUpdateTime(LocalDateTime.now());
        customerService.updateById(customer);

        return R.success(customer);
    }

    @PostMapping("/upload")
    public R<String> upload(MultipartFile image) throws Exception {
        String url = aliOSSUtils.upload(image);

        return R.success(url);
    }

}
