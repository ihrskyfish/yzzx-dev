package com.wocai.platform.modules.business.health.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 17:57
 **/
@Data
public class MmccPregnancyArchivesVo implements Serializable {

    private String id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

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

    /**年*/
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

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "日期")
    private LocalDate localDates;
}
