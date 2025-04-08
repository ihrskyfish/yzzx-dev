package com.wocai.platform.modules.app.nurse.service;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.nurse.dto.AppNurseCollectDto;
import com.wocai.platform.modules.app.nurse.dto.AppNurseDetailsDto;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsQueryByIdVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsVo;
import com.wocai.platform.modules.app.nurse.vo.AppNurseTypeVo;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 15:55
 **/
public interface AppNurseService {
    List<AppNurseTypeVo> typeList();

    List<AppNurseDetailsVo> getDetailsByTypeId(AppNurseDetailsDto appNurseDetailsDto, ReqUser reqUser);

    void collect(AppNurseCollectDto appNurseCollectDto, ReqUser reqUser);

    AppNurseDetailsQueryByIdVo queryById(String id,ReqUser reqUser);
}
