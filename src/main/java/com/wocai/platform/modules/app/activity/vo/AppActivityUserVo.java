package com.wocai.platform.modules.app.activity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 13:40
 **/
@Data
public class AppActivityUserVo implements Serializable {

    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "报名开始")
    private Date createTime;
}
