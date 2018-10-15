package com.toaok.study.databind.view;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.toaok.study.R;
import com.toaok.study.module.home.view.SplashDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDetagate_BT extends SplashDelegate {

    private TextView mEnvironment;
    private TextView mChannel;
    private TextView mVersion;
    private TextView mApplicationId;
    private TextView mBuildType;

    @Override
    public void initWidget() {
        super.initWidget();
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.activity_splash_bt,getFrameLayout());
        mEnvironment = view.findViewById(R.id.tv_env);
        mChannel = view.findViewById(R.id.tv_channel);
        mVersion = view.findViewById(R.id.tv_version);
        mApplicationId = view.findViewById(R.id.tv_application_id);
        mBuildType = view.findViewById(R.id.tv_buildtype);
    }

    public void setEnvironment(CharSequence environment) {
        mEnvironment.setText(mEnvironment.getText().toString()+environment);
    }

    public void setChannel(CharSequence channel) {
        mChannel.setText(mChannel.getText().toString()+channel);
    }

    public void setVersion(CharSequence version) {
        mVersion.setText(mVersion.getText().toString()+version);
    }


    public void setApplicationId(CharSequence applicationId) {
        mApplicationId.setText(mApplicationId.getText().toString() + applicationId);
    }

    public void setBuildType(CharSequence buildType) {
        mBuildType.setText(mBuildType.getText().toString() + buildType);
    }
}
