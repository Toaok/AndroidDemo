package com.toaok.study.module.home.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.toaok.study.BuildConfig;
import com.toaok.study.R;
import com.toaok.study.base.BaseActivity;
import com.toaok.study.common.cache.SplashImageCache;
import com.toaok.study.model.vo.SplashBean;
import com.toaok.study.model.vo.SplashImageBean;
import com.toaok.study.module.home.view.SplashDelegate;
import com.toaok.utilcode.util.BarUtils;
import com.toaok.utilcode.util.NetworkUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSplashActivity<T extends SplashDelegate> extends BaseActivity<T> implements View.OnClickListener {

    private SplashImageCache mSplashImageCache;
    protected SplashBean mSplashBean;

    //定时器
    private CountDownTimer mCountDownTimer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    /**
     * 绑定监听事件
     */
    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.tv_count_down_timer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_count_down_timer:
                over();
                break;
        }
    }

    /**
     * 屏蔽返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    void init() {
        initView();
    }


    private void initView() {
        if (NetworkUtils.isAvailableByPing()) {
            BarUtils.setStatusBarAlpha(this, 0);
            mSplashImageCache = new SplashImageCache(this);
            if (mSplashBean == null) mSplashBean = new SplashBean();
            mCountDownTimer = new TimerTask(new WeakReference<BaseSplashActivity>(this), 5000, 1000);
            if (mSplashImageCache.isEmpty()) {
                getSplashData();
            } else {
                updatUI(mSplashImageCache.getData().get((int) (Math.random() * (mSplashImageCache.dataSize()))));
            }
        } else {
            BarUtils.setStatusBarVisibility(this, false);
            mCountDownTimer = new TimerTask(new WeakReference<BaseSplashActivity>(this), 3000, 1000);
            if (mSplashBean == null) mSplashBean = new SplashBean();
            mSplashBean.setAvailableByPing(false);
            mCountDownTimer.start();
        }
    }

    private void getSplashData() {
        new Thread(new DataTask(new WeakReference<>(this))).start();
    }


    private void updatUI(final SplashImageBean bean) {
        runOnUiThread(() -> {
            mCountDownTimer.start();
            if (!TextUtils.isEmpty(bean.getUrl())) {
                Log.i("imageUrl", bean.getUrl());
                if (mSplashBean == null) {
                    mSplashBean = new SplashBean(bean.getUrl());
                }
                mSplashBean.setUrl(bean.getUrl());
            }
            mSplashImageCache.saveData();
        });

    }

    private synchronized void jumpHome() {
        MainActivity.startActivity(BaseSplashActivity.this);
        finish();
    }

    private void clearTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private void over() {
        clearTimer();
        jumpHome();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearTimer();
    }


    /**
     * 所有内部类都使用静态内部类，避免内存泄漏。
     */

    static class DataTask implements Runnable {

        WeakReference<BaseSplashActivity> mWeakReference;

        public DataTask(WeakReference<BaseSplashActivity> weakReference) {
            this.mWeakReference = weakReference;
        }

        @Override
        public void run() {
            BaseSplashActivity activity = mWeakReference.get();
            List<SplashImageBean> images = new ArrayList<>();
            try {
                Document document = Jsoup.connect(BuildConfig.SPLASH_URL).get();
                Elements imgs = document.select(BuildConfig.SPLASH_CSSQUERY);

                for (int i = 0; i < imgs.size(); ++i) {
                    Element e = imgs.get(i);
                    String src = e.attr(BuildConfig.SPLASH_ATTRIBUTEKEY);
                    if (!TextUtils.isEmpty(src)) {
                        SplashImageBean bean = new SplashImageBean();
                        bean.setUrl(src);
                        bean.setId(i);
                        images.add(bean);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            activity.mSplashImageCache.setData(images);
            activity.initView();
        }
    }

    static class TimerTask extends CountDownTimer {


        private BaseSplashActivity activity;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimerTask(WeakReference<BaseSplashActivity> weakReference, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            activity = weakReference.get();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (activity.mSplashBean != null) {
                activity.mSplashBean.setCountDownTimer("跳过 " +millisUntilFinished / 1000  + "s");
                activity.notifyModelChanged(activity.mSplashBean);
            }
        }

        @Override
        public void onFinish() {
            activity.over();
        }
    }
}
