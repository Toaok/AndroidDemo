package com.toaok.study.module.other.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;

import com.toaok.study.R;
import com.toaok.study.module.base.presenter.activity.BaseToolbarActivity;
import com.toaok.study.module.base.view.BaseToolbarDelegate;
import com.toaok.study.module.other.fragment.WebViewFragment;

/**
 *WebView页面
 */
public class WebActivity extends BaseToolbarActivity<BaseToolbarDelegate> {

    public final static String EXTRA_TITLE = "EXTRA_TITLE";
    public final static String EXTRA_URL = "EXTRA_URL";
    public final static String EXTRA_ISWITHCACHE = "EXTRA_ISWITHCACHE";
    public final static String EXTRA_ISSHARE = "EXTRA_ISSHARE";

    private WebViewFragment mWebViewFragment;

    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent();
        intent.setClass(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String url, String title, boolean isCache) {
        Intent intent = new Intent();
        intent.setClass(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_ISWITHCACHE, isCache);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String url, String title, boolean isCache, boolean isShare) {
        Intent intent = new Intent();
        intent.setClass(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_ISWITHCACHE, isCache);
        intent.putExtra(EXTRA_ISSHARE, isShare);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifyModelChanged(mToolbarBean);
    }


    @Override
    protected void initToolbarView() {
        super.initToolbarView();
        Intent intent = getIntent();
        mToolbarBean.setTitleText(intent.getStringExtra(EXTRA_TITLE));
        mToolbarBean.setRightText(intent.getBooleanExtra(EXTRA_ISSHARE, false)?getString(R.string.right_share_text):"");
    }

    /**
     * 拦截返回键
     * @param keyCode   键码
     * @param event     按键事件
     * @return          事件是否完成
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           /* if (viewDelegate.getWvContent().canGoBack()) {
                viewDelegate.getWvContent().goBack();
                return true;
            }
            viewDelegate.getWvContent().loadData("", "text/html; charset=UTF-8", null);*/
            mWebViewFragment.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClickLeftButton() {
        mWebViewFragment.goBack();
    }


    /**
     * 结束当前进程
     * 在finish中结束进程会导致闪屏问题
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        /**
         * 原因：{@link android.app.Activity#finish()}是立即将该Activity从当前栈顶移除，
         * 并不会立即调用{@link android.app.Activity#onDestroy()}方法释放该Activity所占用的资源，
         * 由于此时该Activity所占用的资源还未完全释放，调用{@link java.lang.System#exit(int)},
         * 会直接结束当前进程，导致闪屏问题，所以应该在{@link this#onDestroy()}在中等Activity所占用资源释放后，
         * 再结束当前线程
         */
        System.exit(0);
    }

    @Override
    protected Class<BaseToolbarDelegate> getDelegateClass() {
        return BaseToolbarDelegate.class;
    }

    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        String url = intent.getStringExtra(EXTRA_URL);
        Log.i("url",url);
        mWebViewFragment=WebViewFragment.newInstance(url);
        return mWebViewFragment;
    }
}