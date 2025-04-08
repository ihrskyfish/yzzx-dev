package com.wocai.platform.modules.common.service.impl;



import com.wocai.platform.modules.common.service.BusinessCommonService;
import com.wocai.platform.modules.common.vo.FileRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: bmfz-api
 * @description
 * @author: YouName
 * @create: 2023-03-16 10:28
 **/
@Service
@Slf4j
public class BusinessCommonServiceImpl implements BusinessCommonService {
    @Override
    public FileRes uploadFile(MultipartFile multipartFile, String module) {
        return null;
    }
}
