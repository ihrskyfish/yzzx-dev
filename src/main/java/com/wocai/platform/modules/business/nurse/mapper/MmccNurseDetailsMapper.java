package com.wocai.platform.modules.business.nurse.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.modules.app.nurse.vo.AppNurseDetailsVo;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @描述: 护理内容
 * @Author: wocai
 * @Date: 2023-05-23
 * @Version: V1.0
 */
public interface MmccNurseDetailsMapper extends BaseMapper<MmccNurseDetails> {

    IPage<MmccNurseDetailsRes> queryPage(IPage<MmccNurseDetailsRes> page, @Param("m") Map<String, Object> reqParam);

    List<AppNurseDetailsVo> getRecommendDetails(Integer gestationalWeek);

}