package com.wocai.platform.modules.app.nurse.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.junit.experimental.theories.DataPoints;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 16:01
 **/
@Data
public class AppNurseTypeVo implements Serializable {

    /**类别名称*/
    @Excel(name = "id", width = 15)
    @ApiModelProperty(value = "id")
    private String id;

    /**类别名称*/
    @Excel(name = "类别名称", width = 15)
    @ApiModelProperty(value = "类别名称")
    private String name;
}
