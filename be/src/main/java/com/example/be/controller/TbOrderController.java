package com.example.be.controller;


import com.example.be.common.R;
import com.example.be.entity.TbAd;
import com.example.be.entity.TbOrder;
import com.example.be.service.ITbOrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/order")
public class TbOrderController {

    @Autowired
    private ITbOrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody TbOrder order){
        orderService.submit(order);
        return R.success("订单提交成功");
    }

    @PostMapping("/check")
    public TbAd check(@RequestBody TbOrder order){
        return orderService.check(order);
    }

}
