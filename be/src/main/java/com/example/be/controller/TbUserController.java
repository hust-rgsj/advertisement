package com.example.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
@RestController
@RequestMapping("/user")
public class TbUserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getUsers")
    @ResponseBody
    
    public List<Map<String,Object>> getUsers(){
    String sql="select * from user";//SQL查询语句
    List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
    return list;
    }
}
