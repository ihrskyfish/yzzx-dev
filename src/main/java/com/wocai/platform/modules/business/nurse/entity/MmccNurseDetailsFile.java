package com.wocai.platform.modules.business.nurse.entity;

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
 * @描述: 护理知识图片
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("mmcc_nurse_details_flie")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="mmcc_nurse_details_flie对象", description="护理知识图片")
public class MmccNurseDetailsFile extends BaseEntity {

	/**内容id*/
	@Excel(name = "内容id", width = 15)
    @ApiModelProperty(value = "内容id")
	private String detailesId;
	/**文件url*/
	@Excel(name = "文件url", width = 15)
    @ApiModelProperty(value = "文件url")
	private String url;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String status;
}