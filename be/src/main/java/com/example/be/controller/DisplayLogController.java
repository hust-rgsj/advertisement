package com.example.be.controller;


import com.example.be.service.IDisplayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/customer/advertisement/displaylog")
public class DisplayLogController {

    @Autowired
    private IDisplayLogService displayLogService;


    @GetMapping("/display")
    @Transactional
    public void display(Integer adId){
        displayLogService.displaycount(adId);

    }
    /**
     * 更新广告的点击数据和转化率
     *
     * @param adId 广告ID
     */
    @GetMapping("/click")
    @Transactional
    public void click(Integer adId){
        displayLogService.clickcount(adId);

    }
}
