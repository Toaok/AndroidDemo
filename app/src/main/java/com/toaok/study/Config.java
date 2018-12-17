package com.toaok.study;

import android.os.Environment;

import com.toaok.utils.Utils;

import java.io.File;

public class Config {

    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String LINE_SEP = System.getProperty("line.separator");
    public static final String PKG      = "com.toaok.study";
    public static final String TEST_PKG = "com.toaok.study";
    public static final String GITHUB   = "https://github.com/Toaok/AndroidDemo";
    public static final String BLOG     = "";
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
