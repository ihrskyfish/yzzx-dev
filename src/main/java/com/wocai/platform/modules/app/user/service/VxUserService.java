package com.wocai.platform.modules.app.user.service;

import com.wocai.platform.modules.app.user.dto.VxUserGestationalWeekDto;
import com.wocai.platform.modules.app.user.dto.WxPhoneCodeDTO;
import com.wocai.platform.modules.app.user.vo.AppUserLoginByCodeVo;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-23 11:13
 **/
public interface VxUserService {
    AppUserLoginByCodeVo loginByCode(String code);

    void bindPhone(WxPhoneCodeDTO wxPhoneCodeDTO,String userId);

    AppUserLoginByCodeVo loginByCodeTest(String code);

    void gestationalWeek(VxUserGestationalWeekDto vxUserGestationalWeekDto, String userId);
}
