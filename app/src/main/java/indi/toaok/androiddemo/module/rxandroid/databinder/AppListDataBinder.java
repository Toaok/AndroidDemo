package indi.toaok.androiddemo.module.rxandroid.databinder;

import indi.toaok.androiddemo.base.databinder.BaseDataBinder;
import indi.toaok.androiddemo.module.rxandroid.bean.AppListBean;
import indi.toaok.androiddemo.module.rxandroid.view.AppListDelegate;

/**
 * @author Toaok
 * @version 1.0  2019/3/20.
 */
public class AppListDataBinder extends BaseDataBinder<AppListDelegate, AppListBean> {
    @Override
    public void viewBindModel(AppListDelegate viewDelegate, AppListBean data) {
        super.viewBindModel(viewDelegate,data);
        if (!viewDelegate.isSetAdapter()) {
            viewDelegate.setAdapter(data.getAdapter());
        }
        if (!viewDelegate.isSetRefreshingListener()) {
            viewDelegate.setOnRefreshListener(data.getRefreshListener());
        }
        viewDelegate.setRefreshing(data.isRefreshing());
        viewDelegate.setVisibility(data.getVisibility());
    }
}
