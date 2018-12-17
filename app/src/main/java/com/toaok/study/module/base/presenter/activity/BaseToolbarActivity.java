package com.toaok.study.module.base.presenter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.toaok.study.R;
import com.toaok.study.base.presenter.activity.BaseActivity;
import com.toaok.study.module.base.bean.ToolbarBean;
import com.toaok.study.module.base.databinder.BaseToolbarDataBinder;
import com.toaok.study.module.base.view.BaseToolbarDelegate;
import com.toaok.themvp.databind.DataBinder;

/**
 * 带有toolbar的基类
 * @author Toaok
 * @version 1.0  2018/10/8.
 */
public abstract class BaseToolbarActivity<T extends BaseToolbarDelegate> extends BaseActivity<T> implements BaseToolbarDelegate.OnClickToolBarListener {

    protected ToolbarBean mToolbarBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewDelegate.setToolBarListener(this);
        mToolbarBean = new ToolbarBean();
        initToolbarView();

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //通过fragmentManager获得Fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 初始化Toolbar的显示
     * leftImageButtong是返回
     * 若要定义左边的图标重写该方法
     */
    protected void initToolbarView() {
        viewDelegate.setLeftIcon(R.drawable.ic_arrow_back_white);
    }


    /**
     * Toolbar左按钮点击事件
     */
    @Override
    public void onClickLeftButton() {
        viewDelegate.toast("onClickLeftButton");
        this.finish();
    }

    /**
     * Toolbar右文字点击事件
     */
    @Override
    public void onClickRightText() {
        viewDelegate.toast("onClickRightText");
    }

    /**
     * Toolbar右按钮点击事件
     */
    @Override
    public void onClickRightButton() {
        viewDelegate.toast("onClickRightButton");
    }


    /**
     * 创建fragment
     * @return Fragment
     */
    protected abstract Fragment createFragment();

    @Override
    public DataBinder getDataBinder() {
        return new BaseToolbarDataBinder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * huwei emui 8.0 memory leaks
         * */
        if (mToolbarBean != null) {
            mToolbarBean = null;
        }

    }

}
