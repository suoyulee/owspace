package com.fss.owspace.model.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.joda.time.DateTime;

/**
 * author: .fss
 * date:   2021/2/7 9:24
 * desc:
 */
public class EntityUtils {
    public EntityUtils() {
    }
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class,new DateTimeTypeAdapter())
            .create();
}
