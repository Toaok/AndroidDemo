package indi.toaok.module.home.activity;import android.graphics.Bitmap;import android.os.Bundle;import android.os.CountDownTimer;import android.support.annotation.Nullable;import android.text.TextUtils;import android.util.Log;import android.view.KeyEvent;import android.view.View;import indi.toaok.imageloder.core.ImageLoader;import indi.toaok.imageloder.core.LoaderListener;import indi.toaok.BuildConfig;import indi.toaok.R;import indi.toaok.model.vo.SplashImageBean;import indi.toaok.utils.FilePathUtil;import indi.toaok.utils.core.BarUtils;import indi.toaok.utils.core.NetworkUtils;import org.jsoup.Jsoup;import org.jsoup.nodes.Document;import org.jsoup.nodes.Element;import org.jsoup.select.Elements;import java.io.IOException;import java.lang.ref.WeakReference;import java.util.ArrayList;import java.util.List;import indi.toaok.base.presenter.activity.BaseActivity;import indi.toaok.common.cache.SplashImageCache;import indi.toaok.module.home.bean.SplashBean;import indi.toaok.module.home.view.SplashDelegate;/** * TODO 逻辑有点问题，代码有点乱，待优化 * */public abstract class BaseSplashActivity<T extends SplashDelegate> extends BaseActivity<T> {    private static final int COUNT_DOWN_TIME = 5;    private SplashImageCache mSplashImageCache;    protected SplashBean mSplashBean;    private boolean isToHome = false;    //定时器    private CountDownTimer mCountDownTimer = new CountDownTimer(COUNT_DOWN_TIME * 1000, 1000) {        @Override        public void onTick(long millisUntilFinished) {            if (mSplashBean != null) {                int time = (int) ((millisUntilFinished + 1000) / 1000);                mSplashBean.setCountDownTimer("跳过 " + time + "s");                notifyModelChanged(mSplashBean);                if (!mSplashBean.isHaveAd() && COUNT_DOWN_TIME - time == 2) {                    over();                }            }        }        @Override        public void onFinish() {            over();        }    };    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        BarUtils.setStatusBarVisibility(this, false);        super.onCreate(savedInstanceState);        init();    }    @Override    protected void onResume() {        super.onResume();        mCountDownTimer.start();    }    @Override    protected void onPause() {        super.onPause();    }    /**     * 绑定监听事件     */    @Override    protected void bindEvenListener() {        super.bindEvenListener();        viewDelegate.setOnClickListener(this, R.id.tv_count_down_timer);    }    @Override    public void onClick(View v) {        switch (v.getId()) {            case R.id.tv_count_down_timer:                over();                break;        }    }    /**     * 屏蔽返回键     */    @Override    public boolean onKeyDown(int keyCode, KeyEvent event) {        return keyCode == KeyEvent.KEYCODE_BACK                && event.getAction() == KeyEvent.ACTION_DOWN                || super.onKeyDown(keyCode, event);    }    void init() {        initData();    }    /**     * 初始化数据     */    private void initData() {        mSplashImageCache = new SplashImageCache(this);        if (mSplashImageCache.isEmpty()) {            //如果没有数据则不显示            initView();            getSplashData();        } else {            initView(mSplashImageCache.getSplashImageBean() == null ? "" : mSplashImageCache.getSplashImageBean().getUrl());        }    }    /**     * 初始化视图，检查是否有网络，若有网加载网络图片停5s，没有则在启动页停2s后进入主页。     */    private void initView() {        initView("");    }    private void initView(String url) {        if (mSplashBean == null) mSplashBean = new SplashBean();        Log.d("imageUrl", url);        if (!TextUtils.isEmpty(url)) {            ImageLoader.downloadImage(this, url, FilePathUtil.getSplashImagePath(), new LoaderListener() {                @Override                public void onSuccess(Bitmap bitmap) {                }                @Override                public void onError() {                }            });        } else {        }        notifyModelChanged(mSplashBean);        mSplashBean.setUrl(url);    }    private void getSplashData() {        new Thread(new CrawlingDataTask(new WeakReference<>(this))).start();    }    private synchronized void jumpHome() {        if (isToHome) return;        MainActivity.startActivity(this);        isToHome = true;        finish();    }    private void clearTimer() {        if (mCountDownTimer != null) {            mCountDownTimer.cancel();            mCountDownTimer = null;        }    }    private void over() {        jumpHome();    }    @Override    protected void onDestroy() {        super.onDestroy();        /*         * huwei emui 8.0 memory leaks         * */        if (mSplashBean != null) {            mSplashBean = null;        }        if (mSplashImageCache != null) {            mSplashImageCache.clear();            mSplashImageCache = null;        }        clearTimer();    }    /**     * 启动线程获取网络图片数据，避免内存泄漏。     */    static class CrawlingDataTask implements Runnable {        WeakReference<BaseSplashActivity> mWeakReference;        public CrawlingDataTask(WeakReference<BaseSplashActivity> weakReference) {            this.mWeakReference = weakReference;        }        @Override        public void run() {            BaseSplashActivity activity = mWeakReference.get();            List<SplashImageBean> images = new ArrayList<>();            try {                Document document = Jsoup.connect(BuildConfig.SPLASH_URL).get();                Elements imgs = document.select(BuildConfig.SPLASH_CSSQUERY);                for (int i = 0; i < imgs.size(); ++i) {                    Element e = imgs.get(i);                    String src = e.attr(BuildConfig.SPLASH_ATTRIBUTEKEY);                    if (!TextUtils.isEmpty(src)) {                        SplashImageBean bean = new SplashImageBean();                        bean.setUrl(src);                        bean.setId(i);                        images.add(bean);                    }                }            } catch (IOException e) {                e.printStackTrace();            }            activity.mSplashImageCache.setData(images);        }    }}