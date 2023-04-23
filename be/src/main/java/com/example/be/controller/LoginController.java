package com.example.be.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.dto.Logindto;
import com.example.be.entity.TbAdmin;
import com.example.be.entity.TbCustomer;
import com.example.be.service.ITbAdminService;
import com.example.be.service.ITbCustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class LoginController {

    private long min = 30 * 60 * 1000;
    @Autowired
    private ITbCustomerService userService;

    @Autowired
    private ITbAdminService managerService;

    @PostMapping("/login")
    public R<Logindto> login(HttpServletRequest request, String name, String password){

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<TbCustomer> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(TbCustomer::getUsername,name);
        TbCustomer user = userService.getOne(queryWrapper1);

        LambdaQueryWrapper<TbAdmin> queryWrapper2= new LambdaQueryWrapper<>();
        queryWrapper2.eq(TbAdmin::getUsername,name);
        TbAdmin manager = managerService.getOne(queryWrapper2);

        if(user == null && manager == null){
            return R.error("账号不存在，登录失败");
        }
        else if(manager != null){
            if(!manager.getPassword().equals(password)){
                return R.error("密码错误，登录失败");
            }
            manager.setUpdateTime(LocalDateTime.now());
            request.getSession().setAttribute("manager",manager.getId());

            SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
            String time = sdFormatter.format(new Date(new Date().getTime() + min));
            Logindto result = new Logindto("manager", manager.getId(), time);
            return R.success(result,"登录成功");
        }

        else{
            if(!user.getPassword().equals(password)){
                return R.error("密码错误，登录失败");
            }
            user.setUpdateTime(LocalDateTime.now());
            request.getSession().setAttribute("user",user.getId());
            SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
            String time = sdFormatter.format(new Date(new Date().getTime() + min));
            Logindto result = new Logindto("user", user.getId(), time);
            return R.success(result,"登录成功");
        }

    }

}
