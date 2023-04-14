package com.be.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.be.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-04-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
