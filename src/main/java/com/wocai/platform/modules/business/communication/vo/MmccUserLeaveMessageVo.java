package com.wocai.platform.modules.business.communication.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-26 17:31
 **/
@Data
public class MmccUserLeaveMessageVo implements Serializable {

    private String id;

    @Excel(name = "交流圈id", width = 15)
    @ApiModelProperty(value = "交流圈id")
    private String circleId;
    /**用户id*/
    @Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**用户评价内容*/
    @Excel(name = "用户评价内容", width = 15)
    @ApiModelProperty(value = "用户评价内容")
    private String evaluate;
    /**是否精华（0否 1是）*/
    @Excel(name = "是否精华（0否 1是）", width = 15)
    @ApiModelProperty(value = "是否精华（0否 1是）")
    private String essence;

    @ApiModelProperty(value = "留言时间")
    @Excel(name = "留言时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private java.util.Date createTime;
}
