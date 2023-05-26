package com.example.be.service;

import com.example.be.entity.DisplayLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-19
 */
public interface IDisplayLogService extends IService<DisplayLog> {
    void displaycount(Integer adId);

    void clickcount(Integer adId);
}
