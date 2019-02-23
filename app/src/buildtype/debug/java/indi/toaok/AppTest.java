package indi.toaok;

import indi.leaks.core.Leaks;

public class AppTest extends indi.toaok.App {
    @Override
    public void onCreate() {
        super.onCreate();
        Leaks.init(getApplicationContext());
    }
}
