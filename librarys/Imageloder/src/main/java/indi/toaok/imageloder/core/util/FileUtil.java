package indi.toaok.imageloder.core.util;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.security.MessageDigest;

/**
 * Created by sj on 14/12/2016.
 */

public class FileUtil {

    private static boolean isEmulator() {
        return android.os.Build.MODEL.equals("sdk");
    }

    public static boolean isExistSdcard() {
        if (!isEmulator()) {
            return Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
        }
        return true;
    }

    /**
     * 检查文件夹是否存在, 不存在则创建
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

    /**
     * 将url转成文件名
     **/
    public static String convertUrlToFileName(String url) {
        return hashKeyForUrl(url);
    }

    private static String hashKeyForUrl(String url) {
        String cacheKey;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(url.getBytes());
            cacheKey = bytesToHexString(digest.digest());
        } catch (Exception e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (1 == hex.length()) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 系统版本是否为 KitKat4.4 或以上
     *
     * @return
     */
    public static boolean isVersionUpKitkat() {
        int version = getAndroidSDKVersion();
        return version == 0 ? false : getAndroidSDKVersion() >= 19;
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {

        }
        return version;
    }

    /**
     * 根据URL查询文件是否存在
     * @param url
     * @return
     */
    public static boolean isExistsCacheFileFromUrl(String filepath,String url) {
        if(TextUtils.isEmpty(filepath) || TextUtils.isEmpty(url)) {
            return false;
        }
        String filename = convertUrlToFileName(url);
        File file = new File(filepath + filename);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 获取缓存图片文件 根据URL
     * @param url
     * @return
     */
    public static File getCacheFileFromUrl(String filepath,String url) {
        if(TextUtils.isEmpty(filepath) || TextUtils.isEmpty(url)) {
            return null;
        }
        String filename = convertUrlToFileName(url);
        File file = new File(filepath + filename);
        if (!file.exists()) {
            return null;
        }
        return file;
    }
}