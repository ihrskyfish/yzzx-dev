package com.wocai.platform.modules.app.demo.service;

import com.wocai.platform.common.api.dto.IdDTO;
import com.wocai.platform.modules.app.demo.dto.TestDemoReq;
import com.wocai.platform.modules.app.demo.vo.TestDemoRes;

/**
 * @文件名: IHouseNoticeHttpService
 * @包名 com.wocai.platform.modules.app.user.service
 * @描述:
 * @创建者 linwq
 * @创建时间 2020-01-13 13:38
 * @版权 Copyright(c)2019 浙江我财
 */
public interface ITestDemoHttpService {

    /**
     * @param idDTO
     * @return
     */
    TestDemoRes selectById(IdDTO idDTO);

    /**
     * @param testDemoReq
     */
    TestDemoRes selectByName(TestDemoReq testDemoReq);


}
