package indi.toaok.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import indi.toaok.databind.SplashDataBinder_BT;
import indi.toaok.model.vo.AppInfo;
import indi.toaok.module.home.activity.BaseSplashActivity;
import indi.toaok.themvp.databind.DataBinder;
import indi.toaok.view.SplashDelegate_BT;
import indi.toaok.vo.SplashBean_BT;

/**
 * Created by sj on 2016/11/25.
 */

public class SplashActivity_BT extends BaseSplashActivity<SplashDelegate_BT> {

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
    protected Class<SplashDelegate_BT> getDelegateClass() {
        return SplashDelegate_BT.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new SplashDataBinder_BT();
    }



}
