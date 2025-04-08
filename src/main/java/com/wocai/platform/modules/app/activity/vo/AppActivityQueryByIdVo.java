package com.wocai.platform.modules.app.activity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 13:39
 **/
@Data
public class AppActivityQueryByIdVo implements Serializable {

    private String id;

    /**活动名称*/
    @Excel(name = "活动名称", width = 15)
    @ApiModelProperty(value = "活动名称")
    private String name;
    /**活动联系人姓名*/
    @Excel(name = "活动联系人姓名", width = 15)
    @ApiModelProperty(value = "活动联系人姓名")
    private String activityUserName;
    /**联系人手机号*/
    @Excel(name = "联系人手机号", width = 15)
    @ApiModelProperty(value = "联系人手机号")
    private String activityUserPhone;
    /**报名开始时间*/
    @Excel(name = "报名开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报名开始时间")
    private Date startTime;
    /**报名截止时间*/
    @Excel(name = "报名截止时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报名截止时间")
    private Date endTime;
    /**活动介绍*/
    @Excel(name = "活动介绍", width = 15)
    @ApiModelProperty(value = "活动介绍")
    private String introduce;
    /**结束状态（0：未结束 1已结束）*/
    @Excel(name = "结束状态（0：未结束 1已结束）", width = 15)
    @ApiModelProperty(value = "结束状态（0：未结束 1已结束）")
    private String endState;
    /**图片地址*/
    @Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
    private String fileUrl;


    @ApiModelProperty(value = "报名用户")
    private List<AppActivityUserVo> appActivityUserVos;

    /**活动成就*/
    @Excel(name = "活动成就", width = 15)
    @ApiModelProperty(value = "活动成就")
    private String achieve;

    @ApiModelProperty(value = "活动图片")
    private List<String> achieveUrls;

    @ApiModelProperty(value = "自己是否报名")
    private boolean application;
}
