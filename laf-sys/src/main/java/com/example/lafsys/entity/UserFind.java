package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_user_find")
public class UserFind {
    private Integer userId;
    private Integer findId;
}
