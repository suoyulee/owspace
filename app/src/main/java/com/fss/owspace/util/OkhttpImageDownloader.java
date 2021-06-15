package com.fss.owspace.util;

import com.fss.owspace.model.util.HttpUitls;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: .fss
 * date:   2021/2/24 16:53
 * desc:
 */
public class OkhttpImageDownloader {
    private static final String TAG = "OkhttpImageDownloader";

    public static void download(String url) {
        Logger.d("~~~~~"+TAG+"~~~~~"+url);
        final Request request = new Request.Builder()
                .url(url)
                .get() //默认就是GET请求，可以不写
                .build();
        //enqueue(Callback)方法来提交异步请求；
        HttpUitls.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //@NotNull  被注释的元素不能为null
                Logger.e("~~~~~"+TAG+"~~~~~"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //保存图片
                saveImage(response);
            }
        });
    }

    private static void saveImage(Response response) {
        FileUtil.createSdDir();
        String url = response.request().url().toString();
        String picName = url.substring(url.lastIndexOf("/") + 1);
        if (FileUtil.isFileExists(picName)) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(FileUtil.createFile(picName));
            InputStream in = response.body().byteStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while (-1 != (len = in.read(buf))) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
