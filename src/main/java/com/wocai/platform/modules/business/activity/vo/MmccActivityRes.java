package com.wocai.platform.modules.business.activity.vo;

import com.wocai.platform.modules.business.activity.dto.FileUrlVo;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: 孕期圈活动Res
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@ApiModel(value="孕期圈活动", description="孕期圈活动")
public class MmccActivityRes extends MmccActivity {

    @ApiModelProperty(value = "活动图片")
    private List<FileUrlVo> fileUrls;

}