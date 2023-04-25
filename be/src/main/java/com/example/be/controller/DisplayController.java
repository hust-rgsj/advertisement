package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.Status;
import com.example.be.entity.Ad;
import com.example.be.entity.Display;
import com.example.be.service.IAdService;
import com.example.be.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/customer/advertisement/display")
public class DisplayController {

    @Autowired
    private IAdService adService;

    @Autowired
    private IDisplayService displayService;

    private Integer clickCount = 0;
    private Integer displayCount = 0;

    @GetMapping("/start")
    @Transactional
    public Ad start(LocalDateTime time){
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getStatus, Status.ON).ge(Ad::getStartTime,time).le(Ad::getEndTime,time);
        List<Ad> list = adService.list(queryWrapper);
        Ad ad = list.get(0);
        Integer adId = ad.getId();
        Display display = displayService.getById(adId);
        displayCount = display.getDisplayCount() + 1;
        display.setDisplayCount(displayCount);
        clickCount = display.getClickCount();
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);
        displayService.updateById(display);
        return ad;
    }

    @GetMapping("/update")
    @Transactional
    public void update(Integer adId){
        Display display = displayService.getById(adId);
        clickCount++;
        display.setClickCount(clickCount);
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);
        displayService.updateById(display);
    }



}
