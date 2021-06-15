package com.fss.owspace.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * author: .fss
 * date:   2021/2/24 11:31
 * desc: 获取app相关的信息工具类
 */
public class AppUtil {
    /**
     * 获取设备编号
     * @param context
     * @return
     */
    public static  String getDeviceId(Context context){
        TelephonyManager tm =  (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
