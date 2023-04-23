package com.example.be;

import com.example.be.entity.TbCustomer;
import com.example.be.mapper.TbCustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private TbCustomerMapper userMapper;
    @Test
    public void testlist(){
        List<TbCustomer> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
