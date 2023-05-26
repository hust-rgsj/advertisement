package com.example.be.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.common.Status;
import com.example.be.dto.DataRatedto;
import com.example.be.dto.Datadto;
import com.example.be.entity.Ad;
import com.example.be.entity.Display;
import com.example.be.entity.DisplayLog;
import com.example.be.service.IAdService;
import com.example.be.service.IDisplayLogService;
import com.example.be.service.IDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.Duration;
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

    private IDisplayLogService displayLogService;

    @GetMapping("/statistics/{adId}")
    public List<DataRatedto> getStatistics(@PathVariable Integer adId) {
        LocalDateTime time = LocalDateTime.now();
        Ad ad = adService.getById(adId);

        Display display = displayService.getByAdId(adId);
        Integer displayAll = display.getDisplayCount();
        Integer clickAll = display.getClickCount();
        String rateAll = display.getConversionRate();

        if (time.minusDays(1).isBefore(ad.getStartTime())) {
            DataRatedto day = new DataRatedto();
            DataRatedto week = new DataRatedto();
            DataRatedto month = new DataRatedto();
            day.setDisplay(displayAll);
            day.setClick(clickAll);
            day.setRate(rateAll);
            week.setDisplay(displayAll);
            week.setClick(clickAll);
            week.setRate(rateAll);
            month.setDisplay(displayAll);
            month.setClick(clickAll);
            month.setRate(rateAll);
            List<DataRatedto> list = new ArrayList<>();
            list.add(day);
            list.add(week);
            list.add(month);
            return list;
        } else {
            if (time.minusDays(7).isBefore(ad.getStartTime())) {
                LambdaQueryWrapper<DisplayLog> queryWrapperDay = new LambdaQueryWrapper<>();
                queryWrapperDay.eq(DisplayLog::getAdId, adId);
                queryWrapperDay.eq(DisplayLog::getUpdateTime, time.minusDays(1));
                List<DisplayLog> listDay = displayLogService.list(queryWrapperDay);
                DisplayLog displayLogDay = listDay.get(0);
                int displayDay = displayAll - displayLogDay.getDisplayCount();
                int clickDay = clickAll - displayLogDay.getClickCount();
                DecimalFormat df = new DecimalFormat("0.0000");
                String rateDay = df.format((double) clickDay / (double) displayDay * 100) + "%";

                DataRatedto day = new DataRatedto();
                DataRatedto week = new DataRatedto();
                DataRatedto month = new DataRatedto();
                day.setDisplay(displayDay);
                day.setClick(clickDay);
                day.setRate(rateDay);
                week.setDisplay(displayAll);
                week.setClick(clickAll);
                week.setRate(rateAll);
                month.setDisplay(displayAll);
                month.setClick(clickAll);
                month.setRate(rateAll);
                List<DataRatedto> list = new ArrayList<>();
                list.add(day);
                list.add(week);
                list.add(month);
                return list;
            } else {
                if (time.minusMonths(1).isBefore(ad.getStartTime())) {

                    LambdaQueryWrapper<DisplayLog> queryWrapperDay = new LambdaQueryWrapper<>();
                    queryWrapperDay.eq(DisplayLog::getAdId, adId);
                    queryWrapperDay.eq(DisplayLog::getUpdateTime, time.minusDays(1));
                    List<DisplayLog> listDay = displayLogService.list(queryWrapperDay);
                    DisplayLog displayLogDay = listDay.get(0);
                    int displayDay = displayAll - displayLogDay.getDisplayCount();
                    int clickDay = clickAll - displayLogDay.getClickCount();
                    DecimalFormat df1 = new DecimalFormat("0.0000");
                    String rateDay = df1.format((double) clickDay / (double) displayDay * 100) + "%";

                    LambdaQueryWrapper<DisplayLog> queryWrapperWeek = new LambdaQueryWrapper<>();
                    queryWrapperWeek.eq(DisplayLog::getAdId, adId);
                    queryWrapperWeek.eq(DisplayLog::getUpdateTime, time.minusDays(7));
                    List<DisplayLog> displayLogWeek = displayLogService.list(queryWrapperWeek);
                    DisplayLog listWeek = displayLogWeek.get(0);
                    int displayWeek = displayAll - listWeek.getDisplayCount();
                    int clickWeek = clickAll - listWeek.getClickCount();
                    DecimalFormat df2 = new DecimalFormat("0.0000");
                    String rateWeek = df2.format((double) clickWeek / (double) displayWeek * 100) + "%";

                    DataRatedto day = new DataRatedto();
                    DataRatedto week = new DataRatedto();
                    DataRatedto month = new DataRatedto();
                    day.setDisplay(displayDay);
                    day.setClick(clickDay);
                    day.setRate(rateDay);
                    week.setDisplay(displayWeek);
                    week.setClick(clickWeek);
                    week.setRate(rateWeek);
                    month.setDisplay(displayAll);
                    month.setClick(clickAll);
                    month.setRate(rateAll);
                    List<DataRatedto> list = new ArrayList<>();
                    list.add(day);
                    list.add(week);
                    list.add(month);

                    return list;
                } else {
                    LambdaQueryWrapper<DisplayLog> queryWrapperDay = new LambdaQueryWrapper<>();
                    queryWrapperDay.eq(DisplayLog::getAdId, adId);
                    queryWrapperDay.eq(DisplayLog::getUpdateTime, time.minusDays(1));
                    List<DisplayLog> listDay = displayLogService.list(queryWrapperDay);
                    DisplayLog displayLogDay = listDay.get(0);
                    int displayDay = displayAll - displayLogDay.getDisplayCount();
                    int clickDay = clickAll - displayLogDay.getClickCount();
                    DecimalFormat df1 = new DecimalFormat("0.0000");
                    String rateDay = df1.format((double) clickDay / (double) displayDay * 100) + "%";


                    LambdaQueryWrapper<DisplayLog> queryWrapperWeek = new LambdaQueryWrapper<>();
                    queryWrapperWeek.eq(DisplayLog::getAdId, adId);
                    queryWrapperWeek.eq(DisplayLog::getUpdateTime, time.minusDays(7));
                    List<DisplayLog> listWeek = displayLogService.list(queryWrapperWeek);
                    DisplayLog displayLogWeek = listWeek.get(0);
                    int displayWeek = displayAll - displayLogWeek.getDisplayCount();
                    int clickWeek = clickAll - displayLogWeek.getClickCount();
                    DecimalFormat df2 = new DecimalFormat("0.0000");
                    String rateWeek = df2.format((double) clickWeek / (double) displayWeek * 100) + "%";

                    LambdaQueryWrapper<DisplayLog> queryWrapperMonth = new LambdaQueryWrapper<>();
                    queryWrapperMonth.eq(DisplayLog::getAdId, adId);
                    queryWrapperMonth.eq(DisplayLog::getUpdateTime, time.minusDays(7));
                    List<DisplayLog> listMonth = displayLogService.list(queryWrapperMonth);
                    DisplayLog displayLogMonth = listMonth.get(0);
                    Integer displayMonth = displayAll - displayLogMonth.getDisplayCount();
                    Integer clickMonth = clickAll - displayLogMonth.getClickCount();
                    DecimalFormat df3 = new DecimalFormat("0.0000");
                    String rateMonth = df3.format((double) clickWeek / (double) displayWeek * 100) + "%";

                    DataRatedto day = new DataRatedto();
                    DataRatedto week = new DataRatedto();
                    DataRatedto month = new DataRatedto();
                    day.setDisplay(displayDay);
                    day.setClick(clickDay);
                    day.setRate(rateDay);
                    week.setDisplay(displayWeek);
                    week.setClick(clickWeek);
                    week.setRate(rateWeek);
                    month.setDisplay(displayMonth);
                    month.setClick(clickMonth);
                    month.setRate(rateMonth);
                    List<DataRatedto> list = new ArrayList<>();
                    list.add(day);
                    list.add(week);
                    list.add(month);

                    return list;
                }
            }
        }
    }

        @GetMapping("/PerHourStastics/{adId}")
        public List<Datadto> getPerHourStastics (@PathVariable Integer adId){

            LocalDateTime time = LocalDateTime.now();
            Ad ad = adService.getById(adId);
            Duration dur = Duration.between(time, ad.getStartTime());
            List<Datadto> list = new ArrayList<>();
            int displayPerHour = 0;
            int clickPerHour = 0;

            if (dur.toHours() >= 12) {
                for (int i = 0; i < 12; i++) {
                    LambdaQueryWrapper<DisplayLog> queryWrapperPerHour = new LambdaQueryWrapper<>();
                    queryWrapperPerHour.eq(DisplayLog::getAdId, adId);
                    queryWrapperPerHour.eq(DisplayLog::getUpdateTime, time.minusHours(12).plusHours(i));
                    List<DisplayLog> listPerHour = displayLogService.list(queryWrapperPerHour);
                    DisplayLog displayLogPerHour = listPerHour.get(0);
                    displayPerHour = displayLogPerHour.getDisplayCount() - displayPerHour;
                    clickPerHour = displayLogPerHour.getClickCount() - clickPerHour;

                    Datadto hour = new Datadto();

                    hour.setDisplay(displayPerHour);
                    hour.setClick(clickPerHour);

                    list.add(hour);
                }
                return list;
            } else {
                for (int i = 0; i < dur.toHours(); i++) {
                    LambdaQueryWrapper<DisplayLog> queryWrapperPerHour = new LambdaQueryWrapper<>();
                    queryWrapperPerHour.eq(DisplayLog::getAdId, adId);
                    queryWrapperPerHour.eq(DisplayLog::getUpdateTime, time.minusDays(dur.toHours()).plusHours(i));
                    List<DisplayLog> listPerHour = displayLogService.list(queryWrapperPerHour);
                    DisplayLog displayLogPerHour = listPerHour.get(0);
                    displayPerHour = displayLogPerHour.getDisplayCount() - displayPerHour;
                    clickPerHour = displayLogPerHour.getClickCount() - clickPerHour;

                    Datadto hour = new Datadto();

                    hour.setDisplay(displayPerHour);
                    hour.setClick(clickPerHour);

                    list.add(hour);
                }
                return list;
            }
        }
}
