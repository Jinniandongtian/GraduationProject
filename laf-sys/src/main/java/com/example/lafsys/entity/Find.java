package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
 * @since 2024-03-13
 */
@Getter
@Setter
@TableName("sys_find")
@ApiModel(value = "Find对象", description = "")
public class Find implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
      @TableId(value = "find_id", type = IdType.AUTO)
    private Integer findId;

    @ApiModelProperty("物品名称")
    private String findName;

    @ApiModelProperty("标题")
    private String findHeadline;

    @ApiModelProperty("发现时间")
    private LocalDate findTime;

    @ApiModelProperty("发现地点")
    private String findLocation;

    @ApiModelProperty("联系电话")
    private String findPhone;

    @ApiModelProperty("物品描述")
    private String findDescription;

    @ApiModelProperty("发布时间")
    private LocalDate issueTime;

    @ApiModelProperty("图片")
    private String imageUrl;

    @ApiModelProperty("是否认领")
    private Integer isClaim;

    @ApiModelProperty("认领人ID")
    private Integer userId;
}
