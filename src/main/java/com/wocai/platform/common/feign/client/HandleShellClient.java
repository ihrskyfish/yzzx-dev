package com.wocai.platform.common.feign.client;

import feign.Headers;
import feign.RequestLine;

/**
 * @ClassName: HandleShellClient
 * @Author: wzw
 * @Description:
 * @Date: 2021/11/8 10:11 下午
 * @Version: 1.0
 */
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface HandleShellClient {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /handleShell")
    void handleShell(String cmd);
}
