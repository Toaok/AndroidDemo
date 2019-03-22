package indi.toaok.androiddemo.module.base.databinder;

import android.text.TextUtils;

import indi.toaok.androiddemo.base.databinder.BaseDataBinder;
import indi.toaok.androiddemo.module.base.bean.ToolbarBean;
import indi.toaok.androiddemo.module.base.view.BaseToolbarDelegate;

/**
 * toolbar 数据绑定
 *
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public class BaseToolbarDataBinder extends BaseDataBinder<BaseToolbarDelegate, ToolbarBean> {


    @Override
    public void viewBindModel(BaseToolbarDelegate viewDelegate, ToolbarBean data) {
        super.viewBindModel(viewDelegate, data);
        if (!TextUtils.isEmpty(data.getTitleText()))
            viewDelegate.setTitle(data.getTitleText());
        if (!TextUtils.isEmpty(data.getRightText()))
            viewDelegate.setRightText(data.getRightText());
    }
}
