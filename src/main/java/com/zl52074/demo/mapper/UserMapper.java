package com.zl52074.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zl52074.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User queryById(@Param("id") Long Id);
}
