package com.wocai.platform.modules.system.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wocai.platform.modules.system.entity.SysVersion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * @描述: 服务更新记录Req
 * @Author: wocai
 * @Date: 2021-11-08
 * @Version: V1.0
 */
@Data
public class SysVersionReq extends SysVersion {


    /**文件*/
    @ApiModelProperty(value = "文件")
    private MultipartFile file;
}