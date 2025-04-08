package com.wocai.platform.modules.business.book.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.app.display.vo.AppDisplayBookQueryByIdVo;
import com.wocai.platform.modules.business.book.dto.BookListDto;
import com.wocai.platform.modules.business.book.service.BookService;
import com.wocai.platform.modules.business.book.vo.BookEvaluationRecordsVo;
import com.wocai.platform.modules.business.book.vo.ReservationRecordVo;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayReq;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseTypeReq;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseTypeRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 15:42
 **/

@Slf4j
@Api(tags="预订管理")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @AutoLog(value = "预订记录-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预订记录-分页列表查询", notes = "预订记录-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<ReservationRecordVo>> reservationRecord(BookListDto bookListDto,
                                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<ReservationRecordVo> page = new Page<>(pageNo, pageSize);
        IPage<ReservationRecordVo> pageList = bookService.reservationRecord(page,bookListDto);
        return Result.toSuccess(pageList);
    }


    @AutoLog(value = "预定管理-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预定管理-通过id查询", notes = "预定管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccServiceUserRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccServiceUserRes mmccServiceUserRes = bookService.getMainById(id);
        return Result.toSuccess(mmccServiceUserRes);
    }

    @AutoLog(value = "评价记录-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "评价记录-分页列表查询", notes = "评价记录-分页列表查询")
    @GetMapping(value = "/evaluationRecordsVo")
    public Result<IPage<BookEvaluationRecordsVo>> evaluationRecordsVo(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<BookEvaluationRecordsVo> page = new Page<>(pageNo, pageSize);
        IPage<BookEvaluationRecordsVo> pageList = bookService.evaluationRecordsVo(page);
        return Result.toSuccess(pageList);
    }


    @AutoLog(value = "到店", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "到店", notes = "到店")
    @GetMapping(value = "/toStore")
    public Result toStore(@RequestParam(value = "serviceUserId", required = true) String serviceUserId) {
        bookService.toStore(serviceUserId);
        return Result.toSuccess("确认到店成功");
    }


    /**
     * 编辑
     *
     * @param mmccServiceUserReq
     * @return
     */
    @AutoLog(value = "用户预约信息-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "用户预约信息-编辑", notes = "用户预约信息-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccServiceUserReq mmccServiceUserReq) {
        bookService.updateMain(mmccServiceUserReq);
        return Result.toSuccess("修改成功!");
    }
}
