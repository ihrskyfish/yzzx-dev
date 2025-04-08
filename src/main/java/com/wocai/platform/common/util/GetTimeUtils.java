package com.wocai.platform.common.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class GetTimeUtils {
        public static void main(String[] args) {
        long timestamp1 = 1390122121000L;  // 第一个时间戳
        long timestamp2 = 1705683952550L;  // 第二个时间戳

        // 计算两个时间戳之间的分钟差异
        long minutesDiff = Duration.between(
                Instant.ofEpochMilli(timestamp1),
                Instant.ofEpochMilli(timestamp2)
        ).toMinutes();

        System.out.println("时间差（分钟）：" + minutesDiff);
    }
//    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//    }
}
