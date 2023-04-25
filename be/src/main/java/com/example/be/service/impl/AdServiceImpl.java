package com.example.be.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.be.entity.Ad;
import com.example.be.mapper.AdMapper;
import com.example.be.service.IAdService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-04-17
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

}
