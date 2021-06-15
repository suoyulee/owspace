package com.fss.owspace.util;

import android.content.Context;
import android.net.ConnectivityManager;


/**
 * author: .fss
 * date:   2021/2/24 16:40
 * desc: 网络相关工具类
 */
public class NetUitl {
    public static boolean isWifi(Context context){
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }
}
