package com.wocai.platform.modules.business.communication.vo;

import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述: mmcc-交流圈Res
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@ApiModel(value="mmcc-交流圈", description="mmcc-交流圈")
public class MmccCommunicationCircleRes extends MmccCommunicationCircle {

    @ApiModelProperty(value = "回答数量")
    private Integer count;

}