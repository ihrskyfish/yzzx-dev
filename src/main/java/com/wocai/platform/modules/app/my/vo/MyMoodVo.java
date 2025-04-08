package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 10:16
 **/
@Data
public class MyMoodVo implements Serializable {

    @Excel(name = "每天心情（0：不开心 1：普通 2：开心）", width = 15)
    @ApiModelProperty(value = "每天心情（0：不开心 1：普通 2：开心）")
    private String mood;

//    @Excel(name = "年", width = 15)
//    @ApiModelProperty(value = "年")
//    private Integer year;
//    /**月*/
//    @Excel(name = "月", width = 15)
//    @ApiModelProperty(value = "月")
//    private Integer month;
//    /**日*/
//    @Excel(name = "日", width = 15)
//    @ApiModelProperty(value = "日")
//    private Integer day;
//
//    @ApiModelProperty(value = "年月日")
//    private LocalDate localDate;
}
