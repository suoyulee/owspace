package com.fss.owspace.model.api;

import com.fss.owspace.model.entity.SplashEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: .fss
 * Date:   2021/2/4 11:30
 */
public interface ApiService {
    /**
     * <p>http://static.owspace.com/static/picture_list.txt?client=android&version=1.3.0&time=1467864021&device_id=866963027059338</p>
     *
     * @param client
     * @param version
     * @param time
     * @param deviceId
     * @return
     */
    /**
     * Observable是能向观察它的人（或外界）在一定时间段内推送值的实体。
     * Observable实体可能会一直推送下去，也可能在某时间点结束，也可能在某时间点抛出error
     * 故Observable可能的行为为：
     * 向observer推送值，告诉observer结束，告诉observer有异常（error）
     *
     * HTTP的response是一个observable：它对观察它的实体在timeout时间内
     * 推送值或抛出异常
     */
     @GET("static/picture_list.txt")
     Observable<SplashEntity> getSplash(@Query("client") String client, @Query("version") String version, @Query("time") Long time, @Query("deviceId") String deviceId);

}
