package com.example.be.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.be.dto.Staticsdto;
import com.example.be.entity.Display;
import com.example.be.mapper.DisplayMapper;
import com.example.be.service.IDisplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@Service
public class DisplayServiceImpl extends ServiceImpl<DisplayMapper, Display> implements IDisplayService {


    @Override
    public Display getByAdId(Integer adId) {
        LambdaQueryWrapper<Display> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Display::getAdId,adId);
        List<Display> list = list(queryWrapper);

        return list.get(0);
    }
}
