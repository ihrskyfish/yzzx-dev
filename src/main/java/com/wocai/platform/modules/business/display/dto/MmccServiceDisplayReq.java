package com.wocai.platform.modules.business.display.dto;

import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: 服务展示Req
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
public class MmccServiceDisplayReq extends MmccServiceDisplay {

    @ApiModelProperty(value = "图片地址")
    private List<String> fileUrls;

}