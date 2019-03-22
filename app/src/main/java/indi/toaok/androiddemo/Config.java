package indi.toaok.androiddemo;

import android.os.Environment;

import indi.toaok.utils.Utils;

import java.io.File;

public class Config {

    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String LINE_SEP = System.getProperty("line.separator");
    public static final String PKG = "indi.toaok.study";
    public static final String TEST_PKG = "indi.toaok.study";
    public static final String GITHUB = "https://github.com/Toaok/AndroidDemo";
    public static final String BAIDU = "https://www.baidu.com/";
    public static final String BLOG = "https://toaok.github.io";
    public static final String PERSONAL_WEB = BLOG;
    public static final String CACHE_PATH;
    public static final String TEST_APK_PATH;

    static {
        File cacheDir = Utils.getApp().getExternalCacheDir();
        if (cacheDir != null) {
            CACHE_PATH = cacheDir.getAbsolutePath();
        } else {
            CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        TEST_APK_PATH = CACHE_PATH + FILE_SEP + "test_install.apk";
    }
}
