package indi.toaok.androiddemo.module.home.databinder;

import indi.toaok.androiddemo.base.databinder.BaseDataBinder;
import indi.toaok.androiddemo.module.home.bean.MainBean;
import indi.toaok.androiddemo.module.home.view.FMainDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/9/30.
 */
public class MainDataBinder extends BaseDataBinder<FMainDelegate,MainBean> {
    @Override
    public void viewBindModel(FMainDelegate viewDelegate, MainBean data) {
        super.viewBindModel(viewDelegate,data);
    }
}
