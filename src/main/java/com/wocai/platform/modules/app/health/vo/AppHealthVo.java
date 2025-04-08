package com.wocai.platform.modules.app.health.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:23
 **/
@Data
@ApiModel
public class AppHealthVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

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

    @ApiModelProperty(value = "孕妇健康今日是否打卡")
    private boolean healthClock;

    @ApiModelProperty(value = "心情连续打卡天数")
    private Integer moodContinuousDays;

    @ApiModelProperty(value = "健康连续打卡天数")
    private Integer healthContinuousDays;

    @ApiModelProperty(value = "B超图片s")
    private List<String> urls;

    @ApiModelProperty(value = "宝宝B超今日是否打卡")
    private boolean babyClock;

    @ApiModelProperty(value = "宝宝B超总打卡天数")
    private Integer babyContinuousDays;



}
