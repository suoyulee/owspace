package com.fss.owspace.model.api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author: .fss
 * date:   2021/2/7 8:56
 * desc:
 */
public class StringConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        return null;
    }
}
