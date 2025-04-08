package com.wocai.platform.modules.app.communication.vo;

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
 * @create: 2023-05-24 15:52
 **/
@Data
public class AppCommunicationUserVo implements Serializable {

    private String id;

    @Excel(name = "用户昵称", width = 15)
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @Excel(name = "用户头像", width = 15)
    @ApiModelProperty(value = "用户头像")
    private String avatarUrl;

    /**内容*/
    @Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
    private String evaluate;

    @ApiModelProperty(value = "评论时间")
    @Excel(name = "评论时间", width = 20, format = "yyyy-MM-dd HH:mm:ss",orderNum = "510")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
