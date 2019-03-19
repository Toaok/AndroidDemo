package indi.toaok.androiddemo.databind;


import indi.toaok.androiddemo.model.vo.AppInfo;
import indi.toaok.androiddemo.module.home.bean.SplashBean;
import indi.toaok.androiddemo.module.home.databinder.SplashDataBinder;
import indi.toaok.androiddemo.module.home.view.SplashDelegate;
import indi.toaok.androiddemo.view.SplashDelegate_BT;
import indi.toaok.androiddemo.vo.SplashBean_BT;


/**
 * 数据绑定
 *
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
public class SplashDataBinder_BT extends SplashDataBinder<SplashDelegate, SplashBean> {
    @Override
    public void viewBindModel(SplashDelegate viewDelegate, SplashBean data) {
        super.viewBindModel(viewDelegate, data);
        if ((data instanceof SplashBean_BT)&&(viewDelegate instanceof SplashDelegate_BT) ){

            SplashBean_BT splashBean_bt=(SplashBean_BT)data;
            SplashDelegate_BT splashDelegate_bt= (SplashDelegate_BT) viewDelegate;

            AppInfo appInfo=splashBean_bt.getAppInfo();
            if (!splashBean_bt.isAppinfoDisplay()) {
                if (!splashBean_bt.isAppinfoDisplay()) {
                    splashDelegate_bt.setEnvironment(appInfo.getEnvironment());
                    splashDelegate_bt.setChannel(appInfo.getChannel());
                    splashDelegate_bt.setVersion(appInfo.getVersionName());
                    splashDelegate_bt.setApplicationId(appInfo.getApplicationId());
                    splashDelegate_bt.setBuildType(appInfo.getBuildType());
                }
            }
            splashBean_bt.setAppinfoDisplay(true);
        }
    }
}
