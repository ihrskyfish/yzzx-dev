package com.wocai.platform.modules.business.user.vo;

import com.wocai.platform.modules.business.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @描述: UserRes
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
@ApiModel(value="User", description="User")
public class UserRes extends User {

    private Long stayDays;

}