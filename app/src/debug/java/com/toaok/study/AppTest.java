package com.toaok.study;

import com.leaks.core.Leaks;

public class AppTest extends com.toaok.study.App {
    @Override
    public void onCreate() {
        super.onCreate();
        Leaks.init(getApplicationContext());
    }
}
