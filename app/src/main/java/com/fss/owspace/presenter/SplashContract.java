package com.fss.owspace.presenter;

/**
 * author: .fss
 * date:   2021/2/24 11:45
 * desc:
 *
 * 在保证功能正常使用同时为了MVP结构的可阅读性,契约类去包含MVP的接口类
 */
public class SplashContract {
    interface Presenter {
        void getSplash(String deviceId);
    }
    public interface View {

    }
}
