package com.wocai.platform.modules.app.health.service;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.health.dto.AppHealthBabyDto;
import com.wocai.platform.modules.app.health.dto.AppHealthClockDto;
import com.wocai.platform.modules.app.health.vo.AppHealthVo;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 09:21
 **/
public interface AppHealthService {
    void clock(AppHealthClockDto appHealthClockDto, ReqUser reqUser);

    void babyUpload(AppHealthBabyDto appHealthBabyDto, ReqUser reqUser);

    AppHealthVo dayClockDetails(ReqUser reqUser);
}
