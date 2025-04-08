package com.wocai.platform.modules.app.display.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 11:46
 **/
@Data
public class AppDisplayListVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "房间名称")
    private String name;
    /**房型介绍*/
    @Excel(name = "房型介绍", width = 15)
    @ApiModelProperty(value = "房型介绍")
    private String roomModel;
    /**服务介绍*/
    @Excel(name = "服务介绍", width = 15)
    @ApiModelProperty(value = "服务介绍")
    private String serviceIntroduce;

    @ApiModelProperty(value = "图片地址")
    private String fileUrl;

    /**营业时间*/
    @Excel(name = "营业时间", width = 15)
    @ApiModelProperty(value = "营业时间")
    private String businessHours;
    /**电话号码*/
    @Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
    private String phoneCode;

    /**门店地址*/
    @Excel(name = "门店地址", width = 15)
    @ApiModelProperty(value = "门店地址")
    private String storeAddress;

//    @ApiModelProperty(value = "是否已预订")
//    private boolean book;
}
