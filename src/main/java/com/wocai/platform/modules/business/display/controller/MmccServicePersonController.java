package com.wocai.platform.modules.business.display.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.display.dto.MmccServicePersonReq;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.vo.MmccServicePersonRes;
import com.wocai.platform.modules.business.display.service.IMmccServicePersonService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.display.vo.MmccServocePersonVO;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Description:  预约接待员管理
 * @Author: why
 * @Date: 2024-02-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="预约接待员管理")
@RestController
@RequestMapping("/servicePerson")
public class MmccServicePersonController extends BaseController<MmccServicePerson, IMmccServicePersonService> {
	@Autowired
	private IMmccServicePersonService mmccServicePersonService;
	
	/**
     * 分页列表查询
     *
     * @param mmccServicePersonReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "预约接待员管理-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预约接待员管理-分页列表查询", notes = "预约接待员管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(MmccServicePersonReq mmccServicePersonReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccServicePersonRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccServicePersonRes> pageList = mmccServicePersonService.queryPageList(page, mmccServicePersonReq);
        return Result.ok(pageList);
    }

     @AutoLog(value = "预约接待人员下拉列表", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "预约接待人员下拉列表", notes = "预约接待人员下拉列表")
     @GetMapping(value = "/personList")
     public Result getServicePersonList() {
         List<MmccServocePersonVO> personList = mmccServicePersonService.getServicePersonList();
         return Result.ok(personList);
     }

    /**
     * 添加
     *
     * @param mmccServicePersonReq
     * @return
     */
    @AutoLog(value = "预约接待员管理-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "预约接待员管理-添加", notes = "预约接待员管理-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccServicePersonReq mmccServicePersonReq) {
        mmccServicePersonService.saveMain(mmccServicePersonReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccServicePersonReq
     * @return
     */
    @AutoLog(value = "预约接待员管理-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "预约接待员管理-编辑", notes = "预约接待员管理-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccServicePersonReq mmccServicePersonReq) {
        mmccServicePersonService.updateMain(mmccServicePersonReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "预约接待员管理-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "预约接待员管理-通过id删除", notes = "预约接待员管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccServicePersonService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "预约接待员管理-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "预约接待员管理-批量删除", notes = "预约接待员管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccServicePersonService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "预约接待员管理-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "预约接待员管理-通过id查询", notes = "预约接待员管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        MmccServicePersonRes mmccServicePersonRes = mmccServicePersonService.getMainById(id);
        return Result.ok(mmccServicePersonRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccServicePerson mmccServicePerson) {
		 return super.exportXls(request, mmccServicePerson, MmccServicePerson.class,"预约接待员管理列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, MmccServicePerson.class);
	 }

}