package com.fss.owspace.di.components;

import com.fss.owspace.di.modules.SplashModule;
import com.fss.owspace.di.scopes.UserScope;
import com.fss.owspace.view.activity.SplashActivity;

import dagger.Component;

/**
 * author: .fss
 * date:   2021/2/25 11:43
 * desc:
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
