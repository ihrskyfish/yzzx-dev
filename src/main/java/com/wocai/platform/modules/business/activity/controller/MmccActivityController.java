package com.wocai.platform.modules.business.activity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.activity.dto.MmccActivityReq;
import com.wocai.platform.modules.business.activity.dto.MmccActivityResultsDto;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.vo.MmccActivityRes;
import com.wocai.platform.modules.business.activity.service.IMmccActivityService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.activity.vo.MmccActivityUserListVo;
import lombok.extern.slf4j.Slf4j;


import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Description: 孕期活动
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "孕期活动")
@RestController
@RequestMapping("/activity")
public class MmccActivityController extends BaseController<MmccActivity, IMmccActivityService> {
    @Autowired
    private IMmccActivityService mmccActivityService;

    /**
     * 分页列表查询
     *
     * @param mmccActivityReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "孕期活动-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期活动-分页列表查询", notes = "孕期活动-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccActivityRes>> queryPageList(MmccActivityReq mmccActivityReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccActivityRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccActivityRes> pageList = mmccActivityService.queryPageList(page, mmccActivityReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccActivityReq
     * @return
     */
    @AutoLog(value = "孕期活动-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期活动-添加", notes = "孕期活动-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccActivityReq mmccActivityReq) {
        mmccActivityService.saveMain(mmccActivityReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccActivityReq
     * @return
     */
    @AutoLog(value = "孕期活动-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期活动-编辑", notes = "孕期活动-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccActivityReq mmccActivityReq) {
        mmccActivityService.updateMain(mmccActivityReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "孕期活动-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期活动-通过id删除", notes = "孕期活动-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccActivityService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "孕期活动-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "孕期活动-批量删除", notes = "孕期活动-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccActivityService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "孕期活动-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期活动-通过id查询", notes = "孕期活动-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccActivityRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccActivityRes mmccActivityRes = mmccActivityService.getMainById(id);
        return Result.toSuccess(mmccActivityRes);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MmccActivity mmccActivity) {
        return super.exportXls(request, mmccActivity, MmccActivity.class, "孕期活动列表");
    }

    /**
     * excel导入
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return Result.toSuccess(super.importExcel(request, response, MmccActivity.class));
    }


    @AutoLog(value = "活动名单-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "活动名单-分页列表查询", notes = "活动名单-分页列表查询")
    @GetMapping(value = "/activityUserList")
    public Result<IPage<MmccActivityUserListVo>> activityUserList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                                  @RequestParam(name = "id") String id) {
        Page<MmccActivityUserListVo> page = new Page<>(pageNo, pageSize);
        IPage<MmccActivityUserListVo> pageList = mmccActivityService.activityUserList(page, id);
        return Result.toSuccess(pageList);
    }

    @AutoLog(value = "添加活动结果", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "添加活动结果", notes = "添加活动结果")
    @PostMapping(value = "/activityResults")
    public Result activityUserList(@RequestBody MmccActivityResultsDto mmccActivityResultsDto){
        mmccActivityService.activityResults(mmccActivityResultsDto);
        return Result.toSuccess();
    }

    @AutoLog(value = "孕期活动-发布", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "孕期活动-发布", notes = "孕期活动-发布")
    @GetMapping(value = "/release")
    public Result release(@RequestParam(name = "id", required = true) String id) {
         mmccActivityService.release(id);
        return Result.toSuccess("发布成功");
    }


}