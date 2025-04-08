package com.wocai.platform.modules.app.display.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 13:54
 **/
@Data
public class AppDisplayQueryByIdVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

    /**房间名称*/
    @Excel(name = "封面图url", width = 15)
    @ApiModelProperty(value = "封面图url")
    private String coverImageUrl;

    /**房间名称*/
    @Excel(name = "详情图url", width = 15)
    @ApiModelProperty(value = "详情图url")
    private String detailsUrl;

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

    @Excel(name = "服务标签（，分割开）", width = 15)
    @ApiModelProperty(value = "服务标签（，分割开）")
    private String roomLabel;

    @ApiModelProperty(value = "图片地址")
    private List<AppDisplayFileVo> appDisplayFileVos;


    @ApiModelProperty(value = "是否预定")
    private boolean predetermine;

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




}
