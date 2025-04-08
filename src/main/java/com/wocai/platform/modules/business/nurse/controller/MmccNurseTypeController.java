package com.wocai.platform.modules.business.nurse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.nurse.dto.MmccNurseTypeReq;
import com.wocai.platform.modules.business.nurse.entity.MmccNurseType;
import com.wocai.platform.modules.business.nurse.vo.MmccNurseTypeRes;
import com.wocai.platform.modules.business.nurse.service.IMmccNurseTypeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.nurse.vo.MmccTypeVo;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Description:  知识护理类型
 * @Author: lq
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="知识类型")
@RestController
@RequestMapping("/nurse/type")
public class MmccNurseTypeController extends BaseController<MmccNurseType, IMmccNurseTypeService> {
	@Autowired
	private IMmccNurseTypeService mmccNurseTypeService;
	
	/**
     * 分页列表查询
     *
     * @param mmccNurseTypeReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "知识护理类型-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "知识护理类型-分页列表查询", notes = "知识护理类型-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccNurseTypeRes>> queryPageList(MmccNurseTypeReq mmccNurseTypeReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccNurseTypeRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccNurseTypeRes> pageList = mmccNurseTypeService.queryPageList(page, mmccNurseTypeReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param mmccNurseTypeReq
     * @return
     */
    @AutoLog(value = "知识护理类型-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "知识护理类型-添加", notes = "知识护理类型-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccNurseTypeReq mmccNurseTypeReq) {
        mmccNurseTypeService.saveMain(mmccNurseTypeReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccNurseTypeReq
     * @return
     */
    @AutoLog(value = "知识护理类型-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "知识护理类型-编辑", notes = "知识护理类型-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccNurseTypeReq mmccNurseTypeReq) {
        mmccNurseTypeService.updateMain(mmccNurseTypeReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识护理类型-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "知识护理类型-通过id删除", notes = "知识护理类型-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccNurseTypeService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "知识护理类型-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "知识护理类型-批量删除", notes = "知识护理类型-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccNurseTypeService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "知识护理类型-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "知识护理类型-通过id查询", notes = "知识护理类型-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        MmccNurseTypeRes mmccNurseTypeRes = mmccNurseTypeService.getMainById(id);
        return Result.toSuccess(mmccNurseTypeRes);
    }

     @AutoLog(value = "知识护理类型-停用启用", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "知识护理类型-停用启用", notes = "知识护理类型-停用启用")
     @GetMapping(value = "/enable")
     public Result enable(@RequestParam(name = "id", required = true) String id) {
         mmccNurseTypeService.enable(id);
         return Result.toSuccess();
     }

     @AutoLog(value = "获取所有护理类型", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "获取所有护理类型", notes = "获取所有护理类型")
     @GetMapping(value = "/getAllTypeList")
     public Result<List<MmccTypeVo>> getAllTypeList() {
         List<MmccTypeVo> result=mmccNurseTypeService.getAllTypeList();
         return Result.toSuccess(result);
     }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccNurseType mmccNurseType) {
		 return super.exportXls(request, mmccNurseType, MmccNurseType.class,"知识护理类型列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccNurseType.class);
//	 }

}