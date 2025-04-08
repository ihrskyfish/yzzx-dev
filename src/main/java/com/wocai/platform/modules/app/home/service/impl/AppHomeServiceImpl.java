package com.wocai.platform.modules.app.home.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.modules.app.home.service.AppHomeService;
import com.wocai.platform.modules.app.home.vo.AppProcessColumnVo;
import com.wocai.platform.modules.app.home.vo.AppRotationChartVo;
import com.wocai.platform.modules.business.rotation.entity.MmccRotationChart;
import com.wocai.platform.modules.business.rotation.mapper.MmccRotationChartMapper;
import com.wocai.platform.modules.business.technology.entity.MmccTechnology;
import com.wocai.platform.modules.business.technology.entity.MmccTechnologyFiles;
import com.wocai.platform.modules.business.technology.mapper.MmccTechnologyFilesMapper;
import com.wocai.platform.modules.business.technology.mapper.MmccTechnologyMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-22 16:53
 **/
@Service
public class AppHomeServiceImpl implements AppHomeService {

    @Resource
    private MmccTechnologyMapper mmccTechnologyMapper;

    @Resource
    private MmccTechnologyFilesMapper mmccTechnologyFilesMapper;

    @Resource
    private MmccRotationChartMapper mmccRotationChartMapper;

    @Override
    public List<AppProcessColumnVo> processColumnList() {
        List<MmccTechnology> mmccTechnologies = mmccTechnologyMapper.selectList(
                Wrappers.<MmccTechnology>lambdaQuery()
                        .eq(MmccTechnology::getDelFlag, 0)
                        .orderByDesc(MmccTechnology::getSort)
                        .orderByDesc(MmccTechnology::getCreateTime));
        List<AppProcessColumnVo> result = mmccTechnologies.stream()
                .map(item -> {
                    AppProcessColumnVo appProcessColumnVo = new AppProcessColumnVo();
                    BeanUtils.copyProperties(item, appProcessColumnVo);
                    if (StringUtils.isNotEmpty(item.getUrl())) {
                        appProcessColumnVo.setUrl(ServerConfig.plat_upload_head_path + "/" + item.getUrl());
                    }
                    List<MmccTechnologyFiles> files = mmccTechnologyFilesMapper.selectList
                            (Wrappers.<MmccTechnologyFiles>lambdaQuery()
                                    .eq(MmccTechnologyFiles::getDelFlag, 0)
                                    .eq(MmccTechnologyFiles::getTechnologyId, item.getId()))
                            .stream().map(file -> {
                                file.setFileUrl(ServerConfig.plat_upload_head_path + "/" + file.getFileUrl());
                                return file;
                            }).collect(Collectors.toList());
                    appProcessColumnVo.setFiles(files);
                    return appProcessColumnVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<AppRotationChartVo> rotationChartList() {
        List<AppRotationChartVo> result = mmccRotationChartMapper.selectList(
                Wrappers.<MmccRotationChart>lambdaQuery()
                        .eq(MmccRotationChart::getDelFlag, 0)
                        .orderByDesc(MmccRotationChart::getCreateTime)
                        .last("limit 10"))
                .stream()
                .map(item -> {
                    AppRotationChartVo appRotationChartVo = new AppRotationChartVo();
                    BeanUtils.copyProperties(item, appRotationChartVo);
                    appRotationChartVo.setFileUrl(ServerConfig.plat_upload_head_path + "/" + item.getFileUrl());
                    return appRotationChartVo;
                })
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public AppProcessColumnVo processColumnQueryById(String id) {
        AppProcessColumnVo result = new AppProcessColumnVo();
        MmccTechnology mmccTechnology = mmccTechnologyMapper.selectById(id);
        if (mmccTechnology == null || !"0".equalsIgnoreCase(mmccTechnology.getDelFlag().toString())) {
            throw new BizException("该公益栏目不存在");
        }
        BeanUtils.copyProperties(mmccTechnology, result);
        if (StringUtils.isNotEmpty(result.getUrl())) {
            result.setUrl(ServerConfig.plat_upload_head_path + "/" + result.getUrl());
        }
        List<MmccTechnologyFiles> files = mmccTechnologyFilesMapper.selectList
                (Wrappers.<MmccTechnologyFiles>lambdaQuery()
                        .eq(MmccTechnologyFiles::getDelFlag, 0)
                        .eq(MmccTechnologyFiles::getTechnologyId, mmccTechnology.getId()))
                .stream().map(file -> {
                    file.setFileUrl(ServerConfig.plat_upload_head_path + "/" + file.getFileUrl());
                    return file;
                }).collect(Collectors.toList());
        result.setFiles(files);
        return result;
    }
}
