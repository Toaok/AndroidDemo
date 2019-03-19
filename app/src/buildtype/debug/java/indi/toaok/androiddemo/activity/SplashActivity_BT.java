package indi.toaok.androiddemo.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;

import indi.toaok.androiddemo.databind.SplashDataBinder_BT;
import indi.toaok.androiddemo.model.vo.AppInfo;
import indi.toaok.androiddemo.module.home.activity.BaseSplashActivity;
import indi.toaok.themvp.databind.DataBinder;
import indi.toaok.androiddemo.view.SplashDelegate_BT;
import indi.toaok.androiddemo.vo.SplashBean_BT;

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
