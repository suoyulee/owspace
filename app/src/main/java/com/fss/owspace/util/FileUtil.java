package com.fss.owspace.util;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author: .fss
 * date:   2021/2/24 17:21
 * desc:
 */
public class FileUtil {
    public static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String ADPATH = FileUtil.SDPATH + "/fss_owspace";
    public static void createSdDir(){
        File file = new File(FileUtil.ADPATH);
        if(!file.exists()){
            file.mkdir();
        } else {
            if(!file.isDirectory()){
                file.delete();
                file.mkdir();
            }
        }
    }

    public static boolean isFileExists(String fileName){
        if(fileName == null) return false;
        File file = new File(FileUtil.ADPATH + "/"+fileName);
        if(file.exists()) return true;
        return false;
    }

    public static File createFile(String fileName){
        File file = new File(FileUtil.ADPATH,fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public static List<String> getAllAD() {
        File file = new File(FileUtil.ADPATH);
        File[] files = file.listFiles();
        List<String> list = new ArrayList<>();

        if(null != files){
            for(File file_tmp:files){
                list.add(file_tmp.getAbsolutePath());
            }
        }
        return list;
    }
}
