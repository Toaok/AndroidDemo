package com.toaok.study;

import android.app.Application;

import com.toaok.utilcode.util.Utils;

/**
 * @author Toaok
 * @version 1.0  2018/8/29.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }

}
