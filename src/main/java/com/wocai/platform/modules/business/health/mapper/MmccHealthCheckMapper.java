package com.wocai.platform.modules.business.health.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.health.vo.MmccBaByUserVo;
import com.wocai.platform.modules.business.health.vo.MmccHealthCheckRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 每日打卡
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccHealthCheckMapper extends BaseMapper<MmccHealthCheck> {

    IPage<MmccHealthCheckRes> queryPage(IPage<MmccHealthCheckRes> page, @Param("m") Map<String, Object> reqParam);

    IPage<MmccBaByUserVo> babyUserList(Page<MmccBaByUserVo> page);
}