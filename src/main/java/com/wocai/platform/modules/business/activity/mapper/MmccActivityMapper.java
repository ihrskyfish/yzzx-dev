package com.wocai.platform.modules.business.activity.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.activity.vo.MmccActivityRes;
import com.wocai.platform.modules.business.activity.vo.MmccActivityUserListVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 孕期圈活动
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccActivityMapper extends BaseMapper<MmccActivity> {

    IPage<MmccActivityRes> queryPage(IPage<MmccActivityRes> page, @Param("m") Map<String, Object> reqParam);

    IPage<MmccActivityUserListVo> activityUserList(Page<MmccActivityUserListVo> page,String id);
}