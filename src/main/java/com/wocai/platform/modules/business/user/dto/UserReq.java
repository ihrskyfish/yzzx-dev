package com.wocai.platform.modules.business.user.dto;

import com.wocai.platform.modules.business.user.entity.User;
import lombok.Data;


/**
 * @描述: UserReq
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Data
public class UserReq extends User {

    private String userPhone;

}