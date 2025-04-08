package com.wocai.platform.modules.app.display.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
public class AppDisplayMyBookListVo implements Serializable {

    @ApiModelProperty(value = "id(房间id)")
    private String id;

    @Excel(name = "房间名称", width = 15)
    @ApiModelProperty(value = "房间名称")
    private String name;

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

    @Excel(name = "评价时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评价时间")
    private Date evaluateTime;

    /**状态*/
    @Excel(name = "状态（-1：未到店 0：未评价 1已评价）", width = 15)
    @ApiModelProperty(value = "状态（-1：未到店 0：未评价 1已评价）")
    private String status;

}
