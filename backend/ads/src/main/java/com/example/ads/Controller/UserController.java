package com.example.ads.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ResponseBody
    /*
    * List 里的对象是Map对象，而Map对象是
    * 由一个String类型的键和Object类型的值组成
    * */
    public List<Map<String,Object>> getUsers(){
    String sql="select * from user";//SQL查询语句
    List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
    return list;
    }
}

