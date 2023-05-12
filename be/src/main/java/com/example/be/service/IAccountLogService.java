package com.example.be.service;

import com.example.be.entity.AccountLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
public interface IAccountLogService extends IService<AccountLog> {
    List<String> getByAccountId(Integer accountId);
}
