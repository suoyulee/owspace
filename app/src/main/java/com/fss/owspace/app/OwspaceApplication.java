package com.fss.owspace.app;

import android.app.Application;
import android.content.Context;

import com.fss.owspace.R;
import com.fss.owspace.di.components.DaggerNetComponent;
import com.fss.owspace.di.components.NetComponent;
import com.fss.owspace.di.modules.NetModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * author: .fss
 * Date:   2021/2/4 11:20
 */
public class OwspaceApplication extends Application {

    private static OwspaceApplication instance;

    private NetComponent netComponent; //网络组件
    /**
     * 获取app的 context
     * @param context
     * @return
     */
    public static OwspaceApplication get(Context context){
        return (OwspaceApplication)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();
        initNet();
        initTypeFace();

    }

    private void initTypeFace() {
        //初始化字体
        CalligraphyConfig calligraphyConfig =new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initNet() {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    private void initLogger() {
        //初始化日志
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public static OwspaceApplication getInstance() {
        return instance;
    }
}
