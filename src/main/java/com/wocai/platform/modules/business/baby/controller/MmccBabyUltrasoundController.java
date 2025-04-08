package com.wocai.platform.modules.business.baby.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.baby.dto.MmccBabyUltrasoundReq;
import com.wocai.platform.modules.business.baby.entity.MmccBabyUltrasound;
import com.wocai.platform.modules.business.baby.vo.MmccBabyUltrasoundRes;
import com.wocai.platform.modules.business.baby.service.IMmccBabyUltrasoundService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  宝宝B超
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="宝宝B超")
@RestController
@RequestMapping("/baby/ultrasound")
public class MmccBabyUltrasoundController extends BaseController<MmccBabyUltrasound, IMmccBabyUltrasoundService> {
	@Autowired
	private IMmccBabyUltrasoundService mmccBabyUltrasoundService;
	
	/**
     * 分页列表查询
     *
     * @param mmccBabyUltrasoundReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "宝宝B超-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宝宝B超-分页列表查询", notes = "宝宝B超-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccBabyUltrasoundRes>> queryPageList(MmccBabyUltrasoundReq mmccBabyUltrasoundReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccBabyUltrasoundRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccBabyUltrasoundRes> pageList = mmccBabyUltrasoundService.queryPageList(page, mmccBabyUltrasoundReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccBabyUltrasoundReq
     * @return
     */
    @AutoLog(value = "宝宝B超-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宝宝B超-添加", notes = "宝宝B超-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        mmccBabyUltrasoundService.saveMain(mmccBabyUltrasoundReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccBabyUltrasoundReq
     * @return
     */
    @AutoLog(value = "宝宝B超-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宝宝B超-编辑", notes = "宝宝B超-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccBabyUltrasoundReq mmccBabyUltrasoundReq) {
        mmccBabyUltrasoundService.updateMain(mmccBabyUltrasoundReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "宝宝B超-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宝宝B超-通过id删除", notes = "宝宝B超-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccBabyUltrasoundService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "宝宝B超-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "宝宝B超-批量删除", notes = "宝宝B超-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccBabyUltrasoundService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "宝宝B超-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "宝宝B超-通过id查询", notes = "宝宝B超-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccBabyUltrasoundRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccBabyUltrasoundRes mmccBabyUltrasoundRes = mmccBabyUltrasoundService.getMainById(id);
        return Result.toSuccess(mmccBabyUltrasoundRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccBabyUltrasound mmccBabyUltrasound) {
		 return super.exportXls(request, mmccBabyUltrasound, MmccBabyUltrasound.class,"宝宝B超列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccBabyUltrasound.class);
//	 }

}