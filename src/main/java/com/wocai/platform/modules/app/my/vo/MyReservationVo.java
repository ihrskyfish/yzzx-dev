package com.wocai.platform.modules.app.my.vo;

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
 * @create: 2023-05-25 11:37
 **/
@Data
public class MyReservationVo implements Serializable {

    @ApiModelProperty(value = "id(房间id)")
    private String id;

    @ApiModelProperty(value = "预订id")
    private String serviceUserId;

    @ApiModelProperty(value = "图片地址")
    private String fileUrl;

    /**预定时间*/
    @Excel(name = "预定时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "预定时间")
    private Date scheduledTime;

    @Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
    private String evaluateContent;
}
