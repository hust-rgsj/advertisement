package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.Status;
import com.example.be.entity.Ad;
import com.example.be.entity.Display;
import com.example.be.entity.DisplayStatistics;
import com.example.be.service.IAdService;
import com.example.be.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    //记录点击次数和展示次数
    private Integer clickCount = 0;
    private Integer displayCount = 0;
    /**
     * 开始展示广告，并更新展示数据
     * 
     * @param time 展示广告的时间
     * @return 返回被展示的广告
     */
    @GetMapping("/start")
    @Transactional
    public Ad start(LocalDateTime time){
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getStatus, Status.ON)
                    .ge(Ad::getStartTime,time)
                    .le(Ad::getEndTime,time);
        List<Ad> list = adService.list(queryWrapper);

        //获取第一个有效广告
        Ad ad = list.get(0);
        Integer adId = ad.getId();

        // 更新展示次数和转化率
        Display display = displayService.getById(adId);
        displayCount = display.getDisplayCount() + 1;
        display.setDisplayCount(displayCount);
        clickCount = display.getClickCount();
        // 计算并格式化转化率
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);
        // 更新展示数据
        displayService.updateById(display);
        return ad;
    }
    /**
     * 更新广告的点击数据和转化率
     * 
     * @param adId 广告ID
     */
    @GetMapping("/update")
    @Transactional
    public void update(Integer adId){
        // 根据广告ID获取对应的展示数据
        Display display = displayService.getById(adId);

        clickCount++;
        display.setClickCount(clickCount);

        // 计算并格式化转化率
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        display.setConversionRate(conversionRate);

        // 更新展示数据
        displayService.updateById(display);
    }


    /**
     * 按时间查看点击数据和转化率
     * 返回指定时间范围内的点击数据和转化率统计
     *
     * @param startTime 查询开始时间
     * @param endTime   查询结束时间
     * @return 返回点击数据和转化率统计
     */
    
    @GetMapping("/statistics")
    public Map<LocalDateTime, DisplayStatistics> getStatistics(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        Map<LocalDateTime, DisplayStatistics> statisticsMap = new LinkedHashMap<>();

        LocalDateTime current = startTime;
        while (!current.isAfter(endTime)) {
            // 构建查询条件
            LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Ad::getStatus, Status.ON)
                    .ge(Ad::getStartTime, current)
                    .le(Ad::getEndTime, current);

            // 查询符合条件的广告列表
            List<Ad> list = adService.list(queryWrapper);

            // 遍历广告列表，获取点击数据和转化率
            for (Ad ad : list) {
                Display display = displayService.getById(ad.getId());
                DisplayStatistics statistics = new DisplayStatistics();
                statistics.setClickCount(display.getClickCount());
                statistics.setDisplayCount(display.getDisplayCount());
                statistics.setConversionRate(display.getConversionRate());
                statisticsMap.put(current, statistics);
            }

            // 增加一天的时间间隔
            current = current.plusDays(1);
        }

        return statisticsMap;
    }

    /**
     * 按照广告分类查询统计信息
     *
     * @param category 广告分类
     * @return 返回指定广告分类下的展示数据和转化率统计
     */
    @GetMapping("/statistics/category")
    public Map<LocalDateTime, DisplayStatistics> getCategoryStatistics(@RequestParam String category) {
        Map<LocalDateTime, DisplayStatistics> statisticsMap = new LinkedHashMap<>();

        LocalDateTime current = LocalDateTime.now();

        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getStatus, Status.ON)
                .le(Ad::getStartTime, current)
                .ge(Ad::getEndTime, current);

        List<Ad> list = adService.list(queryWrapper);

        for (Ad ad : list) {
            Display display = displayService.getById(ad.getId());
            DisplayStatistics statistics = new DisplayStatistics();
            statistics.setClickCount(display.getClickCount());
            statistics.setDisplayCount(display.getDisplayCount());
            statistics.setConversionRate(display.getConversionRate());
            statisticsMap.put(current, statistics);
        }

        return statisticsMap;
    }

    /**
     * 按照用户查询广告展示历史
     *
     * @param userId 用户ID
     * @return 返回用户的广告展示历史记录和相关统计信息
     */
    @GetMapping("/history/{userId}")
    public List<Display> getUserDisplayHistory(@PathVariable int userId) {
        LambdaQueryWrapper<Ad> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ad::getStatus, Status.ON);

        List<Ad> list = adService.list(queryWrapper);
        List<Display> displayHistory = new ArrayList<>();

        for (Ad ad : list) {
            Display display = displayService.getById(ad.getId());
            if (display.getId() == userId) {
                displayHistory.add(display);
            }
        }

        return displayHistory;
    }

}
