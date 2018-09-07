package com.toaok.study.http.rx;

import com.toaok.study.http.retrotfit.RetrofitClient;

import java.util.HashMap;

/**
 * @author Toaok
 * @version 1.0  2018/8/20.
 */
public class RequestManager {
    protected static HashMap<Class, Object> sRequestManager = new HashMap<>();


    public static <T> T getRequest(Class<T> clazz){
        T t= (T) sRequestManager.get(clazz);
        if(t==null){
            t= RetrofitClient.createApi(clazz,"");
            sRequestManager.put(clazz,t);
        }
        return t;
    }
}
