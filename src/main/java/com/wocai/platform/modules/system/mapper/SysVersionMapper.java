package com.wocai.platform.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.system.entity.SysVersion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.system.vo.SysVersionRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 服务更新记录
 * @Author: wocai
 * @Date: 2021-11-08
 * @Version: V1.0
 */
public interface SysVersionMapper extends BaseMapper<SysVersion> {

    IPage<SysVersionRes> queryPage(IPage<SysVersionRes> page,@Param("m") Map<String, Object> reqParam);
}