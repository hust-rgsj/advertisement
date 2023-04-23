package com.example.be.mapper;

import com.example.be.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-04-16
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {

}
