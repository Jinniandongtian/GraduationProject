package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Dat 

import java.awt.*;
import java.sql.Date;

@Data
@TableName("sys_user")
public class User {
    private Integer id;

    private String password;

    private String name;

    private String email;

    private String phone;

    private String avatarUrl;

    private String role;

    private Date lastLogin;


}
