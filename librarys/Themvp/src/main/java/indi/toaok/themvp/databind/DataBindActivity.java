package indi.toaok.themvp.databind;


import android.os.Bundle;

import androidx.annotation.Nullable;

import indi.toaok.themvp.model.IModel;
import indi.toaok.themvp.presenter.ActivityPresenter;
import indi.toaok.themvp.view.IDelegate;


/**
 * 集成数据-视图绑定的activity基类
 *
 * @param <T> View层代理
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public abstract class DataBindActivity<T extends IDelegate> extends
        ActivityPresenter<T> {
    protected DataBinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null && viewDelegate != null) {
            if (viewDelegate instanceof IDelegate && data instanceof IModel) {
                binder.viewBindModel(viewDelegate, data);
            }
        }
    }
}
