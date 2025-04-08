package com.wocai.platform.modules.business.confinement.vo;

import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: mmcc-产期圈Res
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@ApiModel(value="mmcc-产期圈", description="mmcc-产期圈")
public class MmccConfinementRes extends MmccConfinement {

    private List<String> urls;

}