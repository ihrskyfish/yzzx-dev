package com.wocai.platform.modules.business.book.vo;

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
 * @create: 2023-05-25 16:15
 **/
@Data
public class BookEvaluationRecordsVo implements Serializable {

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

    @Excel(name = "评价时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评价时间")
    private Date evaluateTime;
    /**评价内容*/
    @Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
    private String evaluateContent;
}
