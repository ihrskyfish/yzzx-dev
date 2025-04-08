package com.wocai.platform.modules.business.health.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.health.dto.MmccExportWordDto;
import com.wocai.platform.modules.business.health.dto.MmccHealthCheckReq;
import com.wocai.platform.modules.business.health.entity.MmccHealthCheck;
import com.wocai.platform.modules.business.health.vo.*;
import com.wocai.platform.modules.business.health.service.IMmccHealthCheckService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Description: 健康打卡
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "健康打卡")
@RestController
@RequestMapping("/health/check")
public class MmccHealthCheckController extends BaseController<MmccHealthCheck, IMmccHealthCheckService> {
    @Autowired
    private IMmccHealthCheckService mmccHealthCheckService;

    /**
     * 分页列表查询
     *
     * @param mmccHealthCheckReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "健康打卡-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "健康打卡-分页列表查询", notes = "健康打卡-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccHealthCheckRes>> queryPageList(MmccHealthCheckReq mmccHealthCheckReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccHealthCheckRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccHealthCheckRes> pageList = mmccHealthCheckService.queryPageList(page, mmccHealthCheckReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccHealthCheckReq
     * @return
     */
    @AutoLog(value = "健康打卡-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "健康打卡-添加", notes = "健康打卡-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccHealthCheckReq mmccHealthCheckReq) {
        mmccHealthCheckService.saveMain(mmccHealthCheckReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccHealthCheckReq
     * @return
     */
    @AutoLog(value = "健康打卡-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "健康打卡-编辑", notes = "健康打卡-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccHealthCheckReq mmccHealthCheckReq) {
        mmccHealthCheckService.updateMain(mmccHealthCheckReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "健康打卡-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "健康打卡-通过id删除", notes = "健康打卡-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccHealthCheckService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "健康打卡-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "健康打卡-批量删除", notes = "健康打卡-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccHealthCheckService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "健康打卡-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "健康打卡-通过id查询", notes = "健康打卡-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccHealthCheckRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccHealthCheckRes mmccHealthCheckRes = mmccHealthCheckService.getMainById(id);
        return Result.toSuccess(mmccHealthCheckRes);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmccHealthCheck mmccHealthCheck) {
        return super.exportXls(request, mmccHealthCheck, MmccHealthCheck.class, "健康打卡列表");
    }

    /**
     * excel导入
     *
     * @param
     * @param
     * @return
     */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccHealthCheck.class);
//	 }
    @AutoLog(value = "会员列表", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "会员列表", notes = "会员列表")
    @GetMapping(value = "/babyUserList")
    public Result<IPage<MmccBaByUserVo>> babyUserList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccBaByUserVo> page = new Page<>(pageNo, pageSize);
        IPage<MmccBaByUserVo> pageList = mmccHealthCheckService.babyUserList(page);
        return Result.toSuccess(pageList);
    }


    @AutoLog(value = "心情档案", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "心情档案", notes = "心情档案")
    @GetMapping(value = "/moodList")
    public Result<IPage<MmccMoodVo>> moodList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                              @RequestParam(name = "userId") String userId) {
        Page<MmccHealthCheck> page = new Page<>(pageNo, pageSize);
        IPage<MmccMoodVo> pageList = mmccHealthCheckService.moodList(page, userId);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "孕期档案", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期档案", notes = "孕期档案")
    @GetMapping(value = "/pregnancyArchivesList")
    public Result<List<MmccPregnancyArchivesVo>> pregnancyArchivesList(@RequestParam(name = "userId") String userId) {
        List<MmccPregnancyArchivesVo> pageList = mmccHealthCheckService.pregnancyArchivesListV2(userId);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "宝宝档案", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宝宝档案", notes = "宝宝档案")
    @GetMapping(value = "/babyProfileList")
    public Result<IPage<MmccBabyProfileVo>> babyProfileList(@RequestParam(name = "userId") String userId) {
//        Page<MmccBabyUltrasound> page = new Page<>(pageNo, pageSize);
        List<MmccBabyProfileVo> pageList = mmccHealthCheckService.babyProfileList(userId);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "导出word", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "导出word", notes = "导出word")
    @PostMapping(value = "/exportWord")
    public Result exportWord(@RequestBody MmccExportWordDto mmccExportWordDto){
        String path = mmccHealthCheckService.exportWord(mmccExportWordDto);
        return Result.toSuccess(path);
    }


}