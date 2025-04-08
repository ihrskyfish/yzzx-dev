package com.wocai.platform.modules.business.confinement.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementFileRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 产期圈图片表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccConfinementFileMapper extends BaseMapper<MmccConfinementFile> {

    IPage<MmccConfinementFileRes> queryPage(IPage<MmccConfinementFileRes> page, @Param("m") Map<String, Object> reqParam);

}