package com.wocai.platform.modules.app.confinement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementPageVo;
import com.wocai.platform.modules.app.confinement.vo.AppConfinementQueryByIdVo;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 14:21
 **/
public interface AppConfinementService {
    IPage<AppConfinementPageVo> queryPageList(Page<MmccConfinement> page,ReqUser reqUser, String id);

    AppConfinementQueryByIdVo getMainById(String id);

    void praise(String id, ReqUser reqUser);
}
