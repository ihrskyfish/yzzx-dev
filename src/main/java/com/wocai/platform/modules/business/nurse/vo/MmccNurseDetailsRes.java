package com.wocai.platform.modules.business.nurse.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @描述: 护理内容Res
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Data
@ApiModel(value="护理内容", description="护理内容")
public class MmccNurseDetailsRes extends MmccNurseDetails {

    @Excel(name = "知识图片Key", width = 15)
    @ApiModelProperty(value = "知识图片Key")
    private String urlKey;

    @ApiModelProperty(value = "类别名称")
    private String typeName;

    private List<String> urls;

}