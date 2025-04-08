package com.wocai.platform.modules.business.confinement.dto;

import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import lombok.Data;

import java.util.List;


/**
 * @描述: mmcc-产期圈Req
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Data
public class MmccConfinementReq extends MmccConfinement {

    private List<String> urls;

}