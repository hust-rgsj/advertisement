package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.entity.TbManager;
import com.example.be.entity.TbUser;
import com.example.be.service.ITbManagerService;
import com.example.be.service.ITbUserService;
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
 * @since 2023-04-17
 */
@RestController
@RequestMapping("/staff")
public class TbManagerController {

    @Autowired
    private ITbManagerService staffService;

    @Autowired ITbUserService userService;


    @PostMapping("/login")
    public R<TbManager> login(HttpServletRequest request, @RequestBody TbManager staff){

        String password = staff.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());


        LambdaQueryWrapper<TbManager> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbManager::getUsername,staff.getUsername());
        TbManager s = staffService.getOne(queryWrapper);

        if(s == null){
            return R.error("账号不存在，登录失败");
        }

        if(!s.getPassword().equals(password)){
            return R.error("密码错误，登录失败");
        }

        request.getSession().setAttribute("staff",s.getId());
        return R.success(s);

    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("staff");
        return R.success("退出成功");
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody TbManager staff){

        LambdaQueryWrapper<TbManager> queryWrapper = new LambdaQueryWrapper<>();
        if(queryWrapper.eq(TbManager::getUsername,staff.getUsername()) != null){
            return R.error("该用户已存在");
        }

        staff.setCreateTime(LocalDateTime.now());
        staff.setUpdateTime(LocalDateTime.now());

        staffService.save(staff);

        return R.success("注册成功");
    }

    @PostMapping("/remove")
    public R<String> remove(HttpServletRequest request, @RequestBody TbManager staff){
        request.getSession().removeAttribute("staff");
        userService.removeById(staff);
        return R.success("注销成功");
    }


    @PutMapping("/update")
    public R<TbManager> update(HttpServletRequest request, @RequestBody TbManager staff){
        staff.setUpdateTime(LocalDateTime.now());
        staffService.updateById(staff);

        return R.success(staff);
    }

    @GetMapping("/page")
    public PageInfo<TbUser> page(@RequestParam(value = "msg", required = true, defaultValue = "null")int pageNum,String msg){

        PageHelper.startPage(pageNum, 10);
        LambdaQueryWrapper<TbUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg),TbUser::getName,msg).or().likeRight(StringUtils.isNotEmpty(msg),TbUser::getPhone,msg);
        queryWrapper.orderByDesc(TbUser::getUpdateTime);
        List<TbUser> list = userService.list(queryWrapper);
        PageInfo<TbUser> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }



}
