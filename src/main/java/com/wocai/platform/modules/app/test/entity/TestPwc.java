package com.wocai.platform.modules.app.test.entity;

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
 * @描述: 公益栏信息列表
 * @Author: wocai
 * @Date: 2023-06-02
 * @Version: V1.0
 */
@Data
@TableName("test_pwc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="test_pwc对象", description="公益栏信息列表")
public class TestPwc extends BaseEntity {

	/**pwc_table*/
	@Excel(name = "pwc_table", width = 15)
    @ApiModelProperty(value = "pwc_table")
	private String pwcTable;
	/**pwc_table_img*/
	@Excel(name = "pwc_table_img", width = 15)
    @ApiModelProperty(value = "pwc_table_img")
	private String pwcTableImg;
	/**pwc_intr*/
	@Excel(name = "pwc_intr", width = 15)
    @ApiModelProperty(value = "pwc_intr")
	private String pwcIntr;
	/**pwc_text*/
	@Excel(name = "pwc_text", width = 15)
    @ApiModelProperty(value = "pwc_text")
	private String pwcText;
	/**pwc_table_img_big*/
	@Excel(name = "pwc_table_img_big", width = 15)
    @ApiModelProperty(value = "pwc_table_img_big")
	private String pwcTableImgBig;
}