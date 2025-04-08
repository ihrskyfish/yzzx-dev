package com.wocai.platform.modules.app.my.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 10:18
 **/
@Data
@ApiModel(value = "孕期档案")
public class MyPregnancyArchivesVo implements Serializable {

    @ApiModelProperty(value = "连续10天内打卡心情")
    private List<String> myMoodVos;

    @ApiModelProperty(value = "连续10天内打卡体重")
    private List<MyWeightVo> myWeightVos;

    @ApiModelProperty(value = "连续10天内打卡三围走势")
    private List<MyThreeCircumferencesVo> myThreeCircumferencesVos;

    @ApiModelProperty(value = "胸围cm")
    private List<BigDecimal> busts;

    @ApiModelProperty(value = "腰围cm")
    private List<BigDecimal> waists;

    @ApiModelProperty(value = "臀围cm")
    private List<BigDecimal> hip;

    @ApiModelProperty(value = "体重")
    private List<BigDecimal> weights;


    @ApiModelProperty(value = "日期")
    private List<LocalDate> localDates;

}
