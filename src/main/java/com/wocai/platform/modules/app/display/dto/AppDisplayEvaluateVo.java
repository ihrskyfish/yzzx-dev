package com.wocai.platform.modules.app.display.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 14:30
 **/
@Data
public class AppDisplayEvaluateVo implements Serializable {

    @ApiModelProperty(value = "预订id")
    private String serviceUserId;

    @Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
    private String evaluateContent;

}
