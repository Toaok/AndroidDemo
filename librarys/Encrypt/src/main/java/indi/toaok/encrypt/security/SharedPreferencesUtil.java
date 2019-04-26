package indi.toaok.encrypt.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import org.json.JSONException;

import java.util.HashMap;

import indi.toaok.encrypt.security.crypt.AESCrypt;
import indi.toaok.encrypt.security.except.EncryptException;
import indi.toaok.encrypt.uitl.JSONParserHelper;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class SharedPreferencesUtil {

    static HashMap<String, String> sSaltMap;

    public static String getSalt(Context context, String baseSalt) throws EncryptException {

        if(baseSalt == null) {
            throw new NullPointerException("base salt can't be null!");
        }

        if(sSaltMap == null) {
            sSaltMap = new HashMap<>();
        }

        if(sSaltMap.containsKey(baseSalt)) {
            String value = sSaltMap.get(baseSalt);
            if(!TextUtils.isEmpty(value)) {
                return value;
            }
        }

        String salt = Salt.getSalt(baseSalt);
        sSaltMap.put(baseSalt, salt);
        return salt;
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).edit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    /**
     * 存储加密的 sharedpreferences
     *
     * @param key
     * @param value
     */
    public static void putSecurityString(Context context, String baseSalt, String key, String value) throws EncryptException {
        String encryptStr = AESCrypt.encryptStr(getSalt(context, baseSalt), value);
        getEditor(context).putString(key, encryptStr).commit();
    }

    /**
     * 获取解密后的sharedpreferences，无需再次解密
     *
     * @param key
     * @return
     */
    public static String getSecurityString(Context context, String baseSalt, String key) throws EncryptException {
        String encryptStr = getSharedPreferences(context).getString(key, "");
        return AESCrypt.decryptStr(getSalt(context, baseSalt), encryptStr);
    }

    /**
     * 删除
     * @param key
     */
    public static void removeSecurityString(Context context, String key) {
        getEditor(context).remove(key).commit();
    }

    /**
     * map 转 json 加密并存储
     * @param context
     * @param key
     * @param developConfig
     * @return
     */
    public static int putMapData(Context context, String baseSalt, String key, HashMap<String, String> developConfig) {
        try {
            String json = JSONParserHelper.mapToJson(developConfig);
            putSecurityString(context, baseSalt, key, json);
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (EncryptException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
