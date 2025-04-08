package com.wocai.platform.modules.system.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wocai.platform.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @描述: 服务更新记录
 * @Author: wocai
 * @Date: 2021-11-08
 * @Version: V1.0
 */
@Data
@TableName("sys_version")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_version对象", description="服务更新记录")
public class SysVersion extends BaseEntity {

	/**服务类型(1:WEB 2:后台)*/
	@Excel(name = "服务类型(1:WEB 2:后台)", width = 15)
	@Dict(dicCode = "service_type")
    @ApiModelProperty(value = "服务类型(1:WEB 2:后台)")
	private String sysType;
	/**文件地址*/
	@Excel(name = "文件地址", width = 15)
    @ApiModelProperty(value = "文件地址")
	private String fileUrl;
	/**更新说明*/
	@Excel(name = "更新说明", width = 15)
    @ApiModelProperty(value = "更新说明")
	private String remark;
}