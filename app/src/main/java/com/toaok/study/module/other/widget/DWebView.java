package com.toaok.study.module.other.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.toaok.study.utils.AppUtil;
import com.toaok.study.utils.FilePathUtil;

/**
 * Created by sj on 10/24/16.
 */

public class DWebView<T> extends WebView {

    T data;

    public DWebView(Context context) {
        this(context, null);
    }

    public DWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        /**
         * Android 8.0以上的问题webView的bug
         * java.lang.ClassNotFoundException: Didn't find class "android.webkit.SafeBrowsingResponse" on path: DexPathList[[zip file "/data/hw_init/system/app/WebViewGoogle/WebViewGoogle.apk"],nativeLibraryDirectories=[/data/hw_init/system/app/WebViewGoogle/lib/arm64, /data/hw_init/system/app/WebViewGoogle/WebViewGoogle.apk!/lib/arm64-v8a, /system/lib64, /vendor/lib64, /product/lib64, /system/lib64, /vendor/lib64, /product/lib64]]
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //this.getSettings().setSafeBrowsingEnabled(false);
        }


        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        String cacheDirPath = FilePathUtil.getCacheWebPath();
        this.getSettings().setAppCachePath(cacheDirPath);

        this.getSettings().setDomStorageEnabled(true);
        this.getSettings().setDatabaseEnabled(true);
        this.getSettings().setAppCacheEnabled(true);
        this.getSettings().setAllowFileAccess(true);

        this.getSettings().setLoadWithOverviewMode(true);

        this.setWebViewClient(new JWebViewClient());
        this.setWebChromeClient(new JWebChromeClient());
        fixWebView();
    }

    @TargetApi(11)
    private void fixWebView() {
        int version = AppUtil.getAndroidSDKVersion();
        if (version > 10 && version < 17) {
            removeJavascriptInterface("searchBoxJavaBridge_");
        }
    }

    class JWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            if (onJWebViewProgressLinstener != null) {
                onJWebViewProgressLinstener.cancelShowLoading();
            }
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (onJWebViewOverrideUrlLoadingLinstener != null) {
                OverrideUrlLoadingState overrideUrlLoadingState = onJWebViewOverrideUrlLoadingLinstener.shouldOverrideUrlLoading(view, url);
                if(overrideUrlLoadingState != OverrideUrlLoadingState.Default) {
                    return overrideUrlLoadingState == OverrideUrlLoadingState.False ? false : true;
                }
            }
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class JWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                if (onJWebViewProgressLinstener != null) {
                    onJWebViewProgressLinstener.cancelShowLoading();
                }
            } else {
                if (onJWebViewProgressLinstener != null) {
                    onJWebViewProgressLinstener.showLoading(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    public enum OverrideUrlLoadingState {
        Default, False, Ture
    }

    OnJWebViewOverrideUrlLoadingLinstener onJWebViewOverrideUrlLoadingLinstener;

    public void setOnJWebViewOverrideUrlLoadingLinstener(OnJWebViewOverrideUrlLoadingLinstener onJWebViewOverrideUrlLoadingLinstener) {
        this.onJWebViewOverrideUrlLoadingLinstener = onJWebViewOverrideUrlLoadingLinstener;
    }

    public interface OnJWebViewOverrideUrlLoadingLinstener {
        OverrideUrlLoadingState shouldOverrideUrlLoading(WebView view, String url);
    }

    OnJWebViewProgressLinstener onJWebViewProgressLinstener;

    public void setOnJWebViewProgressLinstener(OnJWebViewProgressLinstener onJWebViewProgressLinstener) {
        this.onJWebViewProgressLinstener = onJWebViewProgressLinstener;
    }

    public interface OnJWebViewProgressLinstener {

        void showLoading(int newProgress);

        void cancelShowLoading();
    }
}
