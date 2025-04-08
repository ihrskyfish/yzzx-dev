package com.wocai.platform.modules.app.communication.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 15:52
 **/
@Data
public class AppCommunicationPageVo implements Serializable {

    private String id;

    @Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
    private String title;
    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "回答数量")
    private Integer answerCount;
}
