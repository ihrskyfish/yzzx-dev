package com.wocai.platform.modules.business.display.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 服务展示
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccServiceDisplayMapper extends BaseMapper<MmccServiceDisplay> {

    IPage<MmccServiceDisplayRes> queryPage(IPage<MmccServiceDisplayRes> page, @Param("m") Map<String, Object> reqParam);

}