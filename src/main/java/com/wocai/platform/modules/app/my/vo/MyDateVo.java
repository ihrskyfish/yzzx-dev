package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 11:09
 **/
@Data
public class MyDateVo implements Serializable {

    @Excel(name = "年", width = 15)
    @ApiModelProperty(value = "年")
    private Integer year;
    /**月*/
    @Excel(name = "月", width = 15)
    @ApiModelProperty(value = "月")
    private Integer month;
    /**日*/
    @Excel(name = "日", width = 15)
    @ApiModelProperty(value = "日")
    private Integer day;

    protected java.util.Date createTime;
}
