package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 11:42
 **/
@Data
public class MyCollectionVo implements Serializable {

    /**类别名称*/
    @Excel(name = "id", width = 15)
    @ApiModelProperty(value = "id")
    private String id;
    /**类型id*/
    @Excel(name = "类型id", width = 15)
    @ApiModelProperty(value = "类型id")
    private String typeId;
    /**知识标题*/
    @Excel(name = "知识标题", width = 15)
    @ApiModelProperty(value = "知识标题")
    private String title;

    @Excel(name = "知识介绍", width = 15)
    @ApiModelProperty(value = "知识介绍")
    private String exhibit;

    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;
}
