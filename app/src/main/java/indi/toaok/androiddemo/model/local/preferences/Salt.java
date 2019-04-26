package indi.toaok.androiddemo.model.local.preferences;

import indi.toaok.androiddemo.BuildConfig;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class Salt {
    public static String getCommonBase() {
        return BuildConfig.SALT;
    }
}
