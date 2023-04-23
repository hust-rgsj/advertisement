package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.entity.TbAd;
import com.example.be.entity.TbAdmin;
import com.example.be.entity.TbCustomer;
import com.example.be.service.ITbAdService;
import com.example.be.service.ITbCustomerService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
public class TbCustomerController {
    @Autowired
    private ITbCustomerService userService;

    @Autowired
    private ITbAdService adService;

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody TbCustomer user){
        request.getSession().removeAttribute("user");
        userService.removeById(user);
        return R.success("注销成功");
    }

    @GetMapping("/{id}")
    public R<TbCustomer> getById(@PathVariable Integer id){
        TbCustomer user = userService.getById(id);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @GetMapping("/{username}")
    public R<TbCustomer> getByUsername(@PathVariable String username){
        TbCustomer user = userService.getByUsername(username);
        if(user != null){
            return R.success(user);
        }
        return R.error("该用户不存在");
    }

    @PutMapping("/update")
    public R<TbCustomer> update(HttpServletRequest request, @RequestBody TbCustomer user){
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);

        return R.success(user);
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody TbCustomer user){

        LambdaQueryWrapper<TbCustomer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbCustomer::getUsername,user.getUsername());
        List<TbCustomer> list = userService.list(queryWrapper);
        if(list != null){
            return R.error("该用户已存在");
        }
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(password);
        user.setStatus(Status.RUNNING);
        user.setAccountId(user.getId());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userService.save(user);


        return R.success("注册成功");
    }

    @PostMapping("/status")
    public void status(Integer status, Integer userId){
        TbCustomer user = userService.getById(userId);
        user.setStatus(status);
        userService.updateById(user);
    }

    @PostMapping("/page")
    public PageInfo<TbAd> page(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 6);
        LambdaQueryWrapper<TbAd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), TbAd::getDescription,msg);
        queryWrapper.orderByDesc(TbAd::getUpdateTime);
        List<TbAd> list = adService.list(queryWrapper);
        PageInfo<TbAd> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

}
