package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 11:00
 **/
@Data
public class MyBabyProfileVo implements Serializable {

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

    @ApiModelProperty(value = "图片地址")
    private List<String> fileUrls;
}
