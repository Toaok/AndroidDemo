package com.toaok.study.base;

import android.app.Application;

import com.leaks.IMMLeaks;
import com.toaok.themvp.databind.DataBindActivity;
import com.toaok.themvp.databind.DataBinder;

/**
 * 基准Activity 所有在该项目中使用TheMVP框架的Activity都必须继承该抽象类
 *
 * @author Toaok
 * @version 1.0  2018/9/18.
 */
public abstract class BaseActivity<T extends BaseDelegate> extends DataBindActivity<T> {


    /**
     * 如果该Fragment需要进行数据绑定，必须重新该方法
     *
     * @return 返回数据绑定类
     */
    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    /**
     * 1、处理HUAWEI手机InputMethodManager.mLastSrvView内存泄漏问题
     * {@link com.leaks.IMMLeaks#fixLeak(Application)},但好像没有完全解决
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        IMMLeaks.fixLeak(getApplication());
    }
}
