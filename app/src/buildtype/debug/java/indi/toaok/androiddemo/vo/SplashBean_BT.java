package indi.toaok.androiddemo.vo;

import indi.toaok.androiddemo.model.vo.AppInfo;
import indi.toaok.androiddemo.module.home.bean.SplashBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Toaok
 * @version 1.0  2018/9/8.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class SplashBean_BT extends SplashBean {

    private boolean appinfoDisplay;
    private AppInfo appInfo;

    public SplashBean_BT(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    //继承父类的值
    public SplashBean_BT(AppInfo appInfo, SplashBean splashBean) {
        this.appInfo = appInfo;
        //
        setSplashBean(splashBean);
    }
}
