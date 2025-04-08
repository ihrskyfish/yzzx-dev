package com.wocai.platform.modules.app.nurse.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 16:37
 **/
@Data
public class AppNurseDetailsQueryByIdVo implements Serializable {

    /**类别名称*/
    @Excel(name = "id", width = 15)
    @ApiModelProperty(value = "id")
    private String id;
    /**类型id*/
    @Excel(name = "类型id", width = 15)
    @ApiModelProperty(value = "类型id")
    private String typeId;
    /**知识标题*/
    @Excel(name = "知识标题", width = 15)
    @ApiModelProperty(value = "知识标题")
    private String title;
    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "是否收藏")
    private boolean collect;

    @ApiModelProperty(value = "图片")
    private List<String> fileUrls;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    protected java.util.Date createTime;
}
