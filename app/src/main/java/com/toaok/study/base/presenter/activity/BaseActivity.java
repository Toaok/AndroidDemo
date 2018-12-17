package com.toaok.study.base.presenter.activity;import android.app.Application;import android.view.View;import com.toaok.study.base.view.BaseDelegate;import com.toaok.study.leaks.IMMLeaks;import com.toaok.themvp.databind.DataBindActivity;import com.toaok.themvp.databind.DataBinder;/** * 基准Activity 所有在该项目中使用TheMVP框架的Activity都必须继承该抽象类 * * @author Toaok * @version 1.0  2018/9/18. */public abstract class BaseActivity<T extends BaseDelegate> extends DataBindActivity<T> implements View.OnClickListener{    /**     * 如果该Activity需要进行数据绑定，必须重新该方法     *     * @return 返回数据绑定类     */    @Override    public DataBinder getDataBinder() {        return null;    }    /**     * 1、处理HUAWEI手机InputMethodManager.mLastSrvView内存泄漏问题     * {@link com.toaok.study.leaks.IMMLeaks#fixLeak(Application)},但好像没有完全解决     */    @Override    protected void onDestroy() {        super.onDestroy();        /*         * huwei emui 8.0 memory leaks         * */        if(binder!=null){            binder=null;        }        IMMLeaks.fixLeak(getApplication());    }    /**     * 上次点击时间     */    private long lastClick = 0;    /**     * 判断是否快速点击     *     * @return {@code true}: 是<br>{@code false}: 否     */    private boolean isFastClick() {        long now = System.currentTimeMillis();        if (now - lastClick >= 200) {            lastClick = now;            return false;        }        return true;    }    @Override    public void onClick(View view) {        if (isFastClick())return;    }}