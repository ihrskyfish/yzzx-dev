package com.wocai.platform.modules.business.confinement.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: mmcc-产期圈
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccConfinementMapper extends BaseMapper<MmccConfinement> {

    IPage<MmccConfinementRes> queryPage(IPage<MmccConfinementRes> page, @Param("m") Map<String, Object> reqParam);

}