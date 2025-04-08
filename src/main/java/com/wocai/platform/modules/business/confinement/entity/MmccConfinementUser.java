package com.wocai.platform.modules.business.confinement.entity;

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
 * @描述: 产期圈点赞表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_confinement_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_confinement_user对象", description="产期圈点赞表")
public class MmccConfinementUser extends BaseEntity {

	/**产期圈id*/
	@Excel(name = "产期圈id", width = 15)
    @ApiModelProperty(value = "产期圈id")
	private String confinementId;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private String userId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}