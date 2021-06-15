package com.fss.owspace.model.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author: .fss
 * date:   2021/2/7 8:51
 * desc:
 */
public class StringConverterFactory extends Converter.Factory {
    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type == String.class){
            return new StringConverter();
        }
        return null;
    }
}
