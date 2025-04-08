package com.wocai.platform.modules.business.publicize.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.modules.business.publicize.dto.YzServerInfoReq;
import com.wocai.platform.modules.business.publicize.entity.YzServerInfo;
import com.wocai.platform.modules.business.publicize.vo.YzServerInfoRes;
import com.wocai.platform.modules.business.publicize.service.IYzServerInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  房间预定用户信息
 * @Author: why
 * @Date: 2023-06-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="房间预定用户信息")
@RestController
@RequestMapping("/serverInfo")
public class YzServerInfoController extends BaseController<YzServerInfo, IYzServerInfoService> {
	@Autowired
	private IYzServerInfoService yzServerInfoService;
	
	/**
     * 分页列表查询
     *
     * @param yzServerInfoReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "房间预定用户信息-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "房间预定用户信息-分页列表查询", notes = "房间预定用户信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(YzServerInfoReq yzServerInfoReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<YzServerInfoRes> page = new Page<>(pageNo, pageSize);
        IPage<YzServerInfoRes> pageList = yzServerInfoService.queryPageList(page, yzServerInfoReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param yzServerInfoReq
     * @return
     */
    @AutoLog(value = "房间预定用户信息-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间预定用户信息-添加", notes = "房间预定用户信息-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody YzServerInfoReq yzServerInfoReq) {
        yzServerInfoService.saveMain(yzServerInfoReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param yzServerInfoReq
     * @return
     */
    @AutoLog(value = "房间预定用户信息-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间预定用户信息-编辑", notes = "房间预定用户信息-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody YzServerInfoReq yzServerInfoReq) {
        yzServerInfoService.updateMain(yzServerInfoReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "房间预定用户信息-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间预定用户信息-通过id删除", notes = "房间预定用户信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        yzServerInfoService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "房间预定用户信息-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "房间预定用户信息-批量删除", notes = "房间预定用户信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        yzServerInfoService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "房间预定用户信息-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "房间预定用户信息-通过id查询", notes = "房间预定用户信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        YzServerInfoRes yzServerInfoRes = yzServerInfoService.getMainById(id);
        return Result.ok(yzServerInfoRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, YzServerInfo yzServerInfo) {
		 return super.exportXls(request, yzServerInfo, YzServerInfo.class,"房间预定用户信息列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
	 @PostMapping(value = "/importExcel")
	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
		 return super.importExcel(request, response, YzServerInfo.class);
	 }

}