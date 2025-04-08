package com.wocai.platform.modules.business.rotation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.rotation.dto.MmccRotationChartReq;
import com.wocai.platform.modules.business.rotation.entity.MmccRotationChart;
import com.wocai.platform.modules.business.rotation.vo.MmccRotationChartRes;
import com.wocai.platform.modules.business.rotation.service.IMmccRotationChartService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  轮播图
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="轮播图")
@RestController
@RequestMapping("/rotation/chart")
public class MmccRotationChartController extends BaseController<MmccRotationChart, IMmccRotationChartService> {
	@Autowired
	private IMmccRotationChartService mmccRotationChartService;
	
	/**
     * 分页列表查询
     *
     * @param mmccRotationChartReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "轮播图-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "轮播图-分页列表查询", notes = "轮播图-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccRotationChartRes>> queryPageList(MmccRotationChartReq mmccRotationChartReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccRotationChartRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccRotationChartRes> pageList = mmccRotationChartService.queryPageList(page, mmccRotationChartReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccRotationChartReq
     * @return
     */
    @AutoLog(value = "轮播图-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "轮播图-添加", notes = "轮播图-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccRotationChartReq mmccRotationChartReq) {
        mmccRotationChartService.saveMain(mmccRotationChartReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccRotationChartReq
     * @return
     */
    @AutoLog(value = "轮播图-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "轮播图-编辑", notes = "轮播图-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccRotationChartReq mmccRotationChartReq) {
        mmccRotationChartService.updateMain(mmccRotationChartReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "轮播图-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "轮播图-通过id删除", notes = "轮播图-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccRotationChartService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "轮播图-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "轮播图-批量删除", notes = "轮播图-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccRotationChartService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "轮播图-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "轮播图-通过id查询", notes = "轮播图-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        MmccRotationChartRes mmccRotationChartRes = mmccRotationChartService.getMainById(id);
        return Result.toSuccess(mmccRotationChartRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccRotationChart mmccRotationChart) {
		 return super.exportXls(request, mmccRotationChart, MmccRotationChart.class,"轮播图列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccRotationChart.class);
//	 }

}