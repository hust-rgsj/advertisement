package com.example.be.controller;



import com.example.be.dto.DataRatedto;
import com.example.be.dto.Datadto;

import com.example.be.service.IDisplayService;
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
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/customer/advertisement/display")
public class DisplayController {

    @Autowired
    private IDisplayService displayService;

    @GetMapping("/statistics/{adId}")
    public List<DataRatedto> getStatistics(@PathVariable Integer adId) {
        LocalDateTime time = LocalDateTime.now();
        List<DataRatedto> list = displayService.getdatarate(time,adId);

        return list;
    }


    @GetMapping("/PerHourStastics/{adId}")
    public List<Datadto> getPerHourStatistics (@PathVariable Integer adId){

        LocalDateTime time = LocalDateTime.now();
        List<Datadto> list = displayService.getdata(time, adId);

        return list;
    }
}
