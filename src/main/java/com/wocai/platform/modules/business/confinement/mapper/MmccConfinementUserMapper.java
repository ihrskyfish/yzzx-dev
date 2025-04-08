package com.wocai.platform.modules.business.confinement.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.confinement.entity.MmccConfinementUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.confinement.vo.MmccConfinementUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 产期圈点赞表
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccConfinementUserMapper extends BaseMapper<MmccConfinementUser> {

    IPage<MmccConfinementUserRes> queryPage(IPage<MmccConfinementUserRes> page, @Param("m") Map<String, Object> reqParam);

}