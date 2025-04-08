package com.wocai.platform.modules.app.nurse.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 16:08
 **/
@Data
public class AppNurseDetailsDto implements Serializable {

    @ApiModelProperty(value = "类型id(查询推荐类的不需要填id)")
    private String id;

    @Excel(name = "是否推荐(0:否 1:0)", width = 15)
    @ApiModelProperty(value = "是否推荐(0:否 1:0)")
    private String recommend;

}
