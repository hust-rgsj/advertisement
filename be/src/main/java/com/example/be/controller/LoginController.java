package com.example.be.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.common.Type;
import com.example.be.dto.Logindto;
import com.example.be.dto.Userdto;
import com.example.be.entity.Account;
import com.example.be.entity.Admin;
import com.example.be.entity.Customer;
import com.example.be.service.IAccountService;
import com.example.be.service.IAdminService;
import com.example.be.service.ICustomerService;
import com.example.be.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public R<Logindto> login(@RequestBody Userdto user){

//        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("请求登录{},{}",user.getUsername(),user.getPassword());
        LambdaQueryWrapper<Customer> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Customer::getUsername,user.getUsername());
        List<Customer> list1 = customerService.list(queryWrapper1);

        LambdaQueryWrapper<Admin> queryWrapper2= new LambdaQueryWrapper<>();
        queryWrapper2.eq(Admin::getUsername,user.getUsername());
        List<Admin> list2= adminService.list(queryWrapper2);

        if(list1.isEmpty() && list2.isEmpty()){
            return R.error("账号不存在，登录失败");
        }
        else if(!list2.isEmpty()){
            Admin admin = list2.get(0);
            if(!admin.getPassword().equals(user.getPassword())){
                return R.error("密码错误，登录失败");
            }
            admin.setUpdateTime(LocalDateTime.now());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admin.getId());
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            claims.put("type", admin.getType());

            String jwt = JwtUtils.generateJwt(claims);
            Claims decode = JwtUtils.parseJwt(jwt);
//            String s = decode.toString().split(",")[4].split("=")[1];
//            String expire = s.substring(0,s.length() - 1);

            Long expire = decode.getExpiration().getTime() / 1000;

            Logindto result = new Logindto(jwt,admin.getId(),admin.getType(),expire);

            return R.success(result);
        }

        else{
            Customer customer = list1.get(0);
            if(!customer.getPassword().equals(user.getPassword())){
                return R.error("密码错误，登录失败");
            }
            customer.setUpdateTime(LocalDateTime.now());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", customer.getId());
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            claims.put("type", customer.getType());

            String jwt = JwtUtils.generateJwt(claims);
            Claims decode = JwtUtils.parseJwt(jwt);
//            String s = decode.toString().split(",")[4].split("=")[1];
//            String expire = s.substring(0,s.length() - 1);

            Long expire = decode.getExpiration().getTime() / 1000;

            Logindto result = new Logindto(jwt,customer.getId(),customer.getType(),expire);
            return R.success(result);
        }

    }

    @PostMapping("/register")
    public R<String> register(@RequestBody Userdto user){

        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customer::getUsername,user.getUsername());
        List<Customer> list = customerService.list(queryWrapper);
        if(!list.isEmpty()){
            return R.error("该用户已存在");
        }
        log.info("注册{}，{}",user.getUsername(),user.getPassword());
        Customer customer = new Customer();
        Account account = new Account();
        customer.setType(Type.CUSTOMER);
        customer.setUsername(user.getUsername());
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
        customer.setPassword(user.getPassword());
        customer.setStatus(Status.RUNNING);
        customer.setAccountId(customer.getId());
        customer.setAdCount(0);
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerService.save(customer);
        account.setId(customer.getId());
        account.setBalance(BigDecimal.valueOf(0));
        accountService.save(account);

        return R.success("注册成功");
    }

}
