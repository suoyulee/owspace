package com.fss.owspace.util.tools;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * author: .fss
 * date:   2021/5/17 16:49
 * desc:   RxBus并不是一个库，而是一种模式
 *   事件发布-订阅总线
 */
public class RxBus {
    private static volatile RxBus instance;
    private final Subject<Object,Object> bus;
    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    public static RxBus getInstance() {
        if(instance == null){
            synchronized (RxBus.class){
                if (instance == null){
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }
    public void postEvent(Object event){
        bus.onNext(event);
    }

    public <T>Observable<T> tObservable(Class<T> eventype){
        return bus.ofType(eventype);
    }

}
