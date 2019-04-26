package indi.toaok.androiddemo.http.rx.api;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class BaseRequestBody<T> implements Serializable, IRequestBody {
    private T body;

    public BaseRequestBody(T body) {
        this.body = body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public Map<String, Object> toRequestBody() {
        Map<String, Object> params = null;
        try {
            params = convertToMap(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("reuqestInfo", "request-param" + params);
        return params;
    }


    public HashMap<String, Object> convertToMap(Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }
}
