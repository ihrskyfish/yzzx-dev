package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 10:21
 **/
@Data
public class MyThreeCircumferencesVo implements Serializable {

    /**胸围cm*/
    @Excel(name = "胸围cm", width = 15)
    @ApiModelProperty(value = "胸围cm")
    private BigDecimal bust;
    /**腰围cm*/
    @Excel(name = "腰围cm", width = 15)
    @ApiModelProperty(value = "腰围cm")
    private BigDecimal waist;
    /**臀围cm*/
    @Excel(name = "臀围cm", width = 15)
    @ApiModelProperty(value = "臀围cm")
    private BigDecimal hip;
//    /**年*/
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
