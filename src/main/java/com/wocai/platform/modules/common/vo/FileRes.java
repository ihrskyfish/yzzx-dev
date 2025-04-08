package com.wocai.platform.modules.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:33
 **/
@Data
public class FileRes implements Serializable {

    @ApiModelProperty(value = "文件URL")
    private String fileKey;
    @ApiModelProperty(value = "文件完整URL")
    private String fileUrl;
    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;
}
