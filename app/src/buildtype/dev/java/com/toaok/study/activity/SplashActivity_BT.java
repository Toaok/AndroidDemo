package com.toaok.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.toaok.study.databind.SplashDataBinder_BT;
import com.toaok.study.model.vo.AppInfo;
import com.toaok.study.module.home.activity.BaseSplashActivity;
import com.toaok.study.view.SplashDetagate_BT;
import com.toaok.study.vo.SplashBean_BT;
import com.toaok.themvp.databind.DataBinder;

/**
 * Created by sj on 2016/11/25.
 */

public class SplashActivity_BT extends BaseSplashActivity<SplashDetagate_BT> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppInfo appInfo=new AppInfo();
        if (mSplashBean == null) {
            mSplashBean = new SplashBean_BT(appInfo);
        } else {
            if (mSplashBean instanceof SplashBean_BT) {
                ((SplashBean_BT) mSplashBean).setAppInfo(appInfo);
            }else {
                mSplashBean=new SplashBean_BT(appInfo,mSplashBean);
            }
        }
        notifyModelChanged(mSplashBean);
    }

    @Override
    protected Class<SplashDetagate_BT> getDelegateClass() {
        return SplashDetagate_BT.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new SplashDataBinder_BT();
    }



}
