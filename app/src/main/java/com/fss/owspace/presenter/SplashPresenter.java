package com.fss.owspace.presenter;

import com.fss.owspace.app.OwspaceApplication;
import com.fss.owspace.model.api.ApiService;
import com.fss.owspace.model.entity.SplashEntity;
import com.fss.owspace.util.NetUitl;
import com.fss.owspace.util.OkhttpImageDownloader;
import com.fss.owspace.util.TimeUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * author: .fss
 * date:   2021/2/24 11:42
 * desc:
 * <p>
 * Presenter：主要作为沟通View和Model的桥梁，它从Model层检索数据后，返回给View层
 */
public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private ApiService apiService;

    /**
     * @Inject 注解可以出现在三种类成员之前，表示该成员需要注入依赖项。按运行时的处理顺序这三种成员类型是：
     * （1）构造方法  在构造方法上使用 @Inject 时，其参数在运行时由配置好的IoC容器提供
     * （2）方法
     * （3）属性
     */
    @Inject
    public SplashPresenter(SplashContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    /**
     * 获取图片资源
     *
     * @param deviceId
     */
    @Override
    public void getSplash(String deviceId) {
        Logger.d("~~~~getSplash~~~" + deviceId);
        String client = "android";
        String version = "1.3.0";
        Long time = TimeUtil.getCurrentSeconds();
        apiService.getSplash(client, version, time, deviceId)
                //RxJava Scheduler为调度器
                //Schedulers.io() I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler,行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效
                //subscribeOn指定发送数据的线程,ObserveOn指定观察者接收通知的线程
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<SplashEntity>() {  //subscribe 订阅方法
                    @Override
                    public void onCompleted() {
                        Logger.d("~~~getSplash~~~ onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("~~~getSplash~~~ onError ~~" + e.toString());
                    }

                    @Override
                    public void onNext(SplashEntity splashEntity) {
                        if(NetUitl.isWifi(OwspaceApplication.getInstance().getApplicationContext())){
                            if(splashEntity != null){
                                List<String> imges = splashEntity.getImages();
                                for (String url:imges){
                                    OkhttpImageDownloader.download(url);
                                }
                            }
                        }else {
                            Logger.d("~~~~非WiFi~~不下载图片");
                        }
                    }
                });

    }
}
