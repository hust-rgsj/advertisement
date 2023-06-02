package com.example.be.controller;


import com.example.be.entity.Ad;
import com.example.be.entity.Application;
import com.example.be.service.IAdService;
import com.example.be.service.IApplicationService;
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

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IAdService adService;

    /**
     * 投放广告并更新广告的投放数据和转化率
     * @param applicationId appID
     * @return
     */
    @GetMapping("/display")
    @Transactional
    public String display(Integer applicationId){
        Application application = applicationService.getById(applicationId);
        Integer adId = application.getAdId();
        displayLogService.displaycount(adId);

        Ad ad = adService.getById(adId);
        String url = ad.getUrl();

        return url;
    }
    /**
     * 更新广告的点击数据和转化率
     *
     * @param applicationId appID
     */
    @GetMapping("/click")
    @Transactional
    public void click(Integer applicationId){
        Application application = applicationService.getById(applicationId);
        Integer adId = application.getAdId();
        displayLogService.clickcount(adId);
    }
}
