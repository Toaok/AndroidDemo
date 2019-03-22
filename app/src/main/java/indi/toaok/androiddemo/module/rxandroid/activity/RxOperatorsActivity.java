package indi.toaok.androiddemo.module.rxandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import indi.toaok.androiddemo.R;
import indi.toaok.androiddemo.module.base.presenter.activity.BaseToolbarActivity;
import indi.toaok.androiddemo.module.base.view.BaseToolbarDelegate;
import indi.toaok.androiddemo.module.rxandroid.fragment.RxOperatorsFragment;

/**
 * @author Toaok
 * @version 1.0  2019/3/21.
 */
public class RxOperatorsActivity extends BaseToolbarActivity<BaseToolbarDelegate> {


    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent = intent.setClass(context, RxOperatorsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Fragment createFragment() {
        return RxOperatorsFragment.newInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.rxandroid));
    }

    @Override
    protected Class<BaseToolbarDelegate> getDelegateClass() {
        return BaseToolbarDelegate.class;
    }
}
