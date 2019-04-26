package indi.toaok.androiddemo.model.local.preferences;

import indi.toaok.androiddemo.model.local.preferences.base.BaseSharePreference;
import indi.toaok.androiddemo.utils.AppUtil;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class SystemSharePreference extends BaseSharePreference {

    static String SPLASH_IMAGE_DATA = "splash_image_data";

    public static void setSplashImageData(String data) {
        putSecurityString(AppUtil.getApplication(), SPLASH_IMAGE_DATA, data);
    }

    public static String getSplashImageData() {
        return getSecurityString(AppUtil.getApplication(), SPLASH_IMAGE_DATA);
    }

    public static void cleanSplasImageData() {
        removeSecurityString(AppUtil.getApplication(), SPLASH_IMAGE_DATA);
    }
}
