package com.wocai.platform.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.feign.client.HandleShellClient;
import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;
import com.wocai.platform.common.util.BizFileUtils;
import com.wocai.platform.common.util.StreamGobbler;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.common.util.ZipUtils;
import com.wocai.platform.modules.system.dto.SysVersionReq;
import com.wocai.platform.modules.system.entity.SysConfig;
import com.wocai.platform.modules.system.entity.SysVersion;
import com.wocai.platform.modules.system.mapper.SysConfigMapper;
import com.wocai.platform.modules.system.mapper.SysVersionMapper;
import com.wocai.platform.modules.system.service.ISysVersionService;
import com.wocai.platform.modules.system.vo.SysVersionRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 服务新增记录管理
 * @Author: wzw
 * @Date: 2021-11-08
 * @Version: V1.0
 */
@Service
@Slf4j
public class SysVersionServiceImpl extends BaseServiceImpl<SysVersionMapper, SysVersion> implements ISysVersionService {
    @Resource
    SysConfigMapper configMapper;
    @Resource
    HandleShellClient handleShellClient;

    @Override
    public IPage<SysVersionRes> queryPageList(IPage<SysVersionRes> page, SysVersionReq sysVersionReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotBlank(sysVersionReq.getSysType())) {
            reqParam.put("sysType", sysVersionReq.getSysType());
        }
        if (StringUtils.isNotBlank(sysVersionReq.getRemark())) {
            reqParam.put("remark", sysVersionReq.getRemark());
        }
        IPage<SysVersionRes> resultList = this.baseMapper.queryPage(page, reqParam);
        return resultList;
    }

    @Override
    @Transactional
    public void uploadFile(SysVersionReq req) throws UnsupportedEncodingException, FileNotFoundException {
        MultipartFile file = req.getFile();
        //上传的文件名
        String filename = file.getOriginalFilename();
        //文件的保存路径
        String uploadPath = "";
        if (req.getSysType().equals("1")) {
            uploadPath = getConfig("web_file_path");
        } else {
            uploadPath = getConfig("java_file_path");
        }
        /*解决文件路径中的空格问题*/
        String decodeClassespath = URLDecoder.decode(uploadPath, "utf-8");
        String jarPath = decodeClassespath + (decodeClassespath.endsWith("/") ? "" : "/");
        File jarFile = new File(jarPath.substring(0, jarPath.length() - 1));
        if (!jarFile.exists()) {
            jarFile.mkdirs();
        }
        File file1 = new File(jarPath + filename);
        if (!filename.endsWith("zip")) {
            //处理后台jar包
            try {
                file.transferTo(file1);
            } catch (IOException e) {
                log.error("上传服务版本文件失败,错误原因:{}", e.getMessage());
                throw new BizException("上传失败");
            }
            String shellPath = getConfig("java_shell_path");
            handleShellClient.handleShell("cd " + jarPath + " & sh " + shellPath + " restart " + filename);
        } else {
            //处理前端zip包(包内名称必须是dist文件夹)
            try {
                ZipUtils.unZipFiles(new ZipFile(BizFileUtils.MultipartFileToFile(file)), jarPath);
            } catch (IOException e) {
                log.error("上传服务版本文件失败,错误原因:{}", e.getMessage());
                throw new BizException("上传失败");
            }
            doShell("cd " + jarPath + " & chmod -R 777 dist");
        }

        req.setFileUrl(jarPath + filename);
        saveMain(req);
    }

    public void doShell(String cmd) {
        String[] cmdA = {"/bin/sh", "-c", cmd};
        log.info("准备执行命令行 " + cmd);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
        errorGobbler.start();
        StreamGobbler outGobbler = new StreamGobbler(process.getInputStream(), "STDOUT");
        outGobbler.start();
    }

    public String getConfig(String key) {
        LambdaQueryWrapper<SysConfig> sysConfigLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysConfigLambdaQueryWrapper.eq(SysConfig::getDelFlag, CommonConstant.DEL_FLAG_0);
        sysConfigLambdaQueryWrapper.eq(SysConfig::getKeyName, key);
        SysConfig config = configMapper.selectOne(sysConfigLambdaQueryWrapper);
        return config.getKeyValue();
    }

    @Override
    public boolean doDuplicateCheck(SysVersionReq sysVersionReq) {
        return false;
    }


    @Override
    @Transactional
    public void saveMain(SysVersionReq sysVersionReq) {
        this.doDuplicateCheck(sysVersionReq);
        SysVersion sysVersion = new SysVersion();
        BeanUtils.copyProperties(sysVersionReq, sysVersion);
        this.save(sysVersion);
    }

    @Override
    public void updateMain(SysVersionReq sysVersionReq) {
        SysVersion sysVersion = this.getById(sysVersionReq.getId());
        if (sysVersion == null || CommonConstant.DEL_FLAG_1.equals(sysVersion.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(sysVersionReq);
        BeanUtils.copyProperties(sysVersionReq, sysVersion);
        this.updateById(sysVersion);
    }

    @Override
    public SysVersionRes getMainById(String id) {
        SysVersionRes result = new SysVersionRes();
        SysVersion sysVersion = this.getById(id);
        if (sysVersion == null || CommonConstant.DEL_FLAG_1.equals(sysVersion.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(sysVersion, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            SysVersion sysVersion = this.getById(id);
            if (sysVersion == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<SysVersion> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysVersion::getId, idList);
        SysVersion sysVersion = new SysVersion();
        sysVersion.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(sysVersion, queryWrapper);
    }


}