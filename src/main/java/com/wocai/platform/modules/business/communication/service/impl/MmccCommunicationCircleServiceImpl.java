package com.wocai.platform.modules.business.communication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.common.util.StringUtils;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationCircle;
import com.wocai.platform.modules.business.communication.dto.MmccCommunicationCircleReq;
import com.wocai.platform.modules.business.communication.entity.MmccCommunicationUser;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationUserMapper;
import com.wocai.platform.modules.business.communication.vo.MmccCommunicationCircleRes;
import com.wocai.platform.modules.business.communication.mapper.MmccCommunicationCircleMapper;
import com.wocai.platform.modules.business.communication.service.IMmccCommunicationCircleService;
import com.wocai.platform.modules.business.communication.vo.MmccUserLeaveMessageVo;
import com.wocai.platform.modules.business.user.entity.User;
import com.wocai.platform.modules.business.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.wocai.platform.common.system.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 交流圈话题
 * @Author: lq
 * @Date: 2023-05-24
 * @Version: V1.0
 */
@Service
public class MmccCommunicationCircleServiceImpl extends BaseServiceImpl<MmccCommunicationCircleMapper, MmccCommunicationCircle> implements IMmccCommunicationCircleService {

    @Resource
    private MmccCommunicationUserMapper mmccCommunicationUserMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public IPage<MmccCommunicationCircleRes> queryPageList(IPage<MmccCommunicationCircleRes> page, MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        Map<String, Object> reqParam = new HashMap<>();
        if (StringUtils.isNotEmpty(mmccCommunicationCircleReq.getTitle())){
            reqParam.put("title",mmccCommunicationCircleReq.getTitle());
        }
        IPage<MmccCommunicationCircleRes> resultList = this.baseMapper.queryPage(page, reqParam);
        resultList.getRecords().forEach(record -> {
            Integer count = mmccCommunicationUserMapper.selectCount
                    (Wrappers.<MmccCommunicationUser>lambdaQuery()
                            .eq(MmccCommunicationUser::getDelFlag, 0)
                            .eq(MmccCommunicationUser::getCircleId, record.getId()));
            record.setCount(count);
        });
        return resultList;
    }

    @Override
    public boolean doDuplicateCheck(MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        return false;
    }

    @Override
    public void saveMain(MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        this.doDuplicateCheck(mmccCommunicationCircleReq);
        MmccCommunicationCircle mmccCommunicationCircle = new MmccCommunicationCircle();
        BeanUtils.copyProperties(mmccCommunicationCircleReq, mmccCommunicationCircle);
        this.save(mmccCommunicationCircle);
    }

    @Override
    public void updateMain(MmccCommunicationCircleReq mmccCommunicationCircleReq) {
        MmccCommunicationCircle mmccCommunicationCircle = this.getById(mmccCommunicationCircleReq.getId());
        if (mmccCommunicationCircle == null || CommonConstant.DEL_FLAG_1.equals(mmccCommunicationCircle.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        this.doDuplicateCheck(mmccCommunicationCircleReq);
        BeanUtils.copyProperties(mmccCommunicationCircleReq, mmccCommunicationCircle);
        this.updateById(mmccCommunicationCircle);
    }

    @Override
    public MmccCommunicationCircleRes getMainById(String id) {
        MmccCommunicationCircleRes result = new MmccCommunicationCircleRes();
        MmccCommunicationCircle mmccCommunicationCircle = this.getById(id);
        if (mmccCommunicationCircle == null || CommonConstant.DEL_FLAG_1.equals(mmccCommunicationCircle.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccCommunicationCircle, result);
        return result;
    }

    @Override
    public void batchDelete(String ids) {
        String[] idList = ids.split(",");
        for (String id : idList) {
            MmccCommunicationCircle mmccCommunicationCircle = this.getById(id);
            if (mmccCommunicationCircle == null) {
                throw new BizException("数据不存在");
            }
        }
        LambdaQueryWrapper<MmccCommunicationCircle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(MmccCommunicationCircle::getId, idList);
        MmccCommunicationCircle mmccCommunicationCircle = new MmccCommunicationCircle();
        mmccCommunicationCircle.setDelFlag(CommonConstant.DEL_FLAG_1);
        this.update(mmccCommunicationCircle, queryWrapper);
    }

    @Override
    public IPage<MmccUserLeaveMessageVo> userLeaveMessage(Page<MmccCommunicationUser> page, String id) {
        IPage<MmccUserLeaveMessageVo> result = new Page<>();
        IPage<MmccCommunicationUser> mmccCommunicationUserIPage = mmccCommunicationUserMapper.selectPage(page,
                Wrappers.<MmccCommunicationUser>lambdaQuery()
                        .eq(MmccCommunicationUser::getDelFlag, 0)
                        .eq(MmccCommunicationUser::getCircleId, id)
//                        .orderByDesc(MmccCommunicationUser::getEssence)
                        .orderByDesc(MmccCommunicationUser::getCreateTime));
        BeanUtils.copyProperties(mmccCommunicationUserIPage, result);
        List<MmccUserLeaveMessageVo> list = mmccCommunicationUserIPage.getRecords()
                .stream()
                .map(item -> {
                    MmccUserLeaveMessageVo mmccUserLeaveMessageVo = new MmccUserLeaveMessageVo();
                    BeanUtils.copyProperties(item, mmccUserLeaveMessageVo);
                    User user = userMapper.selectById(item.getUserId());
                    if (user != null && "0".equalsIgnoreCase(user.getDelFlag().toString())) {
                        mmccUserLeaveMessageVo.setNickName(user.getNickName());
                    }
                    return mmccUserLeaveMessageVo;
                })
                .collect(Collectors.toList());
        result.setRecords(list);
        return result;
    }

    @Override
    public void setSelected(String id) {
        MmccCommunicationUser mmccCommunicationUser = mmccCommunicationUserMapper.selectById(id);
        if (mmccCommunicationUser == null || !"0".equalsIgnoreCase(mmccCommunicationUser.getDelFlag().toString())) {
            throw new BizException("该留言不存在");
        }
        if ("0".equalsIgnoreCase(mmccCommunicationUser.getEssence())){
            mmccCommunicationUser.setEssence("1");
        }else {
            mmccCommunicationUser.setEssence("0");
        }

        mmccCommunicationUserMapper.updateById(mmccCommunicationUser);
    }


}