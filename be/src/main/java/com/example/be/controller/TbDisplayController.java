package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.Status;
import com.example.be.entity.TbAd;
import com.example.be.entity.TbDisplay;
import com.example.be.service.ITbAdService;
import com.example.be.service.ITbDisplayService;
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
@RequestMapping("/display")
public class TbDisplayController {

    @Autowired
    private ITbAdService adService;

    @Autowired
    private ITbDisplayService displayService;

    private Integer clickCount = 0;
    private Integer displayCount = 0;

    @GetMapping("/start")
    @Transactional
    public TbAd start(LocalDateTime time){
        LambdaQueryWrapper<TbAd> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbAd::getStatus, Status.ON).ge(TbAd::getStartTime,time).le(TbAd::getEndTime,time);
        List<TbAd> list = adService.list(queryWrapper);
        TbAd ad = list.get(0);
        Integer adId = ad.getId();
        TbDisplay display = displayService.getById(adId);
        displayCount = display.getDisplayCount() + 1;
        display.setDisplayCount(displayCount);
        clickCount = display.getClickCount();
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);
        displayService.updateById(display);
        return ad;
    }

    @PostMapping("/update")
    @Transactional
    public void update(@RequestBody TbAd ad){
        Integer adId = ad.getId();
        TbDisplay display = displayService.getById(adId);
        clickCount++;
        display.setClickCount(clickCount);
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);
        displayService.updateById(display);
    }

}
