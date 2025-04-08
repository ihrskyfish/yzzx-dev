package com.wocai.platform.modules.business.health.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 17:44
 **/
@Data
public class MmccMoodVo implements Serializable {

    private String id;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "每天心情（0：不开心 1：普通 2：开心）")
    private String mood;

    /**年*/
    @Excel(name = "年", width = 15)
    @ApiModelProperty(value = "年")
    private Integer year;
    /**月*/
    @Excel(name = "月", width = 15)
    @ApiModelProperty(value = "月")
    private Integer month;
    /**日*/
    @Excel(name = "日", width = 15)
    @ApiModelProperty(value = "日")
    private Integer day;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
