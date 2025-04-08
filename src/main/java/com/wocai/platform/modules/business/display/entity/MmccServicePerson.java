package com.wocai.platform.modules.business.display.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @描述: 预约接待员管理
 * @Author: wocai
 * @Date: 2024-02-27
 * @Version: V1.0
 */
@Data
@TableName("mmcc_service_person")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_service_person对象", description="预约接待员管理")
public class MmccServicePerson extends BaseEntity {

	/**预约接待员姓名*/
	@Excel(name = "预约接待员姓名", width = 15)
    @ApiModelProperty(value = "预约接待员姓名")
	private String name;
}