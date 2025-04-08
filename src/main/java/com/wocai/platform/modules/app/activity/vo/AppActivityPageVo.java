package com.wocai.platform.modules.app.activity.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 11:57
 **/
@Data
public class AppActivityPageVo implements Serializable {

    private String id;

    @ApiModelProperty(value = "活动名称")
    private String name;

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

    @ApiModelProperty(value = "结束状态（0：未结束 1已结束）")
    private String endState;

    @Excel(name = "图片地址", width = 15)
    @ApiModelProperty(value = "图片地址")
    private String fileUrl;


    @ApiModelProperty(value = "活动人数")
    private Integer count;
}
