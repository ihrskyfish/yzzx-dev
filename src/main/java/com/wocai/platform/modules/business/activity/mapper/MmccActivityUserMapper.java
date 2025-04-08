package com.wocai.platform.modules.business.activity.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.activity.entity.MmccActivityUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.activity.vo.MmccActivityUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 用户活动表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccActivityUserMapper extends BaseMapper<MmccActivityUser> {

    IPage<MmccActivityUserRes> queryPage(IPage<MmccActivityUserRes> page, @Param("m") Map<String, Object> reqParam);

}