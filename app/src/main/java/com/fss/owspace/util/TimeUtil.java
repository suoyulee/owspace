package com.fss.owspace.util;

import android.os.SystemClock;

/**
 * author: .fss
 * date:   2021/2/24 15:27
 * desc: 时间相关工具类
 */
public class TimeUtil {
    public static Long getCurrentSeconds() {
        return System.currentTimeMillis()/1000;
    }
}
