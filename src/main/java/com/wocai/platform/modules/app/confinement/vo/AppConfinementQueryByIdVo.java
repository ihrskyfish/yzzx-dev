package com.wocai.platform.modules.app.confinement.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 14:55
 **/
@Data
public class AppConfinementQueryByIdVo implements Serializable {

    private String id;
    /**活动类型*/
    @Excel(name = "活动类型", width = 15)
    @ApiModelProperty(value = "活动类型")
    private String type;
    /**活动标题*/
    @Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
    private String title;
    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;


    @ApiModelProperty(value = "图片集合")
    private List<String> fileUrls;

}
