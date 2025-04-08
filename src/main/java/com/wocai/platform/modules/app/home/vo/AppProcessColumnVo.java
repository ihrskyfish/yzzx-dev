package com.wocai.platform.modules.app.home.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wocai.platform.modules.business.technology.entity.MmccTechnologyFiles;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 17:06
 **/
@Data
@ApiModel(value = "工艺栏列表")
public class AppProcessColumnVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String id;
    /**名称*/
    @Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private String name;


    @Excel(name = "副标题", width = 15)
    @ApiModelProperty(value = "副标题")
    private String subtitle;

    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;

    /**内容*/
    @Excel(name = "列表展示图片url", width = 15)
    @ApiModelProperty(value = "列表展示图片url")
    private String url;

    @ApiModelProperty(value = "排序")
    private String sort;

    /**状态*/
    @Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private List<MmccTechnologyFiles> files;

}


