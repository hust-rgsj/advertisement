package com.example.be.service;

import com.example.be.dto.Staticsdto;
import com.example.be.entity.Display;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
public interface IDisplayService extends IService<Display> {

    Display getByAdId(Integer adId);

}
