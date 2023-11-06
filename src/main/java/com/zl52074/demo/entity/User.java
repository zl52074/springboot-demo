package com.zl52074.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: zl52074
 * @time: 2023/11/7 3:53
 */
@Data
@TableName("user")
public class User {
    @TableId
    private Long id;
    private String name;
}
