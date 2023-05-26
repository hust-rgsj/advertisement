package com.example.be.service.impl;

import com.example.be.entity.Display;
import com.example.be.entity.DisplayLog;
import com.example.be.mapper.DisplayLogMapper;
import com.example.be.service.IDisplayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.be.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-19
 */
@Service
public class DisplayLogServiceImpl extends ServiceImpl<DisplayLogMapper, DisplayLog> implements IDisplayLogService {

    @Autowired
    private IDisplayService displayService;

    private Integer clickCount = 0;
    private Integer displayCount = 0;
    @Override
    public void displaycount(Integer adId) {

        DisplayLog displayLog = new DisplayLog();
        displayLog.setAdId(adId);
        displayCount = displayLog.getDisplayCount() + 1;
        displayLog.setDisplayCount(displayCount);
        clickCount = displayLog.getClickCount();
        // 计算并格式化转化率
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        displayLog.setConversionRate(conversionRate);
        //记录时间
        displayLog.setUpdateTime(LocalDateTime.now());
        //保存展示数据
        save(displayLog);

        //display
        Display display = displayService.getByAdId(adId);
        display.setDisplayCount(displayCount);
        display.setConversionRate(conversionRate);
        displayService.updateById(display);

    }

    @Override
    public void clickcount(Integer adId) {
        DisplayLog displayLog = new DisplayLog();
        displayLog.setAdId(adId);
        clickCount = displayLog.getDisplayCount() + 1;
        displayLog.setClickCount(clickCount);
        displayCount = displayLog.getDisplayCount();
        // 计算并格式化转化率
        DecimalFormat df = new DecimalFormat("0.0000");
        String conversionRate = df.format((double)clickCount / (double)displayCount * 100) + "%";
        displayLog.setConversionRate(conversionRate);
        //记录时间
        displayLog.setUpdateTime(LocalDateTime.now());
        //保存展示数据
        save(displayLog);

        //display
        Display display = displayService.getByAdId(adId);
        display.setClickCount(clickCount);
        display.setConversionRate(conversionRate);
        displayService.updateById(display);
    }
}
