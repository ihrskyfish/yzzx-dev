package com.wocai.platform.modules.business.health.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.health.entity.YzPregHealth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.health.vo.YzPregHealthRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 孕期健康
 * @Author: wocai
 * @Date: 2023-06-16
 * @Version: V1.0
 */
public interface YzPregHealthMapper extends BaseMapper<YzPregHealth> {

    IPage<YzPregHealthRes> queryPage(IPage<YzPregHealthRes> page, @Param("m") Map<String, Object> reqParam);

}