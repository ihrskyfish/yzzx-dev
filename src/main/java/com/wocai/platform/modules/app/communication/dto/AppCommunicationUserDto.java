package com.wocai.platform.modules.app.communication.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class AppCommunicationUserDto implements Serializable {

    private String id;

    /**内容*/
    @Excel(name = "评价内容", width = 15)
    @ApiModelProperty(value = "评价内容")
    private String evaluate;


}
