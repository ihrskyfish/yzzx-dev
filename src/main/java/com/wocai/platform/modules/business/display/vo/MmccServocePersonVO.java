package com.wocai.platform.modules.business.display.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MmccServocePersonVO {

    @ApiModelProperty(value = "接待人员id")
    private String servicePersonId;

    @ApiModelProperty(value = "接待人员姓名")
    private String servicePersonName;
}
