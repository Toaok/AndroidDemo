package com.toaok.study.module.home.view;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.toaok.study.Config;
import com.toaok.study.R;
import com.toaok.study.module.base.view.BaseToolbarDelegate;
import com.toaok.study.module.other.activity.WebActivity;
import com.toaok.utils.core.BarUtils;

/**
 * @author Toaok
 * @version 1.0  2018/10/18.
 */
public class AMainDelegate extends BaseToolbarDelegate {

    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mDrawerLayout = get(R.id.drawer_layout);
        navigationView = get(R.id.nav_view);
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        NavigationView.OnNavigationItemSelectedListener mListener = item -> {
            switch (item.getItemId()) {
                case R.id.action_git_hub:
                    WebActivity.startActivity(getActivity(), Config.GITHUB, getActivity().getString(R.string.git_hub), true);
                    break;
                default:
                    break;
            }
            return false;
        };
        navigationView.setNavigationItemSelectedListener(mListener);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),
                mDrawerLayout,
                getToolbar(),
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        initDrawerBar();
    }

    private void initDrawerBar(){
        View fakeStatusBar = get(R.id.fake_status_bar);
        BarUtils.setStatusBarAlpha4Drawer(getActivity(), mDrawerLayout, fakeStatusBar,0, false);
        BarUtils.addMarginTopEqualStatusBarHeight(getToolbar());
    }
}
