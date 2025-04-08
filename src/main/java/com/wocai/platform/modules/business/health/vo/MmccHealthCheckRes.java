package com.wocai.platform.modules.business.health.vo;

import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述: 每日打卡Res
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@ApiModel(value="每日打卡", description="每日打卡")
public class MmccHealthCheckRes extends MmccHealthCheck {


}