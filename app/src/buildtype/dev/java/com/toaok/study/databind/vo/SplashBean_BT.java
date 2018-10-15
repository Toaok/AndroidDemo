package com.toaok.study.databind.vo;

import com.toaok.study.model.vo.AppInfo;
import com.toaok.study.module.home.bean.SplashBean;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashBean_BT extends SplashBean {

    private boolean appinfoDisplay;
    private AppInfo appInfo;

    public boolean isAppinfoDisplay() {
        return appinfoDisplay;
    }

    public void setAppinfoDisplay(boolean appinfoDisplay) {
        this.appinfoDisplay = appinfoDisplay;
    }

    public SplashBean_BT(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    //继承父类的值
    public SplashBean_BT(AppInfo appInfo, SplashBean splashBean) {
        this.appInfo = appInfo;
        //
        setSelf(splashBean);
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }


}
