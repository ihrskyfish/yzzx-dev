package com.wocai.platform.modules.business.activity.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.activity.entity.MmccActivityFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.activity.vo.MmccActivityFileRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 活动图片表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccActivityFileMapper extends BaseMapper<MmccActivityFile> {

    IPage<MmccActivityFileRes> queryPage(IPage<MmccActivityFileRes> page, @Param("m") Map<String, Object> reqParam);

}