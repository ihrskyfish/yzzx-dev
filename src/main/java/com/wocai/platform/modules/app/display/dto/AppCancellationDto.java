package com.wocai.platform.modules.app.display.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AppCancellationDto implements Serializable {
    @ApiModelProperty(value = "预订id")
    private String serviceUserId;
}
