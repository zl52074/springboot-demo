package com.zl52074.demo.service;

import com.zl52074.demo.entity.User;

/**
 * @description:
 * @author: zl52074
 * @time: 2023/11/7 3:53
 */
public interface UserService {

    User findUserById(Long id);
}
