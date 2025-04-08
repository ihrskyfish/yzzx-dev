package com.wocai.platform.modules.business.nurse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseDetailsReq;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseDetails;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseDetailsRes;
import com.wocai.platform.modules.business.nurse.service.IMmccNurseDetailsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  护理内容
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="知识内容")
@RestController
@RequestMapping("/nurse/details")
public class MmccNurseDetailsController extends BaseController<MmccNurseDetails, IMmccNurseDetailsService> {
	@Autowired
	private IMmccNurseDetailsService mmccNurseDetailsService;
	
	/**
     * 分页列表查询
     *
     * @param mmccNurseDetailsReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "护理内容-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理内容-分页列表查询", notes = "护理内容-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccNurseDetailsRes>> queryPageList(MmccNurseDetailsReq mmccNurseDetailsReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccNurseDetailsRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccNurseDetailsRes> pageList = mmccNurseDetailsService.queryPageList(page, mmccNurseDetailsReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccNurseDetailsReq
     * @return
     */
    @AutoLog(value = "护理内容-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理内容-添加", notes = "护理内容-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccNurseDetailsReq mmccNurseDetailsReq) {
        mmccNurseDetailsService.saveMain(mmccNurseDetailsReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccNurseDetailsReq
     * @return
     */
    @AutoLog(value = "护理内容-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理内容-编辑", notes = "护理内容-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccNurseDetailsReq mmccNurseDetailsReq) {
        mmccNurseDetailsService.updateMain(mmccNurseDetailsReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理内容-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理内容-通过id删除", notes = "护理内容-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccNurseDetailsService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "护理内容-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "护理内容-批量删除", notes = "护理内容-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccNurseDetailsService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "护理内容-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "护理内容-通过id查询", notes = "护理内容-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccNurseDetailsRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccNurseDetailsRes mmccNurseDetailsRes = mmccNurseDetailsService.getMainById(id);
        return Result.toSuccess(mmccNurseDetailsRes);
    }

     @AutoLog(value = "设为推荐", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "设为推荐", notes = "设为推荐")
     @GetMapping(value = "/essenceById")
     public Result essenceById(@RequestParam(name = "id", required = true) String id) {
         mmccNurseDetailsService.essenceById(id);
         return Result.toSuccess();
     }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccNurseDetails mmccNurseDetails) {
		 return super.exportXls(request, mmccNurseDetails, MmccNurseDetails.class,"护理内容列表");
	 }

	 /**
	  * excel导入
	  * @param
	  * @param
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccNurseDetails.class);
//	 }


}