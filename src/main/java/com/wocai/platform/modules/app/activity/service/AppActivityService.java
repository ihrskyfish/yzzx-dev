package com.wocai.platform.modules.app.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.activity.dto.AppAcitvityRegisterDto;
import com.wocai.platform.modules.app.activity.vo.AppActivityPageVo;
import com.wocai.platform.modules.app.activity.vo.AppActivityQueryByIdVo;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-24 11:54
 **/
public interface AppActivityService {
    IPage<AppActivityPageVo> queryPageList(Page<MmccActivity> page);

    AppActivityQueryByIdVo getMainById(String id,ReqUser reqUser);

    void register(AppAcitvityRegisterDto appAcitvityRegisterDto, ReqUser reqUser);

}
