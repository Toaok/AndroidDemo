package com.toaok.study.module.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.toaok.study.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(MainActivity.getIntent(SplashActivity.this));
            }
        }).start();
    }
}
