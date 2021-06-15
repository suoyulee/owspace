package com.fss.owspace.di.modules;

import com.fss.owspace.BuildConfig;
import com.fss.owspace.model.api.ApiService;
import com.fss.owspace.model.api.StringConverterFactory;
import com.fss.owspace.model.util.EntityUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: .fss
 * Date:   2021/2/4 11:48
 */
@Module
public class NetModule {

    /**
     * 创建okhttp对象
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient(){
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
        //使用new OkHttpClient.Builder()创建一个自定义设置的实例
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) //连接超时时间
                .writeTimeout(20,TimeUnit.SECONDS) //写超时时间
                .readTimeout(20,TimeUnit.SECONDS)  //读超时时间 响应
                .addInterceptor(httpLoggingInterceptor) //添加拦截器
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okhttpClient){
        /**
         * Retrofit2简单的说就是一个网络请求的适配器，它将一个基本的Java接口通过动态代理的方式翻译成一个HTTP请求，
         * 并通过OkHttp去发送请求。此外它还具有强大的可扩展性，支持各种格式转换以及RxJava
         *
         */
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl("http://static.owspace.com/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ApiService provideApiService( Retrofit retrofit ){
        return retrofit.create(ApiService.class);
    }
}
