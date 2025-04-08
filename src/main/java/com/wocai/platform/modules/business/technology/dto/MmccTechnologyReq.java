package com.wocai.platform.modules.business.technology.dto;

import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: mmcc-工艺栏Req
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
public class MmccTechnologyReq extends MmccTechnology {


    @ApiModelProperty(value = "图片地址")
    private List<String> fileUrls;


}