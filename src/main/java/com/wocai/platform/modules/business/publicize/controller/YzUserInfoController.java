package com.wocai.platform.modules.business.publicize.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.publicize.dto.YzUserInfoReq;
import com.wocai.platform.modules.business.publicize.entity.YzUserInfo;
import com.wocai.platform.modules.business.publicize.vo.YzUserInfoRes;
import com.wocai.platform.modules.business.publicize.service.IYzUserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="用户信息")
@RestController
@RequestMapping("/userInfo")
public class YzUserInfoController extends BaseController<YzUserInfo, IYzUserInfoService> {
	@Autowired
	private IYzUserInfoService yzUserInfoService;
	
	/**
     * 分页列表查询
     *
     * @param yzUserInfoReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "用户信息-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "用户信息-分页列表查询", notes = "用户信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(YzUserInfoReq yzUserInfoReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzUserInfoRes> page = new Page<>(pageNo, pageSize);
        IPage<YzUserInfoRes> pageList = yzUserInfoService.queryPageList(page, yzUserInfoReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yzUserInfoReq
     * @return
     */
    @AutoLog(value = "用户信息-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "用户信息-添加", notes = "用户信息-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzUserInfoReq yzUserInfoReq) {
        yzUserInfoService.saveMain(yzUserInfoReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzUserInfoReq
     * @return
     */
    @AutoLog(value = "用户信息-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "用户信息-编辑", notes = "用户信息-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzUserInfoReq yzUserInfoReq) {
        yzUserInfoService.updateMain(yzUserInfoReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "用户信息-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "用户信息-通过id删除", notes = "用户信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzUserInfoService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "用户信息-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "用户信息-批量删除", notes = "用户信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzUserInfoService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "用户信息-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "用户信息-通过id查询", notes = "用户信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        YzUserInfoRes yzUserInfoRes = yzUserInfoService.getMainById(id);
        return Result.ok(yzUserInfoRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzUserInfo yzUserInfo) {
		 return super.exportXls(request, yzUserInfo, YzUserInfo.class,"用户信息列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, YzUserInfo.class);
	 }

}