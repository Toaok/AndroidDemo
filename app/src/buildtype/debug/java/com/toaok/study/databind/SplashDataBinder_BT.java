package com.toaok.study.databind;


import com.toaok.study.module.home.databinder.SplashDataBinder;
import com.toaok.study.view.SplashDetagate_BT;
import com.toaok.study.vo.SplashBean_BT;
import com.toaok.themvp.databind.DataBinder;


/**
 * 数据绑定
 *
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDataBinder_BT implements DataBinder<SplashDetagate_BT, SplashBean_BT> {

    /**
     * 使用组合模式实现数据绑定的继承实现
     */
    private SplashDataBinder parent = new SplashDataBinder();

    @Override
    public void viewBindModel(SplashDetagate_BT viewDelegate, SplashBean_BT data) {
        parent.viewBindModel(viewDelegate, data);
        if (!data.isAppinfoDisplay()) {
            viewDelegate.setEnvironment(data.getAppInfo().getEnvironment());
            viewDelegate.setChannel(data.getAppInfo().getChannel());
            viewDelegate.setVersion(data.getAppInfo().getVersionName());
            viewDelegate.setApplicationId(data.getAppInfo().getApplicationId());
            viewDelegate.setBuildType(data.getAppInfo().getBuildType());
        }
        data.setAppinfoDisplay(true);
    }
}
