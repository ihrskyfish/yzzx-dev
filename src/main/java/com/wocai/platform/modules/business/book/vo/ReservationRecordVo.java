package com.wocai.platform.modules.business.book.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 15:43
 **/
@Data
@ApiModel(value = "预订记录")
public class ReservationRecordVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "displayId")
    private String displayId;

    @Excel(name = "房间名称", width = 15)
    @ApiModelProperty(value = "房间名称")
    private String name;

    /**用户姓名*/
    @Excel(name = "用户姓名", width = 15)
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    /**用户手机号*/
    @Excel(name = "用户手机号", width = 15)
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @Excel(name = "预定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "预定时间")
    private Date scheduledTime;

    @ApiModelProperty(value = "状态（-1：未到店 0：未评价 1已评价）")
    private String status;

    /**是否需要接送（0：是 1：否）*/
    @Excel(name = "是否需要接送（0：是 1：否）", width = 15)
    @ApiModelProperty(value = "是否需要接送（0：是 1：否）")
    private String pickup;
}
