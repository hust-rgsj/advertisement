package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.entity.TbUser;
import com.example.be.service.ITbUserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
@RequestMapping("/user")
public class TbUserController {
    @Autowired
    private ITbUserService userService;

    @PostMapping("/login")
    public R<TbUser> login(HttpServletRequest request, @RequestBody TbUser user){

        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());


        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbUser::getUsername,user.getUsername());
        TbUser u = userService.getOne(queryWrapper);

        if(u == null){
            return R.error("账号不存在，登录失败");
        }

        if(!u.getPassword().equals(password)){
            return R.error("密码错误，登录失败");
        }

        if(u.getStatus() == 0){
            return R.error("账号已被禁用");
        }

        request.getSession().setAttribute("user",u.getId());
        return R.success(u);

    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody TbUser user){
        request.getSession().removeAttribute("user");
        userService.removeById(user);
        return R.success("注销成功");
    }

    @GetMapping("/{id}")
    public R<TbUser> getById(@PathVariable Long id){
        TbUser user = userService.getById(id);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @GetMapping("/{username}")
    public R<TbUser> getByUsername(@PathVariable String username){
        TbUser user = userService.getByUsername(username);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @PutMapping("/update")
    public R<TbUser> update(HttpServletRequest request, @RequestBody TbUser user){
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);

        return R.success(user);
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request,@RequestBody TbUser user){

        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        if(queryWrapper.eq(TbUser::getUsername,user.getUsername()) != null){
            return R.error("该用户已存在");
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userService.save(user);


        return R.success("注册成功");
    }

    @PostMapping("/status")
    public void status(Integer status, @RequestBody TbUser user){
        user.setStatus(status);
        userService.updateById(user);
    }


}
