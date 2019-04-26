package indi.toaok.androiddemo.model.local.preferences.base;

import android.content.Context;

import indi.toaok.androiddemo.model.local.preferences.Salt;
import indi.toaok.encrypt.security.SharedPreferencesUtil;
import indi.toaok.encrypt.security.except.EncryptException;


/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class BaseSharePreference extends SharedPreferencesUtil {
    public static void putSecurityString(Context context, String key, String value) {
        try {
            putSecurityString(context, Salt.getCommonBase(), key, value);
        } catch (EncryptException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取解密后的sharedpreferences，无需再次解密
     *
     * @param key
     * @return
     */
    public static String getSecurityString(Context context, String key)  {
        try {
            return getSecurityString(context, Salt.getCommonBase(), key);
        } catch (EncryptException e) {
            e.printStackTrace();
        }
        return null;
    }
}
