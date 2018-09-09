package com.toaok.study.module.home.databinder;

import com.toaok.study.model.vo.SplashBean;
import com.toaok.study.module.home.view.SplashDelegate;
import com.toaok.themvp.databind.DataBinder;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDataBinder implements DataBinder<SplashDelegate,SplashBean> {
    @Override
    public void viewBindModel(SplashDelegate viewDelegate, SplashBean data) {
        viewDelegate.setImageView(data.getUrl());
    }
}
