package com.wocai.platform.common.feign.callback;

import com.wocai.platform.common.feign.base.BaseFeignClient;
import com.wocai.platform.common.feign.client.HandleShellClient;

/**
 * @ClassName: HandleShellCallBack
 * @Author: wzw
 * @Description:
 * @Date: 2021/11/8 10:13 下午
 * @Version: 1.0
 */
public class HandleShellCallBack extends BaseFeignClient implements HandleShellClient {

    @Override
    public void handleShell(String cmd) {

    }
}
