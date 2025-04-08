package com.wocai.platform.modules.business.rotation.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.rotation.entity.MmccRotationChart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.rotation.vo.MmccRotationChartRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 轮播图
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccRotationChartMapper extends BaseMapper<MmccRotationChart> {

    IPage<MmccRotationChartRes> queryPage(IPage<MmccRotationChartRes> page, @Param("m") Map<String, Object> reqParam);

}