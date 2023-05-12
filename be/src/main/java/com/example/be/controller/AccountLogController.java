package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.entity.AccountLog;
import com.example.be.service.IAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-05-12
 */
@RestController
@RequestMapping("/log")
public class AccountLogController {

    @Autowired
    private IAccountLogService accountLogService;

    @PostMapping("/check")
    public R<List<String>> check(Integer accountId){
        List<String> list = accountLogService.getByAccountId(accountId);

        return R.success(list);
    }

}
