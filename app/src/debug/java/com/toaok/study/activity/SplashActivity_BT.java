package com.toaok.study.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.toaok.study.BuildConfig;
import com.toaok.study.R;
import com.toaok.study.module.home.SplashActivity;

/**
 * Created by sj on 2016/11/25.
 */

public class SplashActivity_BT extends SplashActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        FrameLayout frameLayout = findViewById(R.id.frame_layout);
        View view = getLayoutInflater().inflate(R.layout.activity_splash_bt, frameLayout);
        TextView tv_env = view.findViewById(R.id.tv_env);
        TextView tv_channel = view.findViewById(R.id.tv_channel);
        TextView tv_version = view.findViewById(R.id.tv_version);
        tv_env.setText(tv_env.getText().toString() + BuildConfig.API_HOST);
        tv_channel.setText(tv_channel.getText().toString() + BuildConfig.APPLICATION_ID);
        tv_version.setText(tv_version.getText().toString() + AppUtils.getAppVersionName());
    }
}
