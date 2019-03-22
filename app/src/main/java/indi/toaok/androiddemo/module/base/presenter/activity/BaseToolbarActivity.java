package indi.toaok.androiddemo.module.base.presenter.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.base.presenter.activity.BaseActivity;
import indi.toaok.androiddemo.module.base.bean.ToolbarBean;
import indi.toaok.androiddemo.module.base.databinder.BaseToolbarDataBinder;
import indi.toaok.androiddemo.module.base.view.BaseToolbarDelegate;
import indi.toaok.themvp.databind.DataBinder;

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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }


    public void setTitle(String title){
        mToolbarBean.setTitleText(title);
        notifyModelChanged(mToolbarBean);
    }

    /**
     * Toolbar左按钮点击事件
     */
    @Override
    public void onClickLeftButton() {
        mToolbarBean.setMessage("onClickLeftButton");
        notifyModelChanged(mToolbarBean);
        setResult(RESULT_CANCELED);
        this.finish();
    }

    /**
     * Toolbar右文字点击事件
     */
    @Override
    public void onClickRightText() {
        mToolbarBean.setMessage("onClickRightText");
        notifyModelChanged(mToolbarBean);
    }

    /**
     * Toolbar右按钮点击事件
     */
    @Override
    public void onClickRightButton() {
        mToolbarBean.setMessage("onClickRightButton");
        notifyModelChanged(mToolbarBean);
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
        if (mToolbarBean != null) {
            mToolbarBean = null;
        }
    }

}
