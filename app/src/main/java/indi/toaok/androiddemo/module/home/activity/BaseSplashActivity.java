package indi.toaok.androiddemo.module.home.activity;import android.graphics.Bitmap;import android.os.Bundle;import android.os.CountDownTimer;import android.text.TextUtils;import android.util.Log;import android.view.KeyEvent;import android.view.View;import androidx.annotation.Nullable;import indi.toaok.androiddemo.R;import indi.toaok.androiddemo.base.presenter.activity.BaseActivity;import indi.toaok.androiddemo.common.cache.SplashImageCache;import indi.toaok.androiddemo.http.rx.RxSubscriberHelper;import indi.toaok.androiddemo.http.rx.api.splash.SplashApi;import indi.toaok.androiddemo.http.rx.api.splash.SplashRequestBean;import indi.toaok.androiddemo.http.rx.api.splash.SplashResultBean;import indi.toaok.androiddemo.model.local.preferences.SystemSharePreference;import indi.toaok.androiddemo.model.vo.SplashImageBean;import indi.toaok.androiddemo.module.home.bean.SplashBean;import indi.toaok.androiddemo.module.home.view.SplashDelegate;import indi.toaok.androiddemo.utils.AppUtil;import indi.toaok.androiddemo.utils.FilePathUtil;import indi.toaok.imageloder.core.ImageLoder;import indi.toaok.imageloder.core.LoaderListener;import indi.toaok.imageloder.core.util.ImageUtil;import indi.toaok.utils.core.BarUtils;import io.reactivex.Observable;import io.reactivex.schedulers.Schedulers;/** * TODO 逻辑有点问题，代码有点乱，待优化 */public abstract class BaseSplashActivity<T extends SplashDelegate> extends BaseActivity<T> {    private static final String TAG = BaseSplashActivity.class.getSimpleName();    private static final int COUNT_DOWN_TIME = 5;    private SplashImageCache mSplashImageCache;    protected SplashBean mSplashBean;    private boolean isToHome = false;    //定时器    private CountDownTimer mCountDownTimer = new CountDownTimer(COUNT_DOWN_TIME * 1000, 1000) {        @Override        public void onTick(long millisUntilFinished) {            if (mSplashBean != null) {                int time = (int) ((millisUntilFinished + 1000) / 1000);                mSplashBean.setCountDownTimer("跳过 " + time + "s");                notifyModelChanged(mSplashBean);                if (!mSplashBean.isHaveAd() && COUNT_DOWN_TIME - time == 2) {                    over();                }            }        }        @Override        public void onFinish() {            over();        }    };    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        BarUtils.setStatusBarVisibility(this, false);        super.onCreate(savedInstanceState);        init();    }    @Override    protected void onResume() {        super.onResume();        mCountDownTimer.start();    }    @Override    protected void onPause() {        super.onPause();    }    /**     * 绑定监听事件     */    @Override    protected void bindEvenListener() {        super.bindEvenListener();        viewDelegate.setOnClickListener(this, R.id.tv_count_down_timer);    }    @Override    public void onClick(View v) {        switch (v.getId()) {            case R.id.tv_count_down_timer:                over();                break;        }    }    /**     * 屏蔽返回键     */    @Override    public boolean onKeyDown(int keyCode, KeyEvent event) {        return keyCode == KeyEvent.KEYCODE_BACK                && event.getAction() == KeyEvent.ACTION_DOWN                || super.onKeyDown(keyCode, event);    }    void init() {        initView();        initData();    }    /**     * 初始化数据     */    private void initData() {        SplashImageBean splashImageBean = SplashImageBean.fromJson(SystemSharePreference.getSplashImageData());        mSplashImageCache = new SplashImageCache(AppUtil.getApplication(), splashImageBean == null ? -1 : splashImageBean.getId());        if (mSplashImageCache.isEmpty()) {            //如果没有数据则不显示            getSplashData();        } else {            initView(mSplashImageCache.getCurrentSplashImageBean() == null ? "" : mSplashImageCache.getCurrentSplashImageBean().getUrl());        }    }    private void initView() {        initView("");    }    private void initView(String url) {        if (mSplashBean == null) mSplashBean = new SplashBean();        Log.d("imageUrl", url);        if (!TextUtils.isEmpty(url)) {            //根据uri获取图片            Bitmap bitmap = ImageUtil.getCacheImage(FilePathUtil.getSplashImagePath(), url);            if (bitmap == null) {                //如果图片未下载，去下载。                downloadSplashImage(url);            } else {                //已下载，显示                mSplashBean.setHaveAd(true);                mSplashBean.setImage(bitmap);            }        }        mSplashBean.setUrl(url);        notifyModelChanged(mSplashBean);    }    private void downloadSplashImage(String url) {        ImageLoder.downloadImage(this, url, FilePathUtil.getSplashImagePath(), new LoaderListener() {            @Override            public void onSuccess(Bitmap bitmap) {                if (mSplashImageCache == null) {                    mSplashImageCache = new SplashImageCache(AppUtil.getApplication());                }                SystemSharePreference.setSplashImageData(mSplashImageCache.getNextSplashImageBean().toJson());            }            @Override            public void onError() {                Log.i(TAG, "图片下载失败");            }        });    }    private void getSplashData() {        SplashRequestBean requestBean = new SplashRequestBean();        new SplashApi().getImages(requestBean, new RxSubscriberHelper<SplashResultBean>() {            @Override            public void onNext(SplashResultBean splashResultBean) {                Observable.fromIterable(splashResultBean.getResult()).map(resultBean -> {                    SplashImageBean imageBean = new SplashImageBean();                    imageBean.setUrl(resultBean.getImg());                    return imageBean;                }).toList().subscribeOn(Schedulers.io())                        .subscribe(splashImageBeans -> {                            if (mSplashImageCache == null) {                                mSplashImageCache = new SplashImageCache(AppUtil.getApplication());                            }                            mSplashImageCache.setData(splashImageBeans);                            SplashImageBean nextSplashImageBean = mSplashImageCache.getNextSplashImageBean();                            if (nextSplashImageBean != null) {                                downloadSplashImage(nextSplashImageBean.getUrl());                            }                        });            }        });    }    private synchronized void jumpHome() {        if (isToHome) return;        MainActivity.startActivity(this);        isToHome = true;        finish();    }    private void clearTimer() {        if (mCountDownTimer != null) {            mCountDownTimer.cancel();            mCountDownTimer = null;        }    }    private void over() {        jumpHome();    }    @Override    protected void onDestroy() {        super.onDestroy();        /*         * huwei emui 8.0 memory leaks         * */        if (mSplashBean != null) {            mSplashBean = null;        }        if (mSplashImageCache != null) {            mSplashImageCache.clear();            mSplashImageCache = null;        }        clearTimer();    }}