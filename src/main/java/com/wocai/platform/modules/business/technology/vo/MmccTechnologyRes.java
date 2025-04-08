package com.wocai.platform.modules.business.technology.vo;

import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: mmcc-工艺栏Res
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@ApiModel(value="mmcc-工艺栏", description="mmcc-工艺栏")
public class MmccTechnologyRes extends MmccTechnology {

    private List<String> fileUrls;

}