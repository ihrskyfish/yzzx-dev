package com.wocai.platform.modules.app.my.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @create: 2023-05-25 11:24
 **/
@Data
public class MyLikesVo implements Serializable {

    private String id;

    @Excel(name = "活动类型", width = 15)
    @ApiModelProperty(value = "活动类型")
    private String type;
    /**活动标题*/
    @Excel(name = "活动标题", width = 15)
    @ApiModelProperty(value = "活动标题")
    private String title;

    @ApiModelProperty(value = "故事开头")
    private String startContent;

    /**内容*/
    @Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;


    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @Excel(name = "查看次数", width = 15)
    @ApiModelProperty(value = "查看次数")
    private Long number;

    @ApiModelProperty(value = "点赞数量")
    private Integer likeNumber;

    @ApiModelProperty(value = "图片列表")
    private List<String> urls;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
