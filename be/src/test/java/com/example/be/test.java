package com.example.be;

import com.example.be.entity.Customer;
import com.example.be.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class test {

    @Autowired
    private CustomerMapper userMapper;
    @Test
    public void testlist(){
        List<Customer> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
