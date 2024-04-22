package com.example.lafsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2024-03-15
 */
@Getter
@Setter
@TableName("sys_check")
@ApiModel(value = "Check对象", description = "")
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
      private Integer id;

    @ApiModelProperty("物品名称")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("执行人ID")
    private Integer userId;

    @ApiModelProperty("类型")
    private String type;


}
