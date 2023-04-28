package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.entity.Ad;
import com.example.be.service.IAdService;
import com.example.be.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping({"/customer/advertisement","/admin/advertisement"})
public class AdController {

    @Autowired
    private IAdService adService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/add")
    public R<String> add(@RequestBody Ad ad){
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        if(queryWrapper.eq(Ad::getTitle,ad.getTitle()) != null){
            return R.error("该广告已存在");
        }

        ad.setCreateTime(LocalDateTime.now());
        ad.setStatus(Status.EXAMING);
        adService.save(ad);

        return R.success("广告信息添加成功");
    }

    @PostMapping("/update")
    public R<Ad> update(@RequestBody Ad ad){

        ad.setUpdateTime(LocalDateTime.now());
        adService.updateById(ad);

        return R.success(ad);
    }

    @PostMapping("/examine")
    public R<String> examine(Integer status, @RequestParam(value = "msg", required = true, defaultValue = "")String reason, Integer adId){
        Ad ad = adService.getById(adId);
        if(status == Status.PASS){
            ad.setStatus(status);
            adService.updateById(ad);
            return R.success("审核通过");
        }
        if(status == Status.NOT_PASS){
            ad.setStatus(status);
            adService.updateById(ad);
            return R.error("审核不通过，原因为："+reason+",请修改");
        }
        return null;
    }

    @PostMapping("/SetTime")
    public void SetTime(LocalDateTime start, LocalDateTime end, Integer adId){
        Ad ad = adService.getById(adId);
        ad.setStartTime(start);
        ad.setEndTime(end);
        adService.updateById(ad);
    }

    @PostMapping("/SetPrice")
    public void SerPrice (BigDecimal price, Integer adId){
        Ad ad = adService.getById(adId);
        ad.setPrice(price);
        adService.updateById(ad);
    }

    @GetMapping("/stop")
    public R<String> stop(Integer adId){
        Ad ad = adService.getById(adId);
        ad.setStatus(Status.STOP);
        adService.updateById(ad);
        return R.success("广告服务已终止");
    }

    @GetMapping("/updateStatus")
    public void updateStatus(LocalDateTime time) {
        LambdaQueryWrapper<Ad> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Ad::getStatus, Status.ON).ge(Ad::getEndTime, time);
        List<Ad> list1 = adService.list(queryWrapper1);
        for (Ad ad : list1) {
            ad.setStatus(Status.OFF);
            adService.updateById(ad);
        }
        LambdaQueryWrapper<Ad> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Ad::getStatus, Status.PAID).le(Ad::getEndTime, time).ge(Ad::getStartTime, time);
        List<Ad> list2 = adService.list(queryWrapper2);
        for (Ad ad : list2) {
            ad.setStatus(Status.ON);
            adService.updateById(ad);
        }
    }

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws Exception {
        String url = aliOSSUtils.upload(file);
        return R.success(url);
    }



}