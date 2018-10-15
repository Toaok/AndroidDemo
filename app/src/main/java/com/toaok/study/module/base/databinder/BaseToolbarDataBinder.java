package com.toaok.study.module.base.databinder;

import com.toaok.study.base.databinder.BaseDataBinder;
import com.toaok.study.module.base.bean.ToolbarBean;
import com.toaok.study.module.base.view.BaseToolbarDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public class BaseToolbarDataBinder extends BaseDataBinder<BaseToolbarDelegate,ToolbarBean>  {


    @Override
    public void viewBindModel(BaseToolbarDelegate viewDelegate, ToolbarBean data) {
        viewDelegate.getLeftTextView().setText(data.getLeftText());
        viewDelegate.getCenterTextView().setText(data.getCenterText());
        viewDelegate.getRightTextView().setText(data.getRightText());
    }
}
