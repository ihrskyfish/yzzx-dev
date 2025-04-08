package com.wocai.platform.modules.business.demo.entity;

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
 * @描述: 测试Demo
 * @Author: wocai
 * @Date: 2022-07-07
 * @Version: V1.0
 */
@Data
@TableName("test_demo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="test_demo对象", description="测试Demo")
public class TestDemo extends BaseEntity {

	/**昵称*/
	@Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
	private String nickname;
	/**真实姓名*/
	@Excel(name = "真实姓名", width = 15)
    @ApiModelProperty(value = "真实姓名")
	private String realname;
	/**性别(1:男,2:女)*/
	@Excel(name = "性别(1:男,2:女)", width = 15)
    @ApiModelProperty(value = "性别(1:男,2:女)")
	private Integer sex;
	/**电子邮件*/
	@Excel(name = "电子邮件", width = 15)
    @ApiModelProperty(value = "电子邮件")
	private String email;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String phone;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private Integer status;
}