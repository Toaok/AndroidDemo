package com.toaok.study.module.home.activity;import android.os.Bundle;import android.support.annotation.Nullable;import com.toaok.study.module.home.databinder.SplashDataBinder;import com.toaok.study.module.home.view.SplashDelegate;import com.toaok.themvp.databind.DataBinder;/** * @author Toaok * @version 1.0  2018/9/8. */public class SplashActivity  extends BaseSplashActivity<SplashDelegate>{    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        notifyModelChanged(mSplashBean);    }    @Override    public DataBinder getDataBinder() {        return new SplashDataBinder();    }    @Override    protected Class<SplashDelegate> getDelegateClass() {        return SplashDelegate.class;    }}