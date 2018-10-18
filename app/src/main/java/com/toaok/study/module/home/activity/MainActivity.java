package com.toaok.study.module.home.activity;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import com.toaok.study.R;import com.toaok.study.module.base.presenter.activity.BaseToolbarActivity;import com.toaok.study.module.home.fragemnt.MainFragment;import com.toaok.study.module.home.view.MainDelegate;/** * 主界面 */public class MainActivity extends BaseToolbarActivity implements MainFragment.SelectTab {    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);    }    @Override    protected void initToolbarView() {        viewDelegate.getLeftImageButton().setImageResource(R.drawable.ic_menu_white);        viewDelegate.getRightImageButton().setImageResource(R.drawable.ic_more_vert_white);    }    @Override    public void onClickLeftButton() {        //TODO        // ......    }    @Override    protected Fragment createFragment() {        return MainFragment.newInstance("MainFragment");    }    public static void startActivity(Context context) {        Intent intent = new Intent();        intent.setClass(context, MainActivity.class);        context.startActivity(intent);    }    @Override    public void selected(@MainDelegate.VPI int tabId) {        switch (tabId) {            case MainDelegate.MAP_PAGER:                mToolbarBean.setTitleText(getString(R.string.home_map));                notifyModelChanged(mToolbarBean);                break;            case MainDelegate.KLINE_PAGER:                mToolbarBean.setTitleText(getString(R.string.home_kline));                notifyModelChanged(mToolbarBean);                break;            case MainDelegate.OTHER_PAGER:                mToolbarBean.setTitleText(getString(R.string.home_other));                notifyModelChanged(mToolbarBean);                break;        }    }}