package com.wocai.platform.modules.quartz.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wocai.platform.common.system.base.entity.BaseEntity;
import lombok.Data;

/**
 * @Description: 定时任务在线管理
 * @Date:  2019-01-02
 * @Version: V1.0
 */
@Data
@TableName("sys_quartz_job")
public class QuartzJob extends BaseEntity {

	/**任务类名*/
	@Excel(name="任务类名",width=40)
	private java.lang.String jobClassName;
	/**cron表达式*/
	@Excel(name="cron表达式",width=30)
	private java.lang.String cronExpression;
	/**参数*/
	@Excel(name="参数",width=15)
	private java.lang.String parameter;
	/**任务执行IP*/
	@Excel(name="任务执行IP",width=15)
	private java.lang.String jobIp;
	/**描述*/
	@Excel(name="描述",width=40)
	private java.lang.String description;
	/**状态 0正常 -1停止*/
	@Excel(name="状态",width=15)
	private java.lang.Integer status;
}
