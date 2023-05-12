package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.entity.Ad;
import com.example.be.entity.Application;
import com.example.be.mapper.ApplicationMapper;
import com.example.be.service.IAdService;
import com.example.be.service.IApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

    @Autowired
    private IAdService adService;

    @Override
    public Ad getAdByApplicationId(Integer applicationId){
        Application application  = getById(applicationId);
        Ad ad = adService.getById(application.getAdId());

        return ad;
    }
}
