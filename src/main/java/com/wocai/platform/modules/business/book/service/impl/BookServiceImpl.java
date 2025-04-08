package com.wocai.platform.modules.business.book.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.enums.MmccServiceUserAgeGroupEnum;
import com.wocai.platform.common.exception.BizException;
import com.wocai.platform.modules.business.book.dto.BookListDto;
import com.wocai.platform.modules.business.book.service.BookService;
import com.wocai.platform.modules.business.book.vo.BookEvaluationRecordsVo;
import com.wocai.platform.modules.business.book.vo.ReservationRecordVo;
import com.wocai.platform.modules.business.display.dto.MmccServiceUserReq;
import com.wocai.platform.modules.business.display.entity.MmccServiceDisplay;
import com.wocai.platform.modules.business.display.entity.MmccServicePerson;
import com.wocai.platform.modules.business.display.entity.MmccServiceUser;
import com.wocai.platform.modules.business.display.mapper.MmccServiceDisplayMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServicePersonMapper;
import com.wocai.platform.modules.business.display.mapper.MmccServiceUserMapper;
import com.wocai.platform.modules.business.display.vo.MmccServiceUserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 15:42
 **/
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private MmccServiceUserMapper mmccServiceUserMapper;

    @Resource
    private MmccServiceDisplayMapper mmccServiceDisplayMapper;


    @Resource
    private MmccServicePersonMapper mmccServicePersonMapper;

    @Override
    public IPage<ReservationRecordVo> reservationRecord(Page<ReservationRecordVo> page, BookListDto bookListDto) {
        Map<String,Object> map=new HashMap<>();
        if (StringUtils.isNotEmpty(bookListDto.getUsername())){
            map.put("userName",bookListDto.getUsername());
        }
        if (StringUtils.isNotEmpty(bookListDto.getUserPhone())){
            map.put("userPhone",bookListDto.getUserPhone());
        }
        if (StringUtils.isNotEmpty(bookListDto.getUserId())){
            map.put("userId",bookListDto.getUserId());
        }


        IPage<ReservationRecordVo> result=mmccServiceUserMapper.reservationRecord(page,map);
        return result;
    }

    @Override
    public MmccServiceUserRes getMainById(String id) {
        MmccServiceUserRes result = new MmccServiceUserRes();
        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectById(id);
        if (mmccServiceUser == null || CommonConstant.DEL_FLAG_1.equals(mmccServiceUser.getDelFlag())) {
            throw new BizException("数据不存在");
        }
        BeanUtils.copyProperties(mmccServiceUser, result);

        MmccServiceDisplay mmccServiceDisplay = mmccServiceDisplayMapper.selectById(result.getDisplayId());
        if (mmccServiceDisplay!=null && "0".equalsIgnoreCase(mmccServiceDisplay.getDelFlag().toString())) {
            result.setName(mmccServiceDisplay.getName());
            result.setServiceIntroduce(mmccServiceDisplay.getServiceIntroduce());
            result.setRoomLabel(mmccServiceDisplay.getRoomLabel());

            String servicePersonId = result.getServicePersonId();

            if(StringUtils.isNotEmpty(servicePersonId)){
                MmccServicePerson mmccServicePerson = mmccServicePersonMapper.selectById(servicePersonId);
                result.setServicePersonName(mmccServicePerson.getName());
            }
        }

        return result;
    }

    @Override
    public IPage<BookEvaluationRecordsVo> evaluationRecordsVo(Page<BookEvaluationRecordsVo> page) {
        IPage<BookEvaluationRecordsVo> result=mmccServiceUserMapper.evaluationRecordsVo(page);
        return result;
    }

    @Override
    public void toStore(String serviceUserId) {
        MmccServiceUser mmccServiceUser = mmccServiceUserMapper.selectById(serviceUserId);
        if (mmccServiceUser==null || !"0".equalsIgnoreCase(mmccServiceUser.getDelFlag().toString())){
            throw new BizException("该用户预定信息不存在");
        }
        mmccServiceUser.setStatus("0");
        mmccServiceUserMapper.updateById(mmccServiceUser);

    }

    /**
     * 用户预定信息修改
     * @param mmccServiceUserReq
     */
    @Override
    public void updateMain(MmccServiceUserReq mmccServiceUserReq) {
        mmccServiceUserMapper.updateById(mmccServiceUserReq);
    }
}
