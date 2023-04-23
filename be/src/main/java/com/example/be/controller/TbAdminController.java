package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.entity.TbAdmin;
import com.example.be.entity.TbCustomer;
import com.example.be.service.ITbAdminService;
import com.example.be.service.ITbCustomerService;
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
public class TbAdminController {

    @Autowired
    private ITbAdminService managerService;

    @Autowired
    ITbCustomerService userService;


    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("manager");
        return R.success("退出成功");
    }


    @PutMapping("/update")
    public R<TbAdmin> update(HttpServletRequest request, @RequestBody TbAdmin staff){
        staff.setUpdateTime(LocalDateTime.now());
        managerService.updateById(staff);

        return R.success(staff);
    }

    @GetMapping("/page")
    public PageInfo<TbCustomer> page(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 10);
        LambdaQueryWrapper<TbCustomer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), TbCustomer::getName,msg).or().likeRight(StringUtils.isNotEmpty(msg), TbCustomer::getPhone,msg);
        queryWrapper.orderByDesc(TbCustomer::getUpdateTime);
        List<TbCustomer> list = userService.list(queryWrapper);
        PageInfo<TbCustomer> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }


}
