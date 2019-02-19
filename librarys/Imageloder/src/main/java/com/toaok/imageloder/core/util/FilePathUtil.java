package com.toaok.imageloder.core.util;

import android.os.Environment;
import java.io.File;

/**
 * 外部存储
 */
public class FilePathUtil {

    private static String getBaseDirPath(String childDir) {
        return Environment.getExternalStorageDirectory().getPath() + "/" + "xxxxx" + "/" + childDir;
    }


    public static String getImagePath() {
        return checkAndMkdirs(getBaseDirPath("image/"));
    }

    public static String getSplashImagePath() {
        return checkAndMkdirs(getBaseDirPath("image/splash/"));
    }

    /**
     * 检查文件夹是否存在
     *
     * @param dir
     * @return
     */
    public static String checkAndMkdirs(String dir) {
        File file = new File(dir);
        if (file.exists() == false) {
            file.mkdirs();
        }
        return dir;
    }
}