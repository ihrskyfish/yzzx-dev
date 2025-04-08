package com.wocai.platform.modules.business.display.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: 服务展示Res
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@ApiModel(value="服务展示", description="服务展示")
public class MmccServiceDisplayRes extends MmccServiceDisplay {

    @ApiModelProperty(value = "图片地址")
    private List<String> fileUrls;

}