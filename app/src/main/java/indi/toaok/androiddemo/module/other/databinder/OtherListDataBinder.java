package indi.toaok.androiddemo.module.other.databinder;

import indi.toaok.androiddemo.base.databinder.BaseDataBinder;
import indi.toaok.androiddemo.module.other.bean.OtherListBean;
import indi.toaok.androiddemo.module.other.view.OtherDelegate;

/**
 * @author Toaok
 * @version 1.0  2019/4/26.
 */
public class OtherListDataBinder extends BaseDataBinder<OtherDelegate, OtherListBean> {

    @Override
    public void viewBindModel(OtherDelegate viewDelegate, OtherListBean data) {
        super.viewBindModel(viewDelegate, data);
        viewDelegate.setAdapter(data.getAdapter());
    }
}
