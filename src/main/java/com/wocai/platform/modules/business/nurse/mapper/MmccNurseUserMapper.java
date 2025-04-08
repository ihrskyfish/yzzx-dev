package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 用户收藏
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface MmccNurseUserMapper extends BaseMapper<MmccNurseUser> {

    IPage<MmccNurseUserRes> queryPage(IPage<MmccNurseUserRes> page, @Param("m") Map<String, Object> reqParam);

}