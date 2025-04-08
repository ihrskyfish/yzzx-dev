package com.wocai.platform.modules.business.baby.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.app.my.vo.MyDateVo;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.baby.vo.MmccBabyUltrasoundRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @描述: 宝宝B超
 * @Author: wocai
 * @Date: 2023-05-24
 * @Version: V1.0
 */
public interface MmccBabyUltrasoundMapper extends BaseMapper<MmccBabyUltrasound> {

    IPage<MmccBabyUltrasoundRes> queryPage(IPage<MmccBabyUltrasoundRes> page, @Param("m") Map<String, Object> reqParam);

    List<MyDateVo> getAllDay(String userId);

    List<MyDateVo> getAllPageDay( String userId);

    //获取o总上传天数
    Integer getAllDays(String userId);
}