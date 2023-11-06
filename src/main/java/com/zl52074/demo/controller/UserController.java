package com.zl52074.demo.controller;

import com.zl52074.demo.entity.User;
import com.zl52074.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zl52074
 * @time: 2023/11/7 3:53
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id){
        return userService.findUserById(id);
    }
}
