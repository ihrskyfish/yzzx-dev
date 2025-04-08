package com.wocai.platform.modules.business.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.book.dto.BookListDto;
import com.wocai.platform.modules.business.book.vo.BookEvaluationRecordsVo;
import com.wocai.platform.modules.business.book.vo.ReservationRecordVo;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 15:42
 **/
public interface BookService {
    IPage<ReservationRecordVo> reservationRecord(Page<ReservationRecordVo> page, BookListDto bookListDto);

    MmccServiceUserRes getMainById(String id);

    IPage<BookEvaluationRecordsVo> evaluationRecordsVo(Page<BookEvaluationRecordsVo> page);

    void toStore(String serviceUserId);

    void updateMain(MmccServiceUserReq mmccServiceUserReq);

}
