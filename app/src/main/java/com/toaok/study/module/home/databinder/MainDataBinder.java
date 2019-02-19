package com.toaok.study.module.home.databinder;

import com.toaok.study.base.databinder.BaseDataBinder;
import com.toaok.study.module.home.bean.MainBean;
import com.toaok.study.module.home.view.FMainDelegate;

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
