package com.wocai.platform.modules.app.health.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:39
 **/
@Data
public class AppHealthClockDto implements Serializable {

    @Excel(name = "每天心情（0：不开心 1：普通 2：开心）", width = 15)
    @ApiModelProperty(value = "每天心情（0：不开心 1：普通 2：开心）")
    private String mood;
    /**体重kg*/
    @Excel(name = "体重kg", width = 15)
    @ApiModelProperty(value = "体重kg")
    private BigDecimal weight;
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

    @ApiModelProperty(value = "图片url")
    private List<String> urls;
}
