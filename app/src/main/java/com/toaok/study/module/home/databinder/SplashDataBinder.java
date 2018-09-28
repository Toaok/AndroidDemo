package com.toaok.study.module.home.databinder;

import android.view.View;

import com.toaok.study.base.BaseDataBinder;
import com.toaok.study.model.vo.SplashBean;
import com.toaok.study.module.home.view.SplashDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDataBinder extends BaseDataBinder<SplashDelegate, SplashBean> {
    @Override
    public void viewBindModel(SplashDelegate viewDelegate, SplashBean data) {
        viewDelegate.setImageView(data.getUrl());
        if (!data.isAvailableByPing()) viewDelegate.getCountDownTimer().setVisibility(View.GONE);
        viewDelegate.setCountDownTimer(data.getCountDownTimer());
    }
}
