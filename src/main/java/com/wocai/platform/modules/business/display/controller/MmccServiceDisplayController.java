package com.wocai.platform.modules.business.display.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.display.dto.MmccServiceDisplayReq;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.service.IMmccServiceUserService;
import com.wocai.platform.modules.business.display.vo.MmccServiceDisplayRes;
import com.wocai.platform.modules.business.display.service.IMmccServiceDisplayService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  服务展示
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="房型服务管理")
@RestController
@RequestMapping("/service/display")
public class MmccServiceDisplayController extends BaseController<MmccServiceDisplay, IMmccServiceDisplayService> {
	@Autowired
	private IMmccServiceDisplayService mmccServiceDisplayService;
	@Autowired
	private IMmccServiceUserService mmccServiceUserService;
	
	/**
     * 分页列表查询
     *
     * @param mmccServiceDisplayReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "服务展示-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "服务展示-分页列表查询", notes = "服务展示-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<MmccServiceDisplayRes>> queryPageList(MmccServiceDisplayReq mmccServiceDisplayReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<MmccServiceDisplayRes> page = new Page<>(pageNo, pageSize);
        IPage<MmccServiceDisplayRes> pageList = mmccServiceDisplayService.queryPageList(page, mmccServiceDisplayReq);
        return Result.toSuccess(pageList);
    }

//     /**
//      * 经纬度，到店位置计算
//      *
//      * @param displayId
//      * @param longitude
//      * @param latitude
//      * @return
//      */
//     @AutoLog(value = "服务展示-到店位置计算", recordFlag = CommonConstant.STATUS_0)
//     @ApiOperation(value = "服务展示-到店位置计算", notes = "服务展示-到店位置计算")
//     @GetMapping(value = "/distance")
//     public Result queryDistanceByDisplayId (@RequestParam(name = "displayId") String displayId ,
//                                             @RequestParam(name = "longitude") String longitude,
//                                             @RequestParam(name = "latitude") String latitude) {
//         String distance = mmccServiceDisplayService.queryDistanceByDisplayId(longitude,latitude,displayId);
//         return Result.toSuccess(distance);
//     }

    /**
     * 添加
     *
     * @param mmccServiceDisplayReq
     * @return
     */
    @AutoLog(value = "服务展示-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务展示-添加", notes = "服务展示-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody MmccServiceDisplayReq mmccServiceDisplayReq) {
        mmccServiceDisplayService.saveMain(mmccServiceDisplayReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param mmccServiceDisplayReq
     * @return
     */
    @AutoLog(value = "服务展示-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务展示-编辑", notes = "服务展示-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody MmccServiceDisplayReq mmccServiceDisplayReq) {
        mmccServiceDisplayService.updateMain(mmccServiceDisplayReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "服务展示-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务展示-通过id删除", notes = "服务展示-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        mmccServiceDisplayService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "服务展示-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "服务展示-批量删除", notes = "服务展示-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        mmccServiceDisplayService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "服务展示-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "服务展示-通过id查询", notes = "服务展示-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<MmccServiceDisplayRes> queryById(@RequestParam(name = "id", required = true) String id) {
        MmccServiceDisplayRes mmccServiceDisplayRes = mmccServiceDisplayService.getMainById(id);
        return Result.toSuccess(mmccServiceDisplayRes);
    }

     @AutoLog(value = "服务展示-停用启用", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "服务展示-停用启用", notes = "服务展示-停用启用")
     @GetMapping(value = "/enable")
     public Result enable(@RequestParam(name = "id", required = true) String id) {
         mmccServiceDisplayService.enable(id);
         return Result.toSuccess();
     }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, MmccServiceDisplay mmccServiceDisplay) {
		 return super.exportXls(request, mmccServiceDisplay, MmccServiceDisplay.class,"服务展示列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, MmccServiceDisplay.class);
//	 }


     /**
      * 通过id查询
      *
      * @param id
      * @return
      */
     @AutoLog(value = "用户预约信息-通过id查询", recordFlag = CommonConstant.STATUS_0)
     @ApiOperation(value = "用户预约信息-通过id查询", notes = "用户预约信息-通过id查询")
     @GetMapping(value = "/queryServiceUserById")
     public Result<MmccServiceDisplayRes> queryServiceUserById(@RequestParam(name = "id", required = true) String id) {
         MmccServiceUserRes mmccServiceUserRes = mmccServiceUserService.getMainById(id);
         return Result.toSuccess(mmccServiceUserRes);
     }




}