package com.wocai.platform.modules.business.activity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-26 15:05
 **/
@Data
public class MmccActivityResultsDto implements Serializable {

    private String id;

    @Excel(name = "活动成就", width = 15)
    @ApiModelProperty(value = "活动成就")
    private String achieve;

    @ApiModelProperty(value = "活动图片")
    private List<String> fileUrls;
}
