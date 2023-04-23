package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.entity.TbAd;
import com.example.be.service.ITbAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@RequestMapping("/ad")
public class TbAdController {

    @Autowired
    private ITbAdService adService;

    @PostMapping("/add")
    public R<String> add(@RequestBody TbAd ad){
        LambdaQueryWrapper<TbAd> queryWrapper = new LambdaQueryWrapper<>();
        if(queryWrapper.eq(TbAd::getTitle,ad.getTitle()) != null){
            return R.error("该广告已存在");
        }

        ad.setCreateTime(LocalDateTime.now());
        adService.save(ad);

        return R.success("广告信息添加成功");
    }

    @PostMapping("/update")
    public R<TbAd> update(@RequestBody TbAd ad){

        ad.setUpdateTime(LocalDateTime.now());
        adService.updateById(ad);

        return R.success(ad);
    }

    @PostMapping("/examine")
    public R<String> examine(Integer status, @RequestBody TbAd ad){
        if(status == Status.PASS){
            ad.setStatus(status);
            adService.updateById(ad);
            return R.success("审核通过");
        }
        else if(status == Status.EXAMING){
            ad.setStatus(status);
            adService.updateById(ad);
            return R.error("正在审核中，请耐心等待");
        }
        else{
            ad.setStatus(status);
            adService.updateById(ad);
            return R.error("审核不通过，请修改");
        }
    }

    @PostMapping("SetTime")
    public void SetTime(LocalDateTime start, LocalDateTime end, @RequestBody TbAd ad){
        ad.setStartTime(start);
        ad.setEndTime(end);
        adService.updateById(ad);
    }

    @PostMapping("/SetPrice")
    public void SerPrice (BigDecimal price, @RequestBody TbAd ad){
        ad.setPrice(price);
        adService.updateById(ad);
    }

    @PostMapping("/stop")
    public R<String> stop(@RequestBody TbAd ad){
        ad.setStatus(Status.STOP);
        adService.updateById(ad);
        return R.success("广告服务已终止");
    }

    @GetMapping("/updateStatus")
    public void updateStatus(LocalDateTime time) {
        LambdaQueryWrapper<TbAd> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(TbAd::getStatus, Status.ON).ge(TbAd::getEndTime, time);
        List<TbAd> list1 = adService.list(queryWrapper1);
        for (TbAd ad : list1) {
            ad.setStatus(Status.OFF);
            adService.updateById(ad);
        }
        LambdaQueryWrapper<TbAd> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(TbAd::getStatus, Status.PASS).le(TbAd::getEndTime, time).ge(TbAd::getStartTime, time);
        List<TbAd> list2 = adService.list(queryWrapper2);
        for (TbAd ad : list2) {
            ad.setStatus(Status.ON);
            adService.updateById(ad);
        }
    }
}
