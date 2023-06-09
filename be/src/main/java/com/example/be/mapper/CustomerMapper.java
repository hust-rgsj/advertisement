package com.example.be.mapper;

import com.example.be.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

}
