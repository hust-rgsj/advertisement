package com.example.be.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.common.Type;
import com.example.be.dto.Logindto;
import com.example.be.entity.TbAdmin;
import com.example.be.entity.TbCustomer;
import com.example.be.service.ITbAdminService;
import com.example.be.service.ITbCustomerService;
import com.example.be.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ITbCustomerService customerService;

    @Autowired
    private ITbAdminService adminService;

    @PostMapping("/login")
    public R<Logindto> login(HttpServletRequest request, String username, String password){

//        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("请求登录{},{}",username,password);
        LambdaQueryWrapper<TbCustomer> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(TbCustomer::getUsername,username);
        List<TbCustomer> list1 = customerService.list(queryWrapper1);

        LambdaQueryWrapper<TbAdmin> queryWrapper2= new LambdaQueryWrapper<>();
        queryWrapper2.eq(TbAdmin::getUsername,username);
        List<TbAdmin> list2= adminService.list(queryWrapper2);

        if(list1.isEmpty() && list2.isEmpty()){
            return R.error("账号不存在，登录失败");
        }
        else if(!list2.isEmpty()){
            TbAdmin admin = list2.get(0);
            if(!admin.getPassword().equals(password)){
                return R.error("密码错误，登录失败");
            }
            admin.setUpdateTime(LocalDateTime.now());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", admin.getId());
            claims.put("username", username);
            claims.put("password", password);
            claims.put("type", admin.getType());

            String jwt = JwtUtils.generateJwt(claims);
            Claims decode = JwtUtils.parseJwt(jwt);
//            String s = decode.toString().split(",")[4].split("=")[1];
//            String expire = s.substring(0,s.length() - 1);

            Long expire = decode.getExpiration().getTime() / 1000;

            Logindto result = new Logindto(jwt,admin.getId(),admin.getType(),expire);

            return R.success(result,"登录成功");
        }

        else{
            TbCustomer customer = list1.get(0);
            if(!customer.getPassword().equals(password)){
                return R.error("密码错误，登录失败");
            }
            customer.setUpdateTime(LocalDateTime.now());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", customer.getId());
            claims.put("username", username);
            claims.put("password", password);
            claims.put("type", customer.getType());

            String jwt = JwtUtils.generateJwt(claims);
            Claims decode = JwtUtils.parseJwt(jwt);
//            String s = decode.toString().split(",")[4].split("=")[1];
//            String expire = s.substring(0,s.length() - 1);

            Long expire = decode.getExpiration().getTime() / 1000;

            Logindto result = new Logindto(jwt,customer.getId(),customer.getType(),expire);
            return R.success(result,"登录成功");        }

    }

    @PostMapping("/register")
    public R<String> register(String username,String password){

        LambdaQueryWrapper<TbCustomer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbCustomer::getUsername,username);
        List<TbCustomer> list = customerService.list(queryWrapper);
        if(list != null){
            return R.error("该用户已存在");
        }
        log.info("注册{}，{}",username,password);
        TbCustomer customer = new TbCustomer();
        customer.setType(Type.CUSTOMER);
        customer.setUsername(username);
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
        customer.setPassword(password);
        customer.setStatus(Status.RUNNING);
        customer.setAccountId(customer.getId());
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());

        customerService.save(customer);

        return R.success("注册成功");
    }

}
