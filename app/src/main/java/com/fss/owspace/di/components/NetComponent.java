package com.fss.owspace.di.components;

import com.fss.owspace.di.modules.NetModule;
import com.fss.owspace.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * author: .fss
 * Date:   2021/2/4 11:25
 * 网络部件
 */

//注解的类(一般是接口或抽象类)用于为具体的类注入依赖
@Component(modules = NetModule.class )
@Singleton //单例模式
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkhttp();
    Retrofit getRetrofit();
}
