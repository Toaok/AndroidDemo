package com.toaok.study.module.home.databinder;

import com.toaok.study.module.base.databinder.BaseDataBinder;
import com.toaok.study.module.home.bean.MainBean;
import com.toaok.study.module.home.view.MainDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/9/30.
 */
public class MainDataBinder extends BaseDataBinder<MainDelegate,MainBean> {
    @Override
    public void viewBindModel(MainDelegate viewDelegate, MainBean data) {
        viewDelegate.setViewPager(data.getFragmentManager(),data.getFragmentList());
    }
}
