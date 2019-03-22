package indi.toaok.androiddemo;

import indi.toaok.leaks.core.Leaks;

public class AppTest extends App {
    @Override
    public void onCreate() {
        super.onCreate();
        Leaks.init(getApplicationContext());
    }
}
