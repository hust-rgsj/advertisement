package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.entity.Admin;
import com.example.be.entity.Customer;
import com.example.be.service.IAdService;
import com.example.be.service.IAdminService;
import com.example.be.service.ICustomerService;
import com.example.be.utils.AliOSSUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ICustomerService customerService;

    @Autowired
    private IAdService adService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        return R.success("退出成功");
    }


    @PutMapping("/update")
    public R<Admin> update(@RequestBody Admin admin){
        admin.setUpdateTime(LocalDateTime.now());
        adminService.updateById(admin);

        return R.success(admin);
    }

    @GetMapping("/customer/id/{id}")
    public Customer getByCustomerId(@PathVariable Integer id){
        Customer customer = customerService.getById(id);

        return customer;
    }

    @GetMapping("/customer/page")
    public PageInfo<Customer> pageCustomer(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 10);
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Customer::getName,msg).or().likeRight(StringUtils.isNotEmpty(msg), Customer::getPhone,msg);
        queryWrapper.orderByDesc(Customer::getUpdateTime);
        List<Customer> list = customerService.list(queryWrapper);

        PageInfo<Customer> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @GetMapping("/ad/page")
    public R<List<Ad>> getAdByCustomerId(Integer CustomerId){

        List<Ad> adList = adService.getAdByCustomerId(CustomerId);

        return R.success(adList);
    }

    @PostMapping("/customer/status")
    public R<String> status(Integer status, Integer customerId){
        Customer customer = customerService.getById(customerId);
        customer.setStatus(status);
        customerService.updateById(customer);
        if(status == Status.BANNED){
            return R.success("已成功禁用该账号");
        }
        else{
            return R.success("已解封该账号");
        }
    }

    @GetMapping("/page")
    public PageInfo<Ad> pageAd(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "title", required = true, defaultValue = "")String title,@RequestParam(value = "status", required = true, defaultValue = "10")Integer status){

        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(title),Ad::getTitle,title).or().likeRight(Ad::getStatus,status);
        queryWrapper.orderByDesc(Ad::getUpdateTime);
        List<Ad> list = adService.list(queryWrapper);
        PageInfo<Ad> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @PostMapping("/upload")
    public R<String> upload(MultipartFile image) throws Exception {
        String url = aliOSSUtils.upload(image);

        return R.success(url);
    }

}
