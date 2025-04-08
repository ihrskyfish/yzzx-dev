package com.wocai.platform.modules.business.communication.entity;

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
 * @描述: 话题留言
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
@TableName("mmcc_communication_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_communication_user对象", description="话题留言")
public class MmccCommunicationUser extends BaseEntity {

	/**交流圈id*/
	@Excel(name = "交流圈id", width = 15)
    @ApiModelProperty(value = "交流圈id")
	private String circleId;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
	private String userId;
	/**用户评价内容*/
	@Excel(name = "用户评价内容", width = 15)
    @ApiModelProperty(value = "用户评价内容")
	private String evaluate;
	/**是否精华（0否 1是）*/
	@Excel(name = "是否精华（0否 1是）", width = 15)
    @ApiModelProperty(value = "是否精华（0否 1是）")
	private String essence;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}