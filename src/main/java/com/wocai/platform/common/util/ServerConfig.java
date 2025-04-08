package com.wocai.platform.common.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @文件名: ServerConfig
 * @包名 com.wocai.platform.common.util
 * @描述:
 * @创建者 linwq
 * @创建时间 2019-12-26 14:33
 * @版权 Copyright(c)2019 浙江我财
 */
@Data
public class ServerConfig {

    public static String redis_event_queue_key;

    public static String oss_access_key;
    public static String oss_access_secret;
    public static String oss_bucket_name;
    public static String oss_endpoint;
    public static String oss_endpoint_internal;
    public static String oss_upload_url;
    public static String uploadpath;
    public static String plat_upload_path;
    public static String plat_webapp_path;
    public static String plat_env;
    public static String plat_interface_address;
    public static String plat_name;

    public static String plat_upload_head_path;

}
