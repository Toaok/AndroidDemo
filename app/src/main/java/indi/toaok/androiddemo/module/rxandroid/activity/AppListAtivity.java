package indi.toaok.androiddemo.module.rxandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.module.base.presenter.activity.BaseToolbarActivity;
import indi.toaok.androiddemo.module.base.view.BaseToolbarDelegate;
import indi.toaok.androiddemo.module.rxandroid.fragment.AppListFragment;

/**
 * @author Toaok
 * @version 1.0  2019/3/19.
 */
public class AppListAtivity extends BaseToolbarActivity<BaseToolbarDelegate> {

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AppListAtivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_app_list_info));
    }

    @Override
    protected Fragment createFragment() {
        return AppListFragment.newInstance();
    }

    @Override
    protected Class<BaseToolbarDelegate> getDelegateClass() {
        return BaseToolbarDelegate.class;
    }
}
