package com.fss.owspace.model.util;

import com.fss.owspace.BuildConfig;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author: .fss
 * date:   2021/2/24 17:00
 * desc:
 */
public class HttpUitls {
    private HttpUitls(){}
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(createHttpLoggingInterceptor())
            .build();

    private static Interceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY:
                HttpLoggingInterceptor.Level.NONE);
        return httpLoggingInterceptor;

    }
}
