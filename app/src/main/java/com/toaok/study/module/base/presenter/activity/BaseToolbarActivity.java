package com.toaok.study.module.base.presenter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.toaok.study.R;
import com.toaok.study.base.presenter.activity.BaseActivity;
import com.toaok.study.module.base.databinder.BaseToolbarDataBinder;
import com.toaok.study.module.base.view.BaseToolbarDelegate;
import com.toaok.themvp.databind.DataBinder;

/**
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public abstract class BaseToolbarActivity extends BaseActivity<BaseToolbarDelegate> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //通过fragmentManager获得Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    protected abstract Fragment createFragment();

    @Override
    public DataBinder getDataBinder() {
        return new BaseToolbarDataBinder();
    }

    @Override
    protected Class<BaseToolbarDelegate> getDelegateClass() {
        return BaseToolbarDelegate.class;
    }
}
