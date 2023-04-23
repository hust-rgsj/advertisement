package com.example.be;

import com.example.be.entity.TbUser;
import com.example.be.mapper.TbUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private TbUserMapper userMapper;
    @Test
    public void testlist(){
        List<TbUser> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
