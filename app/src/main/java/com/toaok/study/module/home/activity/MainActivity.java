package com.toaok.study.module.home.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.toaok.study.R;
import com.toaok.study.base.BaseActivity;
import com.toaok.study.module.home.view.MainDelegate;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity<MainDelegate> implements View.OnClickListener {

    public static void startActivity(Context context) {

        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }

    @Override
    protected Class getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_map:
                viewDelegate.setTabBackground(MainDelegate.MAP_PAGER);
                break;
            case R.id.btn_kline:
                viewDelegate.setTabBackground(MainDelegate.KLINE_PAGER);
                break;
            case R.id.btn_other:
                viewDelegate.setTabBackground(MainDelegate.OTHER_PAGER);
                break;

        }

    }


}
