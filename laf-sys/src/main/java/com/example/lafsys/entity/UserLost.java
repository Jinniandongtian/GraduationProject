package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sys_user_lost")
public class UserLost {
    private Integer userId;
    private Integer lostId;
}
