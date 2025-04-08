package com.wocai.platform.modules.business.display.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.book.vo.BookEvaluationRecordsVo;
import com.wocai.platform.modules.business.book.vo.ReservationRecordVo;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @描述: 展示用户预定表
 * @Author: wocai
 * @Date: 2023-05-22
 * @Version: V1.0
 */
public interface MmccServiceUserMapper extends BaseMapper<MmccServiceUser> {

    IPage<MmccServiceUserRes> queryPage(IPage<MmccServiceUserRes> page, @Param("m") Map<String, Object> reqParam);

    IPage<ReservationRecordVo> reservationRecord(Page<ReservationRecordVo> page,@Param("m") Map<String, Object> reqParam);

    IPage<BookEvaluationRecordsVo> evaluationRecordsVo(Page<BookEvaluationRecordsVo> page);
}