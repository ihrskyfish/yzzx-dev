package com.wocai.platform.modules.business.user.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.business.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.user.vo.UserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: User
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<UserRes> queryPage(IPage<UserRes> page, @Param("m") Map<String, Object> reqParam);

}