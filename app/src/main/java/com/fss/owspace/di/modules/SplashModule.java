package com.fss.owspace.di.modules;

import android.media.MediaDrm;

import com.fss.owspace.presenter.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * author: .fss
 * date:   2021/2/25 11:51
 * desc:
 */
@Module
public class SplashModule {
    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @Provides
    public SplashContract.View provideView() {
        return view;
    }
}

