package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author liang
 * @since 2024-03-10
 */
@Getter
@Setter
@TableName("sys_lost")
@ApiModel(value = "Lost对象", description = "")
public class Lost implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
      @TableId(value = "lost_id", type = IdType.AUTO)
    private Integer lostId;

    @ApiModelProperty("标题")
    private String lostHeadline;

    @ApiModelProperty("失物名称")
    private String lostName;

    @ApiModelProperty("丢失时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate lostTime;

    @ApiModelProperty("丢失地点")
    private String lostLocation;

    @ApiModelProperty("物品描述")
    private String lostDescription;

    @ApiModelProperty("报酬")
    private Integer reward;

    @ApiModelProperty("联系电话")
    private String lostPhone;

    @ApiModelProperty("发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate publishTime;

    @ApiModelProperty("图片")
    private String avatarUrl;

    @ApiModelProperty("是否归还")
    private Integer isReturn;

    @ApiModelProperty("归还人ID")
    private Integer userId;


}
