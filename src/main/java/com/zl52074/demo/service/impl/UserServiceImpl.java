package com.zl52074.demo.service.impl;

import com.zl52074.demo.entity.User;
import com.zl52074.demo.mapper.UserMapper;
import com.zl52074.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zl52074
 * @time: 2023/11/7 3:53
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        //测试用xml自定义查询
        User user = userMapper.queryById(id);
        log.info(user.toString());
        return userMapper.selectById(id);
    }
}
