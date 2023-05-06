package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.entity.Admin;
import com.example.be.entity.Customer;
import com.example.be.service.IAdminService;
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
 * @since 2023-04-17
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    ICustomerService customerService;


    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return R.success("退出成功");
    }


    @PutMapping("/update")
    public R<Admin> update(HttpServletRequest request, @RequestBody Admin admin){
        admin.setUpdateTime(LocalDateTime.now());
        adminService.updateById(admin);

        return R.success(admin);
    }

    @GetMapping("/page")
    public List<Customer> page(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 10);
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Customer::getName,msg).or().likeRight(StringUtils.isNotEmpty(msg), Customer::getPhone,msg);
        queryWrapper.orderByDesc(Customer::getUpdateTime);
        List<Customer> list = customerService.list(queryWrapper);

        return list;
    }


}
