package indi.toaok.androiddemo;

import indi.leaks.core.Leaks;
import indi.toaok.androiddemo.App;

public class AppTest extends App {
    @Override
    public void onCreate() {
        super.onCreate();
        Leaks.init(getApplicationContext());
    }
}
