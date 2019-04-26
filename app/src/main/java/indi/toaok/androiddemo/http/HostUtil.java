package indi.toaok.androiddemo.http;

import indi.toaok.androiddemo.BuildConfig;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class HostUtil {

    private static final String HOST_URL_DEFAULT = BuildConfig.API_HOST;

    public static String getServerHost(){
        return HOST_URL_DEFAULT;
    }

}
