package com.wocai.platform.modules.app.home.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 17:55
 **/
@Data
@ApiModel(value = "轮播图")
public class AppRotationChartVo implements Serializable {
    /**图片地址*/
    @Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
    private String fileUrl;
    /**图片名称*/
    @Excel(name = "图片名称", width = 15)
    @ApiModelProperty(value = "图片名称")
    private String fileName;

    @ApiModelProperty(value = "图片内容")
    private String content;
    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;
}
