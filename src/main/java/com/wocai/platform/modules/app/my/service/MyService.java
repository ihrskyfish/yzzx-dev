package com.wocai.platform.modules.app.my.service;

import com.wocai.platform.common.api.dto.ReqUser;
import com.wocai.platform.modules.app.my.dto.MyImagesDto;
import com.wocai.platform.modules.app.my.dto.MyUpdateUserDto;
import com.wocai.platform.modules.app.my.vo.*;

import java.util.List;

/**
 * @program: yzzx
 * @description
 * @author: YouName
 * @create: 2023-05-25 09:55
 **/
public interface MyService {
    void updateMyInformation(MyUpdateUserDto myUpdateUserDto, ReqUser reqUser);

    MyPregnancyArchivesVo MyPregnancyArchives(ReqUser reqUser);

    MyPregnancyArchivesVo MyPregnancyArchivesV2(ReqUser reqUser);

    List<MyBabyProfileVo> babyProfile(ReqUser reqUser);

    List<MyLikesVo> myLikes(ReqUser reqUser);

    List<MyCollectionVo> myCollection(ReqUser reqUser);

    String imageSynthesis(MyImagesDto myImagesDto);

    MyPersonalInformationVo myPersonalInformation(ReqUser reqUser);

    void closeBirthdayGifts(ReqUser reqUser);
}
