package com.example.be.service;

import com.example.be.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
public interface ITbUserService extends IService<TbUser> {
    TbUser getByUsername(String username);


}
