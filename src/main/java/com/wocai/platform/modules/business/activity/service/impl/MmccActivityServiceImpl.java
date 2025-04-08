package com.wocai.platform.modules.business.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.ServerConfig;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.activity.dto.FileUrlVo;
import com.wocai.platform.modules.business.activity.dto.MmccActivityResultsDto;
import com.wocai.platform.modules.business.activity.entity.MmccActivity;
import com.wocai.platform.modules.business.activity.dto.MmccActivityReq;
import com.wocai.platform.modules.business.activity.entity.MmccActivityFile;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityFileMapper;
import com.wocai.platform.modules.business.activity.vo.MmccActivityRes;
import com.wocai.platform.modules.business.activity.mapper.MmccActivityMapper;
import com.wocai.platform.modules.business.activity.service.IMmccActivityService;
import com.wocai.platform.modules.business.activity.vo.MmccActivityUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 孕期活动
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Service
public class MmccActivityServiceImpl extends BaseServiceImpl<MmccActivityMapper, MmccActivity> implements IMmccActivityService {

    @Resource
    private MmccActivityFileMapper mmccActivityFileMapper;

    @Override
    public IPage<MmccActivityRes> queryPageList(IPage<MmccActivityRes> page, MmccActivityReq mmccActivityReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccActivityReq.getName())){
            reqParam.put("name",mmccActivityReq.getName());
        }
        IPage<MmccActivityRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(item->{
            if (StringUtils.isNotEmpty(item.getFileUrl())){
                item.setFileUrl(ServerConfig.plat_upload_head_path+"/"+item.getFileUrl());
            }

            List<FileUrlVo> collect = mmccActivityFileMapper.selectList(
                    Wrappers.<MmccActivityFile>lambdaQuery()
                            .eq(MmccActivityFile::getActivityId, item.getId())
                            .eq(MmccActivityFile::getDelFlag, 0))
                    .stream()
                    .map(vo -> {
                        FileUrlVo fileUrlVo = new FileUrlVo();
                        fileUrlVo.setKey(vo.getFileUrl());
                        fileUrlVo.setUrl(ServerConfig.plat_upload_head_path +"/"+ vo.getFileUrl());
                        return fileUrlVo;
                    })
                    .collect(Collectors.toList());
            item.setFileUrls(collect);
        });

        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccActivityReq mmccActivityReq) {
        return false;
    }

    @Override
    public void saveMain(MmccActivityReq mmccActivityReq) {
        this.doDuplicateCheck(mmccActivityReq);
        MmccActivity mmccActivity = new MmccActivity();
        BeanUtils.copyProperties(mmccActivityReq, mmccActivity);

        Date endTime = mmccActivity.getEndTime();
        LocalDateTime localDateTime = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (LocalDateTime.now().isAfter(localDateTime)){
            throw new BizException("截止时间应该在当前时间后面");
        }

        this.save(mmccActivity);
    }

    @Override
    public void updateMain(MmccActivityReq mmccActivityReq) {
        MmccActivity mmccActivity = this.getById(mmccActivityReq.getId());
        if (mmccActivity == null || CommonConstant.DEL_FLAG_1.equals(mmccActivity.getDelFlag())) {
            throw new BizException("数据不存在");
        }

        if (!"0".equalsIgnoreCase(mmccActivity.getStatus())){
            throw new BizException("非草稿活动 不可以编辑");
        }

        this.doDuplicateCheck(mmccActivityReq);
        BeanUtils.copyProperties(mmccActivityReq, mmccActivity);

        Date endTime = mmccActivity.getEndTime();
        LocalDateTime localDateTime = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (LocalDateTime.now().isAfter(localDateTime)){
            throw new BizException("截止时间应该在当前时间后面");
        }

        this.updateById(mmccActivity);
    }

    @Override
    public MmccActivityRes getMainById(String id) {
        MmccActivityRes result = new MmccActivityRes();
        MmccActivity mmccActivity = this.getById(id);
        if (mmccActivity == null || CommonConstant.DEL_FLAG_1.equals(mmccActivity.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccActivity, result);

        List<FileUrlVo> collect = mmccActivityFileMapper.selectList(
                Wrappers.<MmccActivityFile>lambdaQuery()
                        .eq(MmccActivityFile::getActivityId, id)
                        .eq(MmccActivityFile::getDelFlag, 0))
                .stream()
                .map(vo -> {
                    FileUrlVo fileUrlVo = new FileUrlVo();
                    fileUrlVo.setKey(vo.getFileUrl());
                    fileUrlVo.setUrl(ServerConfig.plat_upload_head_path +"/"+ vo.getFileUrl());
                    return fileUrlVo;
                })
                .collect(Collectors.toList());
        result.setFileUrls(collect);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccActivity mmccActivity = this.getById(id);
            if (mmccActivity == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccActivity::getId, idList);
        MmccActivity mmccActivity = new MmccActivity();
        mmccActivity.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccActivity, queryWrapper);
    }

    @Override
    public IPage<MmccActivityUserListVo> activityUserList(Page<MmccActivityUserListVo> page,String id) {
        IPage<MmccActivityUserListVo> result=baseMapper.activityUserList(page,id);
        return result;
    }

    @Override
    public void activityResults(MmccActivityResultsDto mmccActivityResultsDto) {
        MmccActivity mmccActivity = baseMapper.selectById(mmccActivityResultsDto.getId());
        if (mmccActivity == null || CommonConstant.DEL_FLAG_1.equals(mmccActivity.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        mmccActivity.setStatus("20");
        mmccActivity.setEndState("1");
        mmccActivity.setAchieve(mmccActivityResultsDto.getAchieve());
        baseMapper.updateById(mmccActivity);

        mmccActivityFileMapper.delete(
                Wrappers.<MmccActivityFile>lambdaQuery()
                        .eq(MmccActivityFile::getActivityId,mmccActivityResultsDto.getId())
                        .eq(MmccActivityFile::getDelFlag,0));

        for (String url : mmccActivityResultsDto.getFileUrls()) {
            MmccActivityFile mmccActivityFile = new MmccActivityFile();
            mmccActivityFile.setActivityId(mmccActivityResultsDto.getId());
            mmccActivityFile.setFileUrl(url);
            mmccActivityFileMapper.insert(mmccActivityFile);
        }
    }

    @Override
    public void release(String id) {
        MmccActivity mmccActivity = baseMapper.selectById(id);
        if (mmccActivity == null || CommonConstant.DEL_FLAG_1.equals(mmccActivity.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        if (!"0".equalsIgnoreCase(mmccActivity.getStatus())){
            throw new BizException("该活动已发布，请勿重复发布");
        }

        Date endTime = mmccActivity.getEndTime();
        LocalDateTime localDateTime = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (LocalDateTime.now().isAfter(localDateTime)){
            throw new BizException("截止时间应该在当前时间后面，请修改后再发布");
        }

        mmccActivity.setStatus("10");
        mmccActivity.setEndState("0");
        mmccActivity.setStartTime(new Date());
        baseMapper.updateById(mmccActivity);
    }


}