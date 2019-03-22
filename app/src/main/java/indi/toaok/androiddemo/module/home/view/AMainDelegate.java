package indi.toaok.androiddemo.module.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindDrawable;
import butterknife.BindView;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.module.base.view.BaseToolbarDelegate;
import indi.toaok.utils.core.BarUtils;

/**
 * @author Toaok
 * @version 1.0  2018/10/18.
 */
public class AMainDelegate extends BaseToolbarDelegate {

    //View
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    //Drable
    @BindDrawable(R.drawable.ic_menu_white)
    Drawable drawableMenuWhite;
    @BindDrawable(R.drawable.ic_more_vert_white)
    Drawable drawableMoreVertWhite;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        initDrawerLayout();
        initToolbarView();
    }


    protected void initToolbarView() {
        setLeftIcon(drawableMenuWhite);
        setRightIcon(drawableMoreVertWhite);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    /**
     * 在这里初始化Drawerlayout时会影响ToobarLeftIcon的显示
     */
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
            boolean isSuccess = listener.onMenuItemSelect(item);
            mDrawerLayout.post(() -> mDrawerLayout.closeDrawer(Gravity.LEFT, false));
            return isSuccess;
        });
    }

    public interface OnMenuItemSelectListener {
        boolean onMenuItemSelect(@NonNull MenuItem item);
    }


}
