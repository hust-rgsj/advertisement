package com.example.be.service;

import com.example.be.entity.Ad;
import com.example.be.entity.Application;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
public interface IApplicationService extends IService<Application> {
    Ad getAdByApplicationId(Integer applicationId);
}
