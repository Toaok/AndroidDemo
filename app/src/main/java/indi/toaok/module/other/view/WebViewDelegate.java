package indi.toaok.module.other.view;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.ProgressBar;

import indi.toaok.R;
import indi.toaok.base.view.BaseDelegate;
import indi.toaok.module.other.fragment.WebViewFragment;
import indi.toaok.module.other.widget.DWebView;

import indi.toaok.base.view.BaseDelegate;

/**
 * @author Toaok
 * @version 1.0  2018/10/16.
 */
public class WebViewDelegate extends BaseDelegate {

    private DWebView wvContent;
    private ProgressBar progressBar;


    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        wvContent = get(R.id.wv_content);
        progressBar = get(R.id.progress_bar);
    }


    public void initWebView(final String url) {
        progressBar.setMax(100 * 10000);
        wvContent.setOnJWebViewProgressLinstener(new DWebView.OnJWebViewProgressLinstener() {
            @Override
            public void showLoading(int newProgress) {
                WebViewDelegate.this.showLoading(newProgress);
            }

            @Override
            public void cancelShowLoading() {
                WebViewDelegate.this.cancelShowLoading();
            }
        });
        wvContent.setDownloadListener((url1, userAgent, contentDisposition, mimetype, contentLength) -> downloadByBrowser(url1));
        wvContent.requestFocusFromTouch();
        wvContent.loadUrl(url);
        Log.d("url", url);
    }

    /*
     * 浏览器下载
     * */
    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        getActivity().startActivity(intent);
    }

    protected void showLoading(int newProgress) {
        progressBar.setProgress(newProgress * 10000);
        if (newProgress == 100) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    protected void cancelShowLoading() {
        progressBar.setVisibility(View.GONE);
    }


    public DWebView getWvContent() {
        return wvContent;
    }

    public void setWvContent(DWebView wvContent) {
        this.wvContent = wvContent;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void goBack() {
        if (wvContent.canGoBack()) {
            wvContent.goBack();
            return;
        }
        if (getActivity() != null)
            getActivity().finish();
    }
}
