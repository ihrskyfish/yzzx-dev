package com.wocai.platform.modules.common.service;

import com.wocai.platform.modules.common.vo.FileRes;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessCommonService {

    /**
     * 文件上传
     *
     * @param multipartFile
     * @param module
     * @return
     */
    FileRes uploadFile(MultipartFile multipartFile, String module);

}
