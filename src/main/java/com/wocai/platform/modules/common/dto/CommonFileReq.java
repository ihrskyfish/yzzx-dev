package com.wocai.platform.modules.common.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: bmfz-api
 * @description
 * @author: YouName
 * @create: 2023-03-20 09:41
 **/
@Data
@ApiModel
public class CommonFileReq implements Serializable {

    @Excel(name = "文件url", width = 15)
    @ApiModelProperty(value = "文件url")
    private String fileKey;
    /**文件名称*/
    @Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    /**文件大小*/
    @Excel(name = "文件大小", width = 15)
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
    /**文件扩展名*/
    @Excel(name = "文件扩展名", width = 15)
    @ApiModelProperty(value = "文件扩展名")
    private String fileExt;
}
