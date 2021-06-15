package com.fss.owspace.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * author: .fss
 * date:   2021/2/26 10:44
 * desc:   sp文件工具类
 */
public class PreferenceUtils {
    public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        //getDefaultSharedPreferences 获取到全局作用域的preference
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefInt(Context context, final String key,
                                  final int value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putInt(key, value).apply();
    }
}
