package com.wocai.platform.modules.business.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.vo.Result;
import com.wocai.platform.modules.business.user.dto.UserReq;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.service.IAppUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.modules.business.user.vo.UserRes;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description:  user
 * @Author: lq
 * @Date: 2023-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="user")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, IAppUserService> {
	@Autowired
	private IAppUserService appUserService;
	
	/**
     * 分页列表查询
     *
     * @param appUserReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "user-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "user-分页列表查询", notes = "user-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<UserRes>> queryPageList(UserReq appUserReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<UserRes> page = new Page<>(pageNo, pageSize);
        IPage<UserRes> pageList = appUserService.queryPageList(page, appUserReq);
        return Result.toSuccess(pageList);
    }

    /**
     * 添加
     *
     * @param appUserReq
     * @return
     */
    @AutoLog(value = "user-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "user-添加", notes = "user-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody UserReq appUserReq) {
        appUserService.saveMain(appUserReq);
        return Result.toSuccess("添加成功!");
    }

    /**
     * 编辑
     *
     * @param appUserReq
     * @return
     */
    @AutoLog(value = "user-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "user-编辑", notes = "user-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody UserReq appUserReq) {
        appUserService.updateMain(appUserReq);
        return Result.toSuccess("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "user-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "user-通过id删除", notes = "user-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        appUserService.batchDelete(id);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "user-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "user-批量删除", notes = "user-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        appUserService.batchDelete(ids);
        return Result.toSuccess("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "user-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "user-通过id查询", notes = "user-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        UserRes appUserRes = appUserService.getMainById(id);
        return Result.toSuccess(appUserRes);
    }

    /**
	  * 导出excel
	  *
	  * @param request
	  */
	 @GetMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, User appUser) {
		 return super.exportXls(request, appUser, User.class,"user列表");
	 }

	 /**
	  * excel导入
	  * @param request
	  * @param response
	  * @return
	  */
//	 @PostMapping(value = "/importExcel")
//	 public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//		 return super.importExcel(request, response, User.class);
//	 }

}