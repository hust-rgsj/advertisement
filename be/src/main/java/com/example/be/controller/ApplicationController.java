package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import com.example.be.common.Status;
import com.example.be.dto.Addto;
import com.example.be.entity.Ad;
import com.example.be.entity.Application;
import com.example.be.entity.Display;
import com.example.be.service.IAdService;
import com.example.be.service.IApplicationService;
import com.example.be.service.IDisplayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IAdService adService;

    @Autowired
    private IDisplayService displayService;

    @GetMapping("/pageApp")
    public PageInfo<Application> pageApp(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Application> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg),Application::getName,msg);
        queryWrapper.orderByDesc(Application::getUpdateTime);
        List<Application> list = applicationService.list(queryWrapper);
        PageInfo<Application> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @GetMapping("/set")
    public Ad set(@RequestParam Integer applicationId){
        Ad ad = applicationService.getAdByApplicationId(applicationId);

        return ad;
    }

    @GetMapping("/stop")
    public R<String> stop(@RequestParam Integer applicationId){
        Application application = applicationService.getById(applicationId);
        Ad ad = adService.getById(application.getAdId());
        ad.setStatus(Status.OFF);
        adService.updateById(ad);

        LambdaUpdateWrapper<Application> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Application::getId,applicationId);
        wrapper.set(Application::getAdId,null);
        wrapper.set(Application::getAdTitle,null);
        wrapper.set(Application::getAdUrl,null);
        wrapper.set(Application::getUpdateTime,LocalDateTime.now());
        applicationService.update(wrapper);

        return R.success("广告服务已在"+application.getName() +"app中被终止");
    }

    @GetMapping("/pageAd")
    public PageInfo<Addto> pageAd(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum, @RequestParam(value = "msg", required = true, defaultValue = "")String msg){

        adService.updateStatus();
        PageHelper.startPage(pageNum, 6);

        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotEmpty(msg), Ad::getTitle,msg);
        queryWrapper.eq(Ad::getStatus,Status.ON);
        queryWrapper.orderByDesc(Ad::getUpdateTime);
        List<Ad> listAll = adService.list(queryWrapper);
        PageInfo<Ad> pageInfo = new PageInfo<>(listAll);
        List<Addto> adList = adService.ToDto(listAll);
        PageInfo<Addto> pageInfodto = new PageInfo<>();

        return adService.copyPageInfo(adList,pageInfodto,pageInfo);
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody Application application){
        Integer adId = application.getAdId();

        if (adId != null){
            Ad ad = adService.getById(adId);
            Display display = new Display();
            display.setAdId(adId);
            display.setDisplayCount(0);
            display.setClickCount(0);
            display.setConversionRate("0");
            displayService.save(display);
            application.setUpdateTime(LocalDateTime.now());
            application.setAdTitle(ad.getTitle());
            application.setAdUrl(ad.getUrl());
            applicationService.save(application);
        }
        else{
            application.setUpdateTime(LocalDateTime.now());
            applicationService.save(application);
        }
        return R.success("添加成功");

    }

    @PostMapping("/flush")
    public R<List<Addto>> flush(@RequestParam  Integer[] adIds){
            List<Addto> result = new ArrayList<>();

        for(Integer adId : adIds) {
            Ad ad = adService.getById(adId);
            if (ad.getEndTime().isAfter(LocalDateTime.now())) {
                ad.setStatus(Status.OFF);
                ad.setUpdateTime(LocalDateTime.now());
                adService.updateById(ad);
                LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Ad::getStatus, Status.ON);
                List<Ad> list = adService.list(queryWrapper);
                List<Addto> addto = adService.ToDto(list);
                result.add(addto.get(0));
            } else {
                Addto addto = new Addto();
                addto.setId(ad.getId());
                addto.setTitle(ad.getTitle());
                addto.setDescription(ad.getDescription());
                addto.setStatus(ad.getStatus());
                addto.setUrl(ad.getUrl());
                addto.setStartTime(ad.getStartTime());
                addto.setEndTime(ad.getEndTime());
                result.add(addto);
            }
        }
        return R.success(result);
    }
}
