package com.wocai.platform.modules.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.api.vo.Result;
import com.wocai.platform.common.aspect.annotation.AutoLog;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.system.base.controller.BaseController;
import com.wocai.platform.common.util.Encodes;
import com.wocai.platform.modules.system.dto.SysDatabaseBackReq;
import com.wocai.platform.modules.system.entity.SysDatabaseBack;
import com.wocai.platform.modules.system.service.ISysDatabaseBackService;
import com.wocai.platform.modules.system.vo.SysDatabaseBackRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description: 数据备份
 * @Author: houws
 * @Date: 2022-07-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "数据备份")
@RestController
@RequestMapping("/backup")
public class SysDatabaseBackController extends BaseController<SysDatabaseBack, ISysDatabaseBackService> {

    @Autowired
    private ISysDatabaseBackService sysDatabaseBackService;

    /**
     * 分页列表查询
     *
     * @param sysDatabaseBackReq
     * @param pageNo
     * @param pageSize
     * @return
     */
    @AutoLog(value = "数据备份-分页列表查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "数据备份-分页列表查询", notes = "数据备份-分页列表查询")
    @GetMapping(value = "/list")
    public Result queryPageList(SysDatabaseBackReq sysDatabaseBackReq, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<SysDatabaseBackRes> page = new Page<>(pageNo, pageSize);
        IPage<SysDatabaseBackRes> pageList = sysDatabaseBackService.queryPageList(page, sysDatabaseBackReq);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param sysDatabaseBackReq
     * @return
     */
    @AutoLog(value = "数据备份-添加", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "数据备份-添加", notes = "数据备份-添加")
    @PostMapping(value = "/add")
    public Result add(@RequestBody SysDatabaseBackReq sysDatabaseBackReq) {
        sysDatabaseBackService.saveMain(sysDatabaseBackReq);
        return Result.ok("添加成功!");
    }

    /**
     * 编辑
     *
     * @param sysDatabaseBackReq
     * @return
     */
    @AutoLog(value = "数据备份-编辑", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "数据备份-编辑", notes = "数据备份-编辑")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody SysDatabaseBackReq sysDatabaseBackReq) {
        sysDatabaseBackService.updateMain(sysDatabaseBackReq);
        return Result.ok("修改成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "数据备份-通过id删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "数据备份-通过id删除", notes = "数据备份-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam(name = "id", required = true) String id) {
        sysDatabaseBackService.batchDelete(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "数据备份-批量删除", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "数据备份-批量删除", notes = "数据备份-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        sysDatabaseBackService.batchDelete(ids);
        return Result.ok("删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "数据备份-通过id查询", recordFlag = CommonConstant.STATUS_0)
    @ApiOperation(value = "数据备份-通过id查询", notes = "数据备份-通过id查询")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam(name = "id", required = true) String id) {
        SysDatabaseBackRes sysDatabaseBackRes = sysDatabaseBackService.getMainById(id);
        return Result.ok(sysDatabaseBackRes);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @GetMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SysDatabaseBack sysDatabaseBack) {
        return super.exportXls(request, sysDatabaseBack, SysDatabaseBack.class, "数据备份列表");
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
        return super.importExcel(request, response, SysDatabaseBack.class);
    }


    /**
     * 下载备份文件
     *
     * @param request
     * @param response
     * @param id
     */
    @AutoLog(value = "数据备份-下载备份文件", recordFlag = CommonConstant.STATUS_1)
    @ApiOperation(value = "数据备份-下载备份文件", notes = "数据备份-下载备份文件")
    @GetMapping(value = "/download/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) {
        SysDatabaseBackRes sysDatabaseBackRes = sysDatabaseBackService.getMainById(id);
        if (sysDatabaseBackRes == null) {
            throw new BizException("备份记录未生成");
        }
        File file = new File(sysDatabaseBackRes.getBackPath());
        if (!file.exists()) {
            throw new BizException("文件未生成");
        }
        try {
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Content-Type", "application/octet-stream");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(sysDatabaseBackRes.getBackName()));
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
            return;
        } catch (Exception e) {
            request.setAttribute("exception", new FileNotFoundException("请求的文件不存在"));
            try {
                request.getRequestDispatcher("/error/404.jsp").forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}