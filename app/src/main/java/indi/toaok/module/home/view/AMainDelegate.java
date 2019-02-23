package indi.toaok.module.home.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import indi.toaok.R;
import indi.toaok.module.base.view.BaseToolbarDelegate;
import indi.toaok.utils.core.BarUtils;

/**
 * @author Toaok
 * @version 1.0  2018/10/18.
 */
public class AMainDelegate extends BaseToolbarDelegate {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;


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


    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(),
                mDrawerLayout,
                getToolbar(),
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        initDrawerBar();
    }

    private void initDrawerBar() {
        View fakeStatusBar = get(R.id.fake_status_bar);
        BarUtils.setStatusBarAlpha4Drawer(getActivity(), mDrawerLayout, fakeStatusBar, 0, false);
        BarUtils.addMarginTopEqualStatusBarHeight(getToolbar());
    }

    public void setMenuItemSelectListener(OnMenuItemSelectListener listener) {
        navigationView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            return listener.onMenuItemSelect(item);
        });
    }

    public interface OnMenuItemSelectListener {
        boolean onMenuItemSelect(@NonNull MenuItem item);
    }


}
