package com.example.gymsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;
@Data

@TableName(value = "sys_member")
public class Member {
    @TableId(value = "member_id",type = IdType.AUTO)
    private Integer memberId;
    @JsonIgnore
    private String memberPassword;

    private String memberName;

    private String memberGender;

    private Integer memberAge;

    private Integer memberHeight;

    private Integer memberWeight;

    private BigInteger memberPhone;

    private Date cardTime;

    private Integer cardClass;

    private Integer cardNextClass;


}
