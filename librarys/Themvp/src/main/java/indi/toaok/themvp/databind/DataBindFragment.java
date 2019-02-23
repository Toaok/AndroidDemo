package indi.toaok.themvp.databind;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import indi.toaok.themvp.model.IModel;
import indi.toaok.themvp.presenter.FragmentPresenter;
import indi.toaok.themvp.view.IDelegate;

/**
 * 集成数据-视图绑定的Fragment基类(Presenter)
 *
 * @param <T> View层代理类
 * @author Toaok
 * @version 1.0  2018/9/7.
 */
public abstract class DataBindFragment <T extends IDelegate> extends
        FragmentPresenter<T> {

    protected DataBinder binder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder=getDataBinder();
    }

    protected abstract DataBinder getDataBinder();


    public <D extends IModel> void notifyModelChanged(D data){
        if(binder!=null){
            binder.viewBindModel(viewDelegate,data);
        }
    }
}
